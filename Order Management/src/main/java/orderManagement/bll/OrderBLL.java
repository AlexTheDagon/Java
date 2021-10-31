package orderManagement.bll;

import orderManagement.dao.AbstractDAO;
import orderManagement.model.Client;
import orderManagement.model.Order;
import orderManagement.model.Order;
import orderManagement.model.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Order business logic.
 */
public class OrderBLL {
    private AbstractDAO<Order> orderDAO;
    private AbstractDAO<Product> productDAO;
    private AbstractDAO<Client> clientDAO;

    /**
     * Instantiates a new Order bll.
     */
    public OrderBLL () {
        orderDAO = new AbstractDAO<>(Order.class);
        productDAO = new AbstractDAO<>(Product.class);
        clientDAO = new AbstractDAO<>(Client.class);
    }

    /**
     * Check validity boolean.
     *
     * @param myOrder the my order
     * @return the boolean
     */
    public boolean checkValidity(Order myOrder) {
        if(myOrder.getId() < 0) return false;
        if(myOrder.getClientid() <= 0) return false;
        if(myOrder.getProductid() <= 0) return false;
        if(myOrder.getQuantity() <= 0) return false;
        return true;
    }

    /**
     * Find order by id order.
     *
     * @param id the id
     * @return the order
     */
    public Order findOrderById(int id) {
        Order myOrder = orderDAO.findById(id);
        if (myOrder == null) {
            System.out.println("Order with id = " + id + " not found!");
        }
        return myOrder;
    }


    /**
     * Place order int.
     *
     * @param myOrder the my order
     * @return the int
     */
/*
    -3 -> invalid data
    -2 -> no such product or client
    -1 -> not enough items
    0 -> orderPlaced
     */
    public int placeOrder(Order myOrder) {
        if(checkValidity(myOrder) == false) {
            System.out.println("Invalid Order data!");
            return -3;
        }
        Product wantedProduct = productDAO.findById(myOrder.getProductid());
        Client wantedClient = clientDAO.findById(myOrder.getClientid());
        if(wantedProduct == null || wantedClient == null) return -2;
        if(wantedProduct.getQuantity() < myOrder.getQuantity()) return -1;
        int insertedID = orderDAO.insert(myOrder);
        try {
            FileWriter billGatesDivorce = new FileWriter("Order_nr_" + insertedID);
            billGatesDivorce.write("Order nr. " + insertedID + "\n");
            double price = myOrder.getQuantity() * wantedProduct.getUnitprice();
            wantedProduct.setQuantity(wantedProduct.getQuantity() - myOrder.getQuantity());
            productDAO.update(wantedProduct);
            billGatesDivorce.write("Amount to be paid to mighty Microsoft: " + price + "\n");
            billGatesDivorce.write("Have a nice day!\n");
            billGatesDivorce.flush();
            billGatesDivorce.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * List my orders array list.
     *
     * @return the array list
     */
    public ArrayList<Order> listMyOrders() {
        return orderDAO.findAll();
    }
}
