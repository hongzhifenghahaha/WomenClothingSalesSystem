package WomenClothingSalesSystem;

/**
 * This class implements a Coat.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 */
public class Coat extends WomenClothing{
    private double shoulderAcross;
    private double bustWidth;
    private double sleeveLength;

    /**
     * Construce a Cost object
     */
    public Coat(String type, String name, double price, double length, String size, String color, double shoulderAcross, double bustWidth, double sleeveLength) {
        super(type,name, price, length, size, color);
        this.shoulderAcross = shoulderAcross;
        this.bustWidth = bustWidth;
        this.sleeveLength = sleeveLength;
    }

    /**
     * set the table format of the Coat
     * @return the formatted String
     */
    @Override
    public String toString() {
        return super.toString() +
                String.format("\n%-30s%s\n%-30s%s\n%-30s%s",
                        WomenClothingSalesSystem.BLUE+"shoulderAcross" ,WomenClothingSalesSystem.NONE + shoulderAcross ,
                        WomenClothingSalesSystem.BLUE+"bustWidth" ,WomenClothingSalesSystem.NONE + bustWidth ,
                        WomenClothingSalesSystem.BLUE+"sleeveLength" ,WomenClothingSalesSystem.NONE + sleeveLength
                );
    }

    /**
     * The followings are some getter and setter
     */

    public double getShoulderAcross() {
        return shoulderAcross;
    }

    public void setShoulderAcross(double shoulderAcross) {
        this.shoulderAcross = shoulderAcross;
    }

    public double getBustWidth() {
        return bustWidth;
    }

    public void setBustWidth(double bustWidth) {
        this.bustWidth = bustWidth;
    }

    public double getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(double sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

}
