package foodDelivery.presentationLayer;

import foodDelivery.businessLayer.DeliveryService;
import foodDelivery.dataLayer.FileWriter;
import foodDelivery.dataLayer.Serializator;
import foodDelivery.model.Client;
import foodDelivery.model.CompositeProduct;
import foodDelivery.model.MenuItem;
import foodDelivery.model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The type Client w indow.
 */
public class ClientWIndow {
    /**
     * The My serializer.
     */
    Serializator mySerializer;
    /**
     * The My client.
     */
    Client myClient;
    /**
     * The My delivery service.
     */
    DeliveryService myDeliveryService;
    /**
     * The My file writer.
     */
    foodDelivery.dataLayer.FileWriter myFileWriter = new foodDelivery.dataLayer.FileWriter();
    private JPanel clientPanel;
    private JTable productTable;
    private JTextField priceFilterTextField;
    private JTextField sodiumFilterTextField;
    private JTextField fatFilterTextField;
    private JTextField proteinFilterTextField;
    private JTextField caloriesFilterTextField;
    private JTextField ratingFilterTextField;
    private JTextField titleFilterTextField;
    private JButton placeOrderButton;
    private JButton searchButton;
    /**
     * The Product list.
     */
    ArrayList<MenuItem> productList;

    /**
     * Gets client panel.
     *
     * @return the client panel
     */
    public JPanel getClientPanel() {
        return clientPanel;
    }

    /**
     * Load products.
     */
    public void loadProducts() {
        DefaultTableModel myModel = (DefaultTableModel) productTable.getModel();
        myModel.setRowCount(0);
        String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        myModel.setColumnIdentifiers(columnNames);

        for(String[] x : myDeliveryService.convToTableFormat()) {
            myModel.addRow(x);
        }
    }

    /**
     * Load filtered product list.
     *
     * @param filteredList the filtered list
     */
    public void loadFilteredProductList(ArrayList<MenuItem> filteredList) {
        DefaultTableModel myModel = (DefaultTableModel) productTable.getModel();
        myModel.setRowCount(0);
        String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        myModel.setColumnIdentifiers(columnNames);
        for(MenuItem x : filteredList) {
            myModel.addRow(myDeliveryService.convItemToTableFormat(x));
        }
    }

    /**
     * Instantiates a new Client w indow.
     *
     * @param paramMyDeliveryService the param my delivery service
     * @param user                   the user
     */
    public ClientWIndow(DeliveryService paramMyDeliveryService, String user) {
        this.myDeliveryService = paramMyDeliveryService;
        mySerializer = new Serializator();
        myFileWriter = new FileWriter();


        loadProducts();
        myClient = myDeliveryService.getClientByUsername(user);
        productList = myDeliveryService.getProductList();

        searchButton.addActionListener(e -> {
            ArrayList<MenuItem> filteredProductList = myDeliveryService.getProductList();
            try {
                String titleFilter = titleFilterTextField.getText();
                if(titleFilter.length() != 0) filteredProductList = new ArrayList<MenuItem>(filteredProductList.stream().filter(product -> (product.getTitle().contains(titleFilter) == true)).collect(Collectors.toList()));

            } catch (Exception ex) { }
            try {
                double ratingFilter = Double.parseDouble(ratingFilterTextField.getText());
                filteredProductList = new ArrayList<MenuItem>(filteredProductList.stream().filter(product -> (ratingFilter <= product.getRating())).collect(Collectors.toList()));
            } catch (Exception ex) { }
            try {
                int caloriesFilter = Integer.valueOf(caloriesFilterTextField.getText());
                filteredProductList = new ArrayList<MenuItem>(filteredProductList.stream().filter(product -> (caloriesFilter >= product.getCalories())).collect(Collectors.toList()));
            } catch (Exception ex) { }
            try {
                int proteinFilter = Integer.valueOf(proteinFilterTextField.getText());
                filteredProductList = new ArrayList<MenuItem>(filteredProductList.stream().filter(product -> (proteinFilter >= product.getProtein())).collect(Collectors.toList()));
            } catch (Exception ex) { }
            try {
                int fatFilter = Integer.valueOf(fatFilterTextField.getText());
                filteredProductList = new ArrayList<MenuItem>(filteredProductList.stream().filter(product -> (fatFilter >= product.getFat())).collect(Collectors.toList()));
            } catch (Exception ex) { }
            try {
                int sodiumFilter = Integer.valueOf(sodiumFilterTextField.getText());
                filteredProductList = new ArrayList<MenuItem>(filteredProductList.stream().filter(product -> (sodiumFilter >= product.getSodium())).collect(Collectors.toList()));
            } catch (Exception ex) { }
            try {
                int priceFilter = Integer.valueOf(priceFilterTextField.getText());
                filteredProductList = new ArrayList<MenuItem>(filteredProductList.stream().filter(product -> (priceFilter >= product.computePrice())).collect(Collectors.toList()));
            } catch (Exception ex) { }

            loadFilteredProductList(filteredProductList);
            productList = filteredProductList;
            mySerializer.serialize(myDeliveryService);
        });


        placeOrderButton.addActionListener(e -> {
            int[] indexProductsToCompose = productTable.getSelectedRows();
            if(indexProductsToCompose.length > 0) {
                ArrayList<MenuItem> productsToOrder = new ArrayList<MenuItem>();
                int orderID = myDeliveryService.getMyOrders().size();
                int clientID = myClient.getClientID();
                LocalDate orderDate = java.time.LocalDate.now();
                LocalTime orderTime = java.time.LocalTime.now();
                Order myOrder = new Order(orderID, clientID, orderDate, orderTime);
                int totalPrice = 0;

                String bill = "Order nr. " + String.valueOf(orderID) + "\n";
                bill = bill +  "Products ordered:\n";
                for(int index : indexProductsToCompose) {
                    productsToOrder.add(productList.get(index));
                    bill = bill + productList.get(index).getTitle() + ":  " + String.valueOf(productList.get(index).computePrice()) + "\n";
                    totalPrice += productList.get(index).computePrice();
                }
                bill = bill + "Total price: " + String.valueOf(totalPrice) + "\n";
                myDeliveryService.notifyEmployee(bill);
                myDeliveryService.placeOrder(myOrder, productsToOrder);
                String reportName = "Order_nr_" + orderID;
                myFileWriter.writeFile(reportName, bill);
            }
            else {
                JOptionPane.showMessageDialog(clientPanel, "Please Select a product to buy!");
            }

            mySerializer.serialize(myDeliveryService);
        });
    }
}
