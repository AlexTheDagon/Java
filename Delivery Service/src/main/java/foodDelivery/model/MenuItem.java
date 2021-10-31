package foodDelivery.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Menu item.
 */
public abstract class MenuItem implements Serializable {

    /**
     * The Title.
     */
    protected String title;
    /**
     * The Rating.
     */
    protected double rating;
    /**
     * The Calories.
     */
    protected int calories;
    /**
     * The Protein.
     */
    protected int protein;
    /**
     * The Fat.
     */
    protected int fat;
    /**
     * The Sodium.
     */
    protected int sodium;
    /**
     * The Price.
     */
    protected int price;

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Gets calories.
     *
     * @return the calories
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Sets calories.
     *
     * @param calories the calories
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Gets protein.
     *
     * @return the protein
     */
    public int getProtein() {
        return protein;
    }

    /**
     * Sets protein.
     *
     * @param protein the protein
     */
    public void setProtein(int protein) {
        this.protein = protein;
    }

    /**
     * Gets fat.
     *
     * @return the fat
     */
    public int getFat() {
        return fat;
    }

    /**
     * Sets fat.
     *
     * @param fat the fat
     */
    public void setFat(int fat) {
        this.fat = fat;
    }

    /**
     * Gets sodium.
     *
     * @return the sodium
     */
    public int getSodium() {
        return sodium;
    }

    /**
     * Sets sodium.
     *
     * @param sodium the sodium
     */
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() { return title; }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) { this.title = title; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        return Double.compare(menuItem.getRating(), getRating()) == 0 && getCalories() == menuItem.getCalories() && getProtein() == menuItem.getProtein() && getFat() == menuItem.getFat() && getSodium() == menuItem.getSodium() && price == menuItem.price && getTitle().equals(menuItem.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getRating(), getCalories(), getProtein(), getFat(), getSodium(), price);
    }

    /**
     * Compute price int.
     *
     * @return the int
     */
    public abstract int computePrice() ;
}
