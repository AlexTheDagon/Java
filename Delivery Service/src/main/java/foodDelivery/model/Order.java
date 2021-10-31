package foodDelivery.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


/**
 * The type Order.
 */
public class Order implements Serializable {
    private int orderID;
    private int clientID;
    private LocalDate orderDate;
    private LocalTime orderTime;

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Sets order id.
     *
     * @param orderID the order id
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Sets client id.
     *
     * @param clientID the client id
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * Gets order date.
     *
     * @return the order date
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * Sets order date.
     *
     * @param orderDate the order date
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Gets order time.
     *
     * @return the order time
     */
    public LocalTime getOrderTime() {
        return orderTime;
    }

    /**
     * Sets order time.
     *
     * @param orderTime the order time
     */
    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * Instantiates a new Order.
     *
     * @param orderID   the order id
     * @param clientID  the client id
     * @param orderDate the order date
     * @param orderTime the order time
     */
    public Order(int orderID, int clientID, LocalDate orderDate, LocalTime orderTime) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", clientID=" + clientID +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getOrderID() == order.getOrderID() && getClientID() == order.getClientID() && getOrderDate().equals(order.getOrderDate()) && getOrderTime().equals(order.getOrderTime());
    }


}
