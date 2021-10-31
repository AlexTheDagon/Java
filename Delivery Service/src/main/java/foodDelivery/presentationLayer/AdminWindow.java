package foodDelivery.presentationLayer;

import foodDelivery.businessLayer.DeliveryService;
import foodDelivery.dataLayer.FileWriter;
import foodDelivery.dataLayer.Serializator;
import foodDelivery.model.BaseProduct;
import foodDelivery.model.CompositeProduct;
import foodDelivery.model.MenuItem;
import foodDelivery.model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * The type Admin window.
 */
public class AdminWindow {

    /**
     * The My delivery service.
     */
    DeliveryService myDeliveryService;
    /**
     * The My serializer.
     */
    Serializator mySerializer;
    /**
     * The My report.
     */
    String myReport;
    /**
     * The My writer.
     */
    FileWriter myWriter;
    private JPanel adminPanel;
    private JTable productTable;
    private JButton importProductsButton;
    private JButton addCompositeProductButton;
    private JButton deleteProductButton;
    private JTextField titleTextField;
    private JTextField ratingTextField;
    private JTextField caloriesTextField;
    private JTextField proteinTextField;
    private JTextField fatTextField;
    private JTextField sodiumTextField;
    private JTextField priceTextField;
    private JLabel titleLabel;
    private JLabel ratingLabel;
    private JLabel caloriesLabel;
    private JLabel proteinLabel;
    private JLabel fatLabel;
    private JLabel sodiumLabel;
    private JLabel priceLabel;
    private JButton insertProductButton;
    private JButton editProductButton;
    private JTextField minHourTextField;
    private JTextField maxHourTextField;
    private JTextField minProductCountTextField;
    private JTextField minNrOrdersTextField;
    private JTextField minOrderPriceTextField;
    private JTextField dayTextField;
    private JButton report1Button;
    private JButton report2Button;
    private JButton report3Button;
    private JButton report4Button;

    /**
     * Gets admin panel.
     *
     * @return the admin panel
     */
    public JPanel getAdminPanel() {
        return adminPanel;
    }


    /**
     * Instantiates a new Admin window.
     *
     * @param myDeliveryServiceParam the my delivery service param
     */
    public AdminWindow(DeliveryService myDeliveryServiceParam) {
        myDeliveryService = myDeliveryServiceParam;
        mySerializer = new Serializator();
        myWriter = new FileWriter();
        if(myDeliveryService.getProductList().size() > 0) {
            DefaultTableModel myModel = (DefaultTableModel) productTable.getModel();
            myModel.setRowCount(0);
            String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
            myModel.setColumnIdentifiers(columnNames);
            for(String[] x : myDeliveryService.convToTableFormat()) {
                myModel.addRow(x);
            }
        }

        importProductsButton.addActionListener(e -> {
            myDeliveryService.importCSV("products.csv");
            DefaultTableModel myModel = (DefaultTableModel) productTable.getModel();
            myModel.setRowCount(0);
            String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
            myModel.setColumnIdentifiers(columnNames);

            for(String[] x : myDeliveryService.convToTableFormat()) {
                myModel.addRow(x);
            }
            mySerializer.serialize(myDeliveryService);
        });

        addCompositeProductButton.addActionListener(e -> {
            int[] indexProductsToCompose = productTable.getSelectedRows();
            CompositeProduct newCompositeProduct = new CompositeProduct();
            for(int index : indexProductsToCompose) {
                newCompositeProduct.addMenuItem(myDeliveryService.getProductByIndex(index));
            }
            newCompositeProduct.setTitle(newCompositeProduct.computeTitle());
            newCompositeProduct.setRating(newCompositeProduct.computeRating());
            newCompositeProduct.setCalories(newCompositeProduct.computeCalories());
            newCompositeProduct.setPrice(newCompositeProduct.computePrice());
            newCompositeProduct.setProtein(newCompositeProduct.computeProtein());
            newCompositeProduct.setFat(newCompositeProduct.computeFat());
            newCompositeProduct.setSodium(newCompositeProduct.computeSodium());

            boolean addItemToList = true;
            for(MenuItem x : myDeliveryService.getProductList()) {
                if(x.getTitle().equals(newCompositeProduct.getTitle())) addItemToList = false;
            }
            if(addItemToList) {
                myDeliveryService.getProductList().add(newCompositeProduct);
                DefaultTableModel myModel = (DefaultTableModel) productTable.getModel();
                myModel.addRow(myDeliveryService.convItemToTableFormat(newCompositeProduct));
            }
            mySerializer.serialize(myDeliveryService);
        });

        deleteProductButton.addActionListener(e -> {
            DefaultTableModel myModel = (DefaultTableModel) productTable.getModel();
            int[] productsToDelete = productTable.getSelectedRows();
            for(int index = productsToDelete.length - 1; index >= 0; --index) {
                myDeliveryService.removeProductByIndex(productsToDelete[index]);
                myModel.removeRow(productsToDelete[index]);
            }
            mySerializer.serialize(myDeliveryService);
        });

        insertProductButton.addActionListener(e -> {
            boolean validProduct = true;
            String title = "";
            double rating = 0;
            int calories = 0;
            int protein = 0;
            int fat = 0;
            int sodium = 0;
            int price = 0;
            try {
                title = titleTextField.getText();
                rating = Double.parseDouble(ratingTextField.getText());
                calories = Integer.valueOf(caloriesTextField.getText());
                protein = Integer.valueOf(proteinTextField.getText());
                fat = Integer.valueOf(fatTextField.getText());
                sodium = Integer.valueOf(sodiumTextField.getText());
                price = Integer.valueOf(priceTextField.getText());
                if(title.length() == 0) validProduct = false;
            }
            catch (Exception ex) {
                validProduct = false;
            }

            if(validProduct) {

                BaseProduct newProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
                myDeliveryService.getProductList().add(newProduct);
                DefaultTableModel myModel = (DefaultTableModel) productTable.getModel();
                myModel.addRow(myDeliveryService.convItemToTableFormat(newProduct));
                JOptionPane.showMessageDialog(adminPanel, "Product Inserted Succesfully");
            }
            else {
                JOptionPane.showMessageDialog(adminPanel, "Product Insertion Failed\nPlease check the inserted data");
            }

            mySerializer.serialize(myDeliveryService);
        });

        editProductButton.addActionListener(e -> {
            String title = "";
            double rating = 0;
            int calories = 0;
            int protein = 0;
            int fat = 0;
            int sodium = 0;
            int price = 0;
            int[] productToEdit = productTable.getSelectedRows();
            if(productToEdit.length == 1) {
                MenuItem myProduct = myDeliveryService.getProductByIndex(productToEdit[0]);

                try {
                    title = titleTextField.getText();
                    if(title.length() != 0) myProduct.setTitle(title);
                } catch (Exception ex) {}
                try {
                    rating = Double.parseDouble(ratingTextField.getText());
                    myProduct.setRating(rating);
                } catch (Exception ex) {}
                try {
                    calories = Integer.valueOf(caloriesTextField.getText());
                    myProduct.setCalories(calories);
                } catch (Exception ex) {}
                try {
                    protein = Integer.valueOf(proteinTextField.getText());
                    myProduct.setProtein(protein);
                } catch (Exception ex) {}
                try {
                    fat = Integer.valueOf(fatTextField.getText());
                    myProduct.setFat(fat);
                } catch (Exception ex) {}
                try {
                    sodium = Integer.valueOf(sodiumTextField.getText());
                    myProduct.setSodium(sodium);
                } catch (Exception ex) {}
                try {
                    price = Integer.valueOf(priceTextField.getText());
                    myProduct.setPrice(price);
                } catch (Exception ex) {}

                DefaultTableModel myModel = (DefaultTableModel) productTable.getModel();
                myModel.setRowCount(0);
                String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                myModel.setColumnIdentifiers(columnNames);
                for(String[] x : myDeliveryService.convToTableFormat()) {
                    myModel.addRow(x);
                }
            }
            mySerializer.serialize(myDeliveryService);
        });

        report1Button.addActionListener(e -> {
            try {
                LocalTime minHour = LocalTime.parse(minHourTextField.getText());
                LocalTime maxHour = LocalTime.parse(maxHourTextField.getText());
                HashMap<Order, ArrayList<MenuItem>> myOrders = myDeliveryService.getMyOrders();
                myReport = "Orders in interval: " + minHour.toString() + " - " + maxHour.toString() + "\n";
                Predicate<HashMap.Entry<Order,ArrayList<MenuItem>>> filteredOrders = order -> (minHour.isBefore(order.getKey().getOrderTime()) && maxHour.isAfter(order.getKey().getOrderTime()));
                myOrders.entrySet().stream().filter(filteredOrders).forEach(data -> {
                    myReport = myReport + data.getKey().toString() + "\n";
                    data.getValue().forEach(product -> {
                        myReport = myReport + "   > " + product.toString() + "\n";
                    });
                });
                String reportName = myDeliveryService.getReportName(1);
                myWriter.writeFile(reportName, myReport);
                //System.out.println(myReport);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(adminPanel, "Please check the input data!\nhh:mm:ss - ss optinal");
            }
            mySerializer.serialize(myDeliveryService);
        });


        report2Button.addActionListener(e -> {
            try {

                int minCount = Integer.valueOf(minProductCountTextField.getText());
                HashMap<Order, ArrayList<MenuItem>> myOrders = myDeliveryService.getMyOrders();
                HashMap<MenuItem, Integer> productCounter = new HashMap<MenuItem, Integer>();
                for (Map.Entry<Order, ArrayList<MenuItem> > items : myOrders.entrySet()) {
                    for(MenuItem x : items.getValue()) {
                        if(productCounter.containsKey(x) == false) productCounter.put(x, 1);
                        else {
                            Integer counter = productCounter.get(x);
                            productCounter.put(x, counter + 1);
                        }
                    }
                }

                myReport = "Products ordered more than " + minCount + " times:\n";
                Predicate<HashMap.Entry<MenuItem, Integer>> filteredProducts = product -> (product.getValue() >= minCount);
                productCounter.entrySet().stream().filter(filteredProducts).forEach(product -> {
                    myReport = myReport + "   > " + product.getKey().getTitle() + "    " + product.getValue() + "\n";
                });
                String reportName = myDeliveryService.getReportName(2);
                myWriter.writeFile(reportName, myReport);
                //System.out.println(myReport);


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(adminPanel, "Please check the input data!");
            }
            mySerializer.serialize(myDeliveryService);
        });

        report3Button.addActionListener(e -> {
            try {
                int minOrderPrice = Integer.valueOf(minOrderPriceTextField.getText());
                int minNrOrders = Integer.valueOf(minNrOrdersTextField.getText());
                HashMap<Order, ArrayList<MenuItem>> myOrders = myDeliveryService.getMyOrders();
                HashMap<Integer, Integer> clientCounter = new HashMap<Integer, Integer>();

                for (Map.Entry<Order, ArrayList<MenuItem> > items : myOrders.entrySet()) {
                    if(myDeliveryService.computeProductListPrice(items.getValue()) >= minOrderPrice) {
                        if(clientCounter.containsKey(items.getKey().getClientID()) == false) clientCounter.put(items.getKey().getClientID(), 1);
                        else {
                            Integer counter = clientCounter.get(items.getKey().getClientID());
                            clientCounter.put(items.getKey().getClientID(), counter + 1);
                        }
                    }
                }

                myReport = "Clients that have more than " + minNrOrders + " orders with prices above " + minOrderPrice + ":\n";
                Predicate<HashMap.Entry<Integer, Integer>> filteredClients = client -> (client.getValue() >= minNrOrders);
                clientCounter.entrySet().stream().filter(filteredClients).forEach(client -> {
                    myReport = myReport + "   > " + myDeliveryService.getUserById(client.getKey()) + "    " + client.getValue() + "\n";
                });
                String reportName = myDeliveryService.getReportName(3);
                myWriter.writeFile(reportName, myReport);
                //System.out.println(myReport);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(adminPanel, "Please check the input data!");
            }

            mySerializer.serialize(myDeliveryService);
        });


        report4Button.addActionListener(e -> {
            try {
                int dayOrdered = Integer.valueOf(dayTextField.getText());
                HashMap<Order, ArrayList<MenuItem>> myOrders = myDeliveryService.getMyOrders();
                HashMap<MenuItem, Integer> productCounter = new HashMap<MenuItem, Integer>();

                Predicate<HashMap.Entry<Order, ArrayList<MenuItem>>> filteredOrders = order -> (order.getKey().getOrderDate().getDayOfMonth() == dayOrdered);
                myOrders.entrySet().stream().filter(filteredOrders).forEach(order -> {
                    for(MenuItem x : order.getValue()) {
                        if(productCounter.containsKey(x) == false) productCounter.put(x, 1);
                        else {
                            Integer counter = productCounter.get(x);
                            productCounter.put(x, counter + 1);
                        }
                    }
                });
                myReport = "Products ordered on day " + dayOrdered + ":\n";
                for (Map.Entry<MenuItem, Integer > product : productCounter.entrySet()) {
                    myReport = myReport + "   > " + product.getKey().getTitle() + "    " + product.getValue() + "\n";
                }
                String reportName = myDeliveryService.getReportName(4);
                myWriter.writeFile(reportName, myReport);
                //System.out.println(myReport);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(adminPanel, "Please check the input data!");
            }
            mySerializer.serialize(myDeliveryService);
        });
    }

}
