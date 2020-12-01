package WomenClothingSalesSystem;

import java.util.Objects;
/**
 * This class implements a Skirt.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 */
public class Skirt extends WomenClothing{
    private double waistCircumference;
    private double hips;
    private String hemline;

    /**
     * Construct a Skirt object
     */
    public Skirt(String type,String name, double price, double length, String size, String color, double waistCircumference, double hips, String hemline) {
        super(type,name, price, length, size, color);
        this.waistCircumference = waistCircumference;
        this.hips = hips;
        this.hemline = hemline;
    }

    /**
     * set the table format of the Skirt
     * @return the formatted String
     */
    @Override
    public String toString() {
        return super.toString() +
                String.format("\n%-30s%s\n%-30s%s\n%-30s%s",
                        WomenClothingSalesSystem.BLUE+"waistCircumference:" ,WomenClothingSalesSystem.NONE + waistCircumference ,
                        WomenClothingSalesSystem.BLUE+"hips:" ,WomenClothingSalesSystem.NONE + hips ,
                        WomenClothingSalesSystem.BLUE+"hemline:" , WomenClothingSalesSystem.NONE +hemline
                );
    }

    /**
     * judge whether a Skirt equals another
     * @param o the Skirt to be judged
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skirt)) return false;
        Skirt skirt = (Skirt) o;
        return Double.compare(skirt.getWaistCircumference(), getWaistCircumference()) == 0 &&
                Double.compare(skirt.getHips(), getHips()) == 0 &&
                getHemline().equals(skirt.getHemline());
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

    public String getHemline() {
        return hemline;
    }

    public void setHemline(String hemline) {
        this.hemline = hemline;
    }

}
