package WomenClothingSalesSystem;

import java.util.Objects;

/**
 * This class implements a WomenClothing.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 */
public class WomenClothing {
    private String name;
    private double price;
    private double length;
    private String size;
    private String color;
    private String type;

    /**
     * Construct a WomenClothing object
     */
    public WomenClothing(String type, String name, double price, double length, String size, String color) {
        this.name = name;
        this.price = price;
        this.length = length;
        this.size = size;
        this.color = color;
        this.type = type;
    }
    /**
     * set the table format of the WomenClothings
     * @return the formatted String
     */
    @Override
    public String toString() {
        return String.format("\n%-30s%s\n%-30s%s\n%-30s%s\n%-30s%s\n%-30s%s\n%-30s%s",
                WomenClothingSalesSystem.BLUE+"name:"  , WomenClothingSalesSystem.NONE + name,
                WomenClothingSalesSystem.BLUE+"price:" , WomenClothingSalesSystem.NONE + price,
                WomenClothingSalesSystem.BLUE+"length:", WomenClothingSalesSystem.NONE + length,
                WomenClothingSalesSystem.BLUE+"size:"  , WomenClothingSalesSystem.NONE + size,
                WomenClothingSalesSystem.BLUE+"color:" , WomenClothingSalesSystem.NONE + color,
                WomenClothingSalesSystem.BLUE+"type:"  , WomenClothingSalesSystem.NONE + type
        );
    }
    /**
     * judge whether a WomenClothing equals another
     * @param o the WomenClothing to be judged
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WomenClothing)) return false;
        WomenClothing that = (WomenClothing) o;
        return Double.compare(that.getPrice(), getPrice()) == 0 &&
                Double.compare(that.getLength(), getLength()) == 0 &&
                getName().equals(that.getName()) &&
                getSize().equals(that.getSize()) &&
                getColor().equals(that.getColor()) &&
                getType().equals(that.getType());
    }

    /**
     * The following are some getter and setter
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

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
