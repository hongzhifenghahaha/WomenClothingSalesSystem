package WomenClothingSalesSystem;
/**
 * This class implements a Pants.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 */
public class Pants extends WomenClothing{
    private double waistCircumference;
    private double hips;
    private double legOpening;

    /**
     * Construct a pants object
     */
    public Pants(String type, String name, double price, double length, String size, String color, double waistCircumference, double hips, double legOpening) {
        super(type,name, price, length, size, color);
        this.waistCircumference = waistCircumference;
        this.hips = hips;
        this.legOpening = legOpening;
    }

    /**
     * set the table format of the Pants
     * @return the formatted String
     */
    @Override
    public String toString() {
        return super.toString() +
                String.format("\n%-30s%s\n%-30s%s\n%-30s%s",
                        WomenClothingSalesSystem.BLUE+"waistCircumference" ,WomenClothingSalesSystem.NONE + waistCircumference ,
                        WomenClothingSalesSystem.BLUE+"hips" ,WomenClothingSalesSystem.NONE + hips ,
                        WomenClothingSalesSystem.BLUE+"legOpening" ,WomenClothingSalesSystem.NONE + legOpening
                );
    }

    /**
     * The followings are some getter and setter
     */

    public double getWaistCircumference() {
        return waistCircumference;
    }

    public void setWaistCircumference(double waistCircumference) {
        this.waistCircumference = waistCircumference;
    }

    public double getHips() {
        return hips;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    public double getLegOpening() {
        return legOpening;
    }

    public void setLegOpening(double legOpening) {
        this.legOpening = legOpening;
    }
}
