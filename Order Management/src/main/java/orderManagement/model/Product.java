package orderManagement.model;

/**
 * The type Product.
 */
public class Product {
    private int id;
    private String name;
    private int quantity;
    private double unitprice;

    /**
     * Instantiates a new Product.
     */
    public Product() { }

    /**
     * Instantiates a new Product.
     *
     * @param id        the id
     * @param name      the name
     * @param quantity  the quantity
     * @param unitprice the unitprice
     */
    public Product(int id, String name, int quantity, double unitprice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unitprice = unitprice;
    }

    /**
     * Instantiates a new Product.
     *
     * @param name      the name
     * @param quantity  the quantity
     * @param unitprice the unitprice
     */
    public Product(String name, int quantity, double unitprice) {
        this.name = name;
        this.quantity = quantity;
        this.unitprice = unitprice;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * Gets unitprice.
     *
     * @return the unitprice
     */
    public double getUnitprice() {
        return unitprice;
    }

    /**
     * Sets unitprice.
     *
     * @param unitprice the unitprice
     */
    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unitprice=" + unitprice +
                '}';
    }
}
