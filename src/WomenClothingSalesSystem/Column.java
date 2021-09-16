package WomenClothingSalesSystem;

/**
 * This class implements a Column.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 * @see ShoppingColumn
 * @see SearchedColumn
 * @see ShowingColumn
 */
public class Column {
    private String name;
    private double price;

    /**
     * Construce a Column object
     */
    public Column(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * The followings are some getter and setters
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
