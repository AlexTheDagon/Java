package foodDelivery.model;


import java.io.Serializable;

/**
 * The type Base product.
 */
public class BaseProduct extends MenuItem implements Serializable {

    /**
     * Instantiates a new Base product.
     *
     * @param title    the title
     * @param rating   the rating
     * @param calories the calories
     * @param protein  the protein
     * @param fat      the fat
     * @param sodium   the sodium
     * @param price    the price
     */
    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    @Override
    public String toString() {
        return "BaseProduct{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }

    @Override
    public int computePrice() {
        return price;
    }
}
