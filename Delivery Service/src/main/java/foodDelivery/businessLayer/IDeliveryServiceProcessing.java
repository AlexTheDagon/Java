package foodDelivery.businessLayer;

import foodDelivery.model.Client;
import foodDelivery.model.MenuItem;
import foodDelivery.model.Order;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The interface Delivery service processing.
 */
public interface IDeliveryServiceProcessing {

    /**
     * Import csv.
     *
     * @param inputFilePath the input file path
     */
    void importCSV(String inputFilePath);

    /**
     * Validate login string.
     *
     * @param user the user
     * @param pass the pass
     * @return the string
     */
    String validateLogin(String user, String pass);

    /**
     * Register client string.
     *
     * @param user the user
     * @param pass the pass
     * @return the string
     */
    String registerClient(String user, String pass);

    /**
     * Conv item to table format string [ ].
     *
     * @param x the x
     * @return the string [ ]
     */
    String[] convItemToTableFormat(MenuItem x);

    /**
     * Conv to table format array list.
     *
     * @return the array list
     */
    ArrayList<String[]> convToTableFormat();

    /**
     * Gets product list.
     *
     * @return the product list
     */
    ArrayList<MenuItem> getProductList();

    /**
     * Gets my orders.
     *
     * @return the my orders
     */
    HashMap<Order, ArrayList<MenuItem>> getMyOrders();

    /**
     * Remove product by index.
     *
     * @param index the index
     */
    void removeProductByIndex(int index);

    /**
     * Gets product by index.
     *
     * @param index the index
     * @return the product by index
     */
    MenuItem getProductByIndex(int index);

    /**
     * Gets client by username.
     *
     * @param user the user
     * @return the client by username
     */
    Client getClientByUsername(String user);

    /**
     * List prod.
     */
    void listProd();

    /**
     * List users.
     */
    void listUsers();

    /**
     * List orders.
     */
    void listOrders();

    /**
     * Place order.
     *
     * @param myOrder         the my order
     * @param orderedProducts the ordered products
     */
    void placeOrder(Order myOrder, ArrayList<MenuItem> orderedProducts);

    /**
     * Notify employee.
     *
     * @param bill the bill
     */
    void notifyEmployee(String bill);

    /**
     * Compute product list price int.
     *
     * @param myProductList the my product list
     * @return the int
     */
    int computeProductListPrice(ArrayList<MenuItem> myProductList);

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    String getUserById(int id);

    /**
     * Gets report name.
     *
     * @param type the type
     * @return the report name
     */
    String getReportName(int type);


}
