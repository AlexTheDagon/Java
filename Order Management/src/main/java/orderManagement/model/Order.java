package orderManagement.model;

/**
 * The type Order.
 */
public class Order {
    private int id;
    private int clientid;
    private int productid;
    private int quantity;

    /**
     * Instantiates a new Order.
     */
    public Order () { }

    /**
     * Instantiates a new Order.
     *
     * @param id        the id
     * @param clientid  the clientid
     * @param productid the productid
     * @param quantity  the quantity
     */
    public Order(int id, int clientid, int productid, int quantity) {
        this.id = id;
        this.clientid = clientid;
        this.productid = productid;
        this.quantity = quantity;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets clientid.
     *
     * @return the clientid
     */
    public int getClientid() {
        return clientid;
    }

    /**
     * Sets clientid.
     *
     * @param clientid the clientid
     */
    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    /**
     * Gets productid.
     *
     * @return the productid
     */
    public int getProductid() {
        return productid;
    }

    /**
     * Sets productid.
     *
     * @param productid the productid
     */
    public void setProductid(int productid) {
        this.productid = productid;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientid=" + clientid +
                ", productid=" + productid +
                ", quantity=" + quantity +
                '}';
    }
}
