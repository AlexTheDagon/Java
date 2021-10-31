package orderManagement.bll;

import orderManagement.dao.AbstractDAO;
import orderManagement.model.Client;
import orderManagement.model.Product;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The type Product business logic.
 */
public class ProductBLL {

    private AbstractDAO<Product> productDAO;


    /**
     * Instantiates a new Product bll.
     */
    public ProductBLL() { productDAO = new AbstractDAO<>(Product.class); }

    /**
     * Check validity boolean.
     *
     * @param myProduct the my product
     * @return the boolean
     */
    public boolean checkValidity(Product myProduct) {
        if(myProduct.getName().length() > 30) return false;
        if(myProduct.getQuantity() < 0) return false;
        if(myProduct.getUnitprice() < 0) return false;
        if(myProduct.getId() < 0) return false;
        return true;
    }

    /**
     * Check availability int.
     *
     * @param name the name
     * @return the int
     */
    public int checkAvailability(String name) { // Return 0 if available or the ID if in the table
        ArrayList<Product> productList = productDAO.findAll();
        if(productList == null) return 0;
        for(Product myProduct : productList) {
            if(myProduct.getName().equals(name)) return myProduct.getId();
        }
        return 0;
    }

    /**
     * Find product by id product.
     *
     * @param id the id
     * @return the product
     */
    public Product findProductById(int id) {
        Product myProduct = productDAO.findById(id);
        if (myProduct == null) {
            System.out.println("Product with id = " + id + " not found!");
        }
        return myProduct;
    }

    /**
     * Insert product.
     *
     * @param myProduct the my product
     */
    public void insertProduct(Product myProduct) {
        if(checkValidity(myProduct) == false) {
            System.out.println("Invalid Product data!");
            return;
        }
        int foundID = checkAvailability(myProduct.getName());
        if(foundID == 0) productDAO.insert(myProduct);
        else System.out.println("Product name already in use!");
    }

    /**
     * Update product.
     *
     * @param myProduct the my product
     */
    public void updateProduct(Product myProduct) {
        if(checkValidity(myProduct) == false) {
            System.out.println("Invalid Product data!");
            return;
        }
        int foundID = checkAvailability(myProduct.getName());
        if(foundID == 0 || foundID == myProduct.getId()) productDAO.update(myProduct);
        else System.out.println("Product name already in use!");
    }

    /**
     * Delete product.
     *
     * @param myProduct the my product
     */
    public void deleteProduct(Product myProduct) { // Delete only by id of the Client
        Product productToDelete = findProductById(myProduct.getId());
        if(productToDelete != null) productDAO.delete(productToDelete);
    }

    /**
     * List my products array list.
     *
     * @return the array list
     */
    public ArrayList<Product> listMyProducts() {
        return productDAO.findAll();
    }
}
