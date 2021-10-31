package orderManagement.presentation;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import orderManagement.bll.ClientBLL;
import orderManagement.bll.OrderBLL;
import orderManagement.bll.ProductBLL;
import orderManagement.dao.AbstractDAO;
import orderManagement.model.Client;
import orderManagement.model.Order;
import orderManagement.model.Product;


import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * The type Gui.
 */
public class GUI {

    /**
     * The Client bll.
     */
    ClientBLL clientBLL;
    /**
     * The Product bll.
     */
    ProductBLL productBLL;
    /**
     * The Order bll.
     */
    OrderBLL orderBLL;

    /**
     * The Main root.
     */
    BorderPane mainRoot;


    /**
     * The Tab pane.
     */
    TabPane tabPane;
    /**
     * The Clients tab.
     */
    Tab clientsTab;
    /**
     * The Products tab.
     */
    Tab productsTab;
    /**
     * The Orders tab.
     */
    Tab ordersTab;

    /**
     * The Client pane content.
     */
    HBox clientPaneContent;
    /**
     * The Clients controller.
     */
    VBox clientsController;
    /**
     * The Id client h box.
     */
    HBox idClientHBox;
    /**
     * The Id client text.
     */
    TextField idClientText;
    /**
     * The Id client label.
     */
    Label idClientLabel;
    /**
     * The Phone client h box.
     */
    HBox phoneClientHBox;
    /**
     * The Phone client text.
     */
    TextField phoneClientText;
    /**
     * The Phone client label.
     */
    Label phoneClientLabel;
    /**
     * The Firstname client h box.
     */
    HBox firstnameClientHBox;
    /**
     * The Firstname client text.
     */
    TextField firstnameClientText;
    /**
     * The Firstname client label.
     */
    Label firstnameClientLabel;
    /**
     * The Lastname client h box.
     */
    HBox lastnameClientHBox;
    /**
     * The Lastname client text.
     */
    TextField lastnameClientText;
    /**
     * The Lastname client label.
     */
    Label lastnameClientLabel;
    /**
     * The Insert client button.
     */
    Button insertClientButton;
    /**
     * The Update client button.
     */
    Button updateClientButton;
    /**
     * The Delete client button.
     */
    Button deleteClientButton;

    /**
     * The Table clients.
     */
    HashMap<Class<?>, TableView<Object> > myHash = new HashMap<>();

    /**
     * The Product pane content.
     */
    HBox productPaneContent;
    /**
     * The Products controller.
     */
    VBox productsController;
    /**
     * The Id product h box.
     */
    HBox idProductHBox;
    /**
     * The Id product text.
     */
    TextField idProductText;
    /**
     * The Id product label.
     */
    Label idProductLabel;
    /**
     * The Name product h box.
     */
    HBox nameProductHBox;
    /**
     * The Name product text.
     */
    TextField nameProductText;
    /**
     * The Name product label.
     */
    Label nameProductLabel;
    /**
     * The Quantity product h box.
     */
    HBox quantityProductHBox;
    /**
     * The Quantity product text.
     */
    TextField quantityProductText;
    /**
     * The Quantity product label.
     */
    Label quantityProductLabel;
    /**
     * The Unitprice product h box.
     */
    HBox unitpriceProductHBox;
    /**
     * The Unitprice product text.
     */
    TextField unitpriceProductText;
    /**
     * The Unitprice product label.
     */
    Label unitpriceProductLabel;
    /**
     * The Insert product button.
     */
    Button insertProductButton;
    /**
     * The Update product button.
     */
    Button updateProductButton;
    /**
     * The Delete product button.
     */
    Button deleteProductButton;
    /**
     * The Table products.
     */


    /**
     * The Order pane content.
     */
    HBox orderPaneContent;
    /**
     * The Orders controller.
     */
    VBox ordersController;
    /**
     * The Id order h box.
     */
    HBox idOrderHBox;
    /**
     * The Id order text.
     */
    TextField idOrderText;
    /**
     * The Id order label.
     */
    Label idOrderLabel;
    /**
     * The Clientid order h box.
     */
    HBox clientidOrderHBox;
    /**
     * The Clientid order text.
     */
    TextField clientidOrderText;
    /**
     * The Clientid order label.
     */
    Label clientidOrderLabel;
    /**
     * The Productid order h box.
     */
    HBox productidOrderHBox;
    /**
     * The Productid order text.
     */
    TextField productidOrderText;
    /**
     * The Productid order label.
     */
    Label productidOrderLabel;
    /**
     * The Quantity order h box.
     */
    HBox quantityOrderHBox;
    /**
     * The Quantity order text.
     */
    TextField quantityOrderText;
    /**
     * The Quantity order label.
     */
    Label quantityOrderLabel;
    /**
     * The Place order button.
     */
    Button placeOrderButton;
    /**
     * The Table orders.
     */


    /**
     * Load client table.
     *
     * @param myClass the my class
     */
    public void loadMyTable(Class<?> myClass) {
        myHash.get(myClass).getItems().clear();
        myHash.get(myClass).getColumns().clear();
        myHash.get(myClass).setItems(FXCollections.observableArrayList(new AbstractDAO<>(myClass).findAll()));
        for(Field field : myClass.getDeclaredFields()) {
            TableColumn<Object, String> colonita = new TableColumn<>(field.getName());
            colonita.setCellValueFactory(new PropertyValueFactory<Object, String>(field.getName()));
            myHash.get(myClass).getColumns().add(colonita);
        }
    }


    /**
     * Instantiates a new Gui.
     *
     * @param primaryStage the primary stage
     */
    public GUI(Stage primaryStage) {

        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();
        orderBLL = new OrderBLL();

        mainRoot = new BorderPane();
        tabPane = new TabPane();
        clientsTab = new Tab("CLIENTS");
        productsTab = new Tab("PRODUCTS");
        ordersTab = new Tab("ORDERS");

        tabPane.getTabs().add(clientsTab);
        tabPane.getTabs().add(productsTab);
        tabPane.getTabs().add(ordersTab);

        /// Setting up clients tab

        clientPaneContent = new HBox();

        myHash.put(Client.class, new TableView<>());
        myHash.put(Product.class, new TableView<>());
        myHash.put(Order.class, new TableView<>());

        loadMyTable(Client.class);
        loadMyTable(Product.class);
        loadMyTable(Order.class);

        clientsController = new VBox(); // Add, edit, delete
        clientsController.setSpacing(20);
        clientsController.setPrefWidth(205);
        clientsController.setAlignment(Pos.CENTER);

        idClientHBox = new HBox();
        idClientText = new TextField();
        idClientLabel = new Label("ID: ");
        idClientHBox.getChildren().add(idClientLabel);
        idClientHBox.getChildren().add(idClientText);
        idClientHBox.setAlignment(Pos.CENTER);


        phoneClientHBox = new HBox();
        phoneClientText = new TextField();
        phoneClientLabel = new Label("PH: ");
        phoneClientHBox.getChildren().add(phoneClientLabel);
        phoneClientHBox.getChildren().add(phoneClientText);
        phoneClientHBox.setAlignment(Pos.CENTER);


        firstnameClientHBox = new HBox();
        firstnameClientText = new TextField();
        firstnameClientLabel = new Label("FN: ");
        firstnameClientHBox.getChildren().add(firstnameClientLabel);
        firstnameClientHBox.getChildren().add(firstnameClientText);
        firstnameClientHBox.setAlignment(Pos.CENTER);


        lastnameClientHBox = new HBox();
        lastnameClientText = new TextField();
        lastnameClientLabel = new Label("LN: ");
        lastnameClientHBox.getChildren().add(lastnameClientLabel);
        lastnameClientHBox.getChildren().add(lastnameClientText);
        lastnameClientHBox.setAlignment(Pos.CENTER);


        clientsController.getChildren().add(idClientHBox);
        clientsController.getChildren().add(phoneClientHBox);
        clientsController.getChildren().add(firstnameClientHBox);
        clientsController.getChildren().add(lastnameClientHBox);

        insertClientButton = new Button("INSERT");
        updateClientButton = new Button("UPDATE");
        deleteClientButton = new Button("DELETE");

        insertClientButton.setOnAction(e -> {
            int myID = Integer.parseInt(idClientText.getText());
            String myPH = phoneClientText.getText();
            String myFN = firstnameClientText.getText();
            String myLN = lastnameClientText.getText();
            Client myClient = new Client(myID, myPH, myFN, myLN);
            clientBLL.insertClient(myClient);
            loadMyTable(Client.class);
            loadMyTable(Product.class);
            loadMyTable(Order.class);
        });

        updateClientButton.setOnAction(e -> {
            int myID = Integer.parseInt(idClientText.getText());
            Client myClient = clientBLL.findClientById(myID);
            String myPH = phoneClientText.getText();
            String myFN = firstnameClientText.getText();
            String myLN = lastnameClientText.getText();
            if(myClient == null) myClient = new Client(myID, myPH, myFN, myLN);
            else {
                if(myPH.length() > 0) myClient.setPhone(myPH);
                if(myFN.length() > 0) myClient.setFirstname(myFN);
                if(myLN.length() > 0) myClient.setLastname(myLN);
            }
            clientBLL.updateClient(myClient);
            loadMyTable(Client.class);
            loadMyTable(Product.class);
            loadMyTable(Order.class);
        });

        deleteClientButton.setOnAction(e -> {
            int myID = Integer.parseInt(idClientText.getText());
            String myPH = phoneClientText.getText();
            String myFN = firstnameClientText.getText();
            String myLN = lastnameClientText.getText();
            Client myClient = new Client(myID, myPH, myFN, myLN);
            clientBLL.deleteClient(myClient);
            loadMyTable(Client.class);
            loadMyTable(Product.class);
            loadMyTable(Order.class);
        });


        clientsController.getChildren().add(insertClientButton);
        clientsController.getChildren().add(updateClientButton);
        clientsController.getChildren().add(deleteClientButton);


        /// Here add the components in the clients Tab
        clientPaneContent.getChildren().add(clientsController);
        clientPaneContent.getChildren().add(myHash.get(Client.class));
        clientsTab.setContent(clientPaneContent);







        /// Setting up products tab

        productPaneContent = new HBox();


        productsController = new VBox(); // Add, edit, delete
        productsController.setSpacing(20);
        productsController.setPrefWidth(205);
        productsController.setAlignment(Pos.CENTER);


        idProductHBox = new HBox();
        idProductText = new TextField();
        idProductLabel = new Label("ID: ");
        idProductHBox.getChildren().add(idProductLabel);
        idProductHBox.getChildren().add(idProductText);
        idProductHBox.setAlignment(Pos.CENTER);


        nameProductHBox = new HBox();
        nameProductText = new TextField();
        nameProductLabel = new Label("NA: ");
        nameProductHBox.getChildren().add(nameProductLabel);
        nameProductHBox.getChildren().add(nameProductText);
        nameProductHBox.setAlignment(Pos.CENTER);


        quantityProductHBox  = new HBox();
        quantityProductText  = new TextField();
        quantityProductLabel = new Label("QU: ");
        quantityProductHBox.getChildren().add(quantityProductLabel);
        quantityProductHBox.getChildren().add(quantityProductText);
        quantityProductHBox.setAlignment(Pos.CENTER);


        unitpriceProductHBox  = new HBox();
        unitpriceProductText = new TextField();
        unitpriceProductLabel = new Label("UP: ");
        unitpriceProductHBox.getChildren().add(unitpriceProductLabel);
        unitpriceProductHBox.getChildren().add(unitpriceProductText);
        unitpriceProductHBox.setAlignment(Pos.CENTER);


        productsController.getChildren().add(idProductHBox);
        productsController.getChildren().add(nameProductHBox);
        productsController.getChildren().add(quantityProductHBox);
        productsController.getChildren().add(unitpriceProductHBox);

        insertProductButton  = new Button("INSERT");
        updateProductButton  = new Button("UPDATE");
        deleteProductButton  = new Button("DELETE");

        insertProductButton.setOnAction(e -> {
            int myID = Integer.parseInt(idProductText.getText());
            String myNA = nameProductText.getText();
            int myQU = Integer.parseInt(quantityProductText.getText());
            double myUP = Double.parseDouble(unitpriceProductText.getText());
            Product myProduct  = new Product(myID, myNA, myQU, myUP);
            productBLL.insertProduct(myProduct);
            loadMyTable(Client.class);
            loadMyTable(Product.class);
            loadMyTable(Order.class);
        });

        updateProductButton.setOnAction(e -> {
            int myID = 0;
            int myQU = 0;
            double myUP = 0;
            String myNA = nameProductText.getText();
            if(quantityProductText.getText().length() > 0) myQU = Integer.parseInt(quantityProductText.getText());
            if(unitpriceProductText.getText().length() > 0) myUP = Double.parseDouble(unitpriceProductText.getText());
            if(idProductText.getText().length() > 0) myID = Integer.parseInt(idProductText.getText());
            Product myProduct = productBLL.findProductById(myID);
            if(myProduct == null) myProduct = new Product(myID, myNA, myQU, myUP);
            else {
                if(myNA.length() > 0) myProduct.setName(myNA);
                if(quantityProductText.getText().length() > 0) myProduct.setQuantity(myQU);
                if(unitpriceProductText.getText().length() > 0) myProduct.setUnitprice(myUP);
            }
            productBLL.updateProduct(myProduct);
            loadMyTable(Client.class);
            loadMyTable(Product.class);
            loadMyTable(Order.class);
        });

        deleteProductButton.setOnAction(e -> {
            int myID = Integer.parseInt(idProductText.getText());
            String myNA = nameProductText.getText();
            int myQU = Integer.parseInt(quantityProductText.getText());
            double myUP = Double.parseDouble(unitpriceProductText.getText());
            Product myProduct  = new Product(myID, myNA, myQU, myUP);
            productBLL.deleteProduct(myProduct);
            loadMyTable(Client.class);
            loadMyTable(Product.class);
            loadMyTable(Order.class);
        });


        productsController.getChildren().add(insertProductButton);
        productsController.getChildren().add(updateProductButton);
        productsController.getChildren().add(deleteProductButton);


        /// Here add the components in the clients Tab
        productPaneContent.getChildren().add(productsController);
        productPaneContent.getChildren().add(myHash.get(Product.class));
        productsTab.setContent(productPaneContent);











        Button placeOrderButton;


        /// Setting up orders tab

        orderPaneContent = new HBox();




        ordersController = new VBox(); // Add, edit, delete
        ordersController.setSpacing(20);
        ordersController.setPrefWidth(205);
        ordersController.setAlignment(Pos.CENTER);


        idOrderHBox = new HBox();
        idOrderText = new TextField();
        idOrderLabel = new Label("ID: ");
        idOrderHBox.getChildren().add(idOrderLabel);
        idOrderHBox.getChildren().add(idOrderText);
        idOrderHBox.setAlignment(Pos.CENTER);


        clientidOrderHBox = new HBox();
        clientidOrderText = new TextField();
        clientidOrderLabel = new Label("CI: ");
        clientidOrderHBox.getChildren().add(clientidOrderLabel);
        clientidOrderHBox.getChildren().add(clientidOrderText);
        clientidOrderHBox.setAlignment(Pos.CENTER);


        productidOrderHBox = new HBox();
        productidOrderText = new TextField();
        productidOrderLabel = new Label("PI: ");
        productidOrderHBox.getChildren().add(productidOrderLabel);
        productidOrderHBox.getChildren().add(productidOrderText);
        productidOrderHBox.setAlignment(Pos.CENTER);


        quantityOrderHBox = new HBox();
        quantityOrderText = new TextField();
        quantityOrderLabel = new Label("QU: ");
        quantityOrderHBox.getChildren().add(quantityOrderLabel);
        quantityOrderHBox.getChildren().add(quantityOrderText);
        quantityOrderHBox.setAlignment(Pos.CENTER);


        ordersController.getChildren().add(idOrderHBox);
        ordersController.getChildren().add(clientidOrderHBox);
        ordersController.getChildren().add(productidOrderHBox);
        ordersController.getChildren().add(quantityOrderHBox);

        placeOrderButton = new Button("ORDER");


        placeOrderButton.setOnAction(e -> {
            int myID = Integer.parseInt(idOrderText.getText());
            int myCI = Integer.parseInt(clientidOrderText.getText());
            int myPI = Integer.parseInt(productidOrderText.getText());
            int myQU = Integer.parseInt(quantityOrderText.getText());
            Order myOrder = new Order(myID, myCI, myPI, myQU);
            orderBLL.placeOrder(myOrder);
            loadMyTable(Client.class);
            loadMyTable(Product.class);
            loadMyTable(Order.class);
        });


        ordersController.getChildren().add(placeOrderButton);



        /// Here add the components in the orders Tab
        orderPaneContent.getChildren().add(ordersController);
        orderPaneContent.getChildren().add(myHash.get(Order.class));
        ordersTab.setContent(orderPaneContent);



        mainRoot.setCenter(tabPane);
        primaryStage.setScene(new Scene(mainRoot, 800, 500));
        primaryStage.setTitle("Aalia - 'Adrenaline' ");
        primaryStage.show();
    }
}
