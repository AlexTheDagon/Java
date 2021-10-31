package foodDelivery.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Composite product.
 */
public class CompositeProduct extends MenuItem implements Serializable {

    /**
     * The Composite product list.
     */
    ArrayList < MenuItem > compositeProductList;

    /**
     * Add menu item.
     *
     * @param menuItem the menu item
     */
    public void addMenuItem(MenuItem menuItem) {
        compositeProductList.add(menuItem);
    }

    /**
     * Compute title string.
     *
     * @return the string
     */
    public String computeTitle() {
        String totalTitle = new String();
        for(MenuItem x : compositeProductList) {
            totalTitle = totalTitle + " + " + x.getTitle();
        }
        return totalTitle.substring(3);
    }

    /**
     * Compute rating double.
     *
     * @return the double
     */
    public double computeRating() {
        double totalRating = 0;
        for(MenuItem x : compositeProductList) {
            totalRating += x.getRating();
        }
        return totalRating / compositeProductList.size();
    }

    /**
     * Compute calories int.
     *
     * @return the int
     */
    public int computeCalories() {
        int totalCalories = 0;
        for(MenuItem x : compositeProductList) {
            totalCalories += x.getCalories();
        }
        return totalCalories;
    }

    /**
     * Compute protein int.
     *
     * @return the int
     */
    public int computeProtein() {
        int totalProtein = 0;
        for(MenuItem x : compositeProductList) {
            totalProtein += x.getProtein();
        }
        return totalProtein;
    }

    /**
     * Compute fat int.
     *
     * @return the int
     */
    public int computeFat() {
        int totalFat = 0;
        for(MenuItem x : compositeProductList) {
            totalFat += x.getFat();
        }
        return totalFat;
    }

    /**
     * Compute sodium int.
     *
     * @return the int
     */
    public int computeSodium() {
        int totalSodium = 0;
        for(MenuItem x : compositeProductList) {
            totalSodium += x.getSodium();
        }
        return totalSodium;
    }

    @Override
    public int computePrice() {
        int totalPrice = 0;
        for(MenuItem x : compositeProductList) {
            totalPrice += x.computePrice();
        }
        return totalPrice;
    }

    /**
     * Instantiates a new Composite product.
     */
    public CompositeProduct() {
        this.compositeProductList = new ArrayList<MenuItem>();
    }
}
