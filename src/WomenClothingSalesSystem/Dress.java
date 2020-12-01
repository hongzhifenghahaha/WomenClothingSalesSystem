package WomenClothingSalesSystem;

import java.util.Objects;
/**
 * This class implements a Dress.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 */
public class Dress extends WomenClothing {
    private double waistCircumference;
    private double hips;
    private String hemline;
    private double shoulderAcross;
    private double bustWidth;
    private double sleeveLength;

    /**
     * Construct a Dress object
     */
    public Dress(String type, String name, double price, double length, String size, String color, double waistCircumference, double hips, String hemline, double shoulderAcross, double bustWidth, double sleeveLength) {
        super(type,name, price, length, size, color);
        this.waistCircumference = waistCircumference;
        this.hips = hips;
        this.hemline = hemline;
        this.shoulderAcross = shoulderAcross;
        this.bustWidth = bustWidth;
        this.sleeveLength = sleeveLength;
    }

    /**
     * set the table format of the Dress
     * @return the formatted String
     */
    @Override
    public String toString() {
        return super.toString() +
                String.format("\n%-30s%s\n%-30s%s\n%-30s%s\n%-30s%s\n%-30s%s\n%-30s%s",
                        WomenClothingSalesSystem.BLUE+"waistCircumference" , WomenClothingSalesSystem.NONE +waistCircumference ,
                        WomenClothingSalesSystem.BLUE+"hips" , WomenClothingSalesSystem.NONE +hips ,
                        WomenClothingSalesSystem.BLUE+"hemline" , WomenClothingSalesSystem.NONE +hemline ,
                        WomenClothingSalesSystem.BLUE+"shoulderAcross" , WomenClothingSalesSystem.NONE +shoulderAcross ,
                        WomenClothingSalesSystem.BLUE+ "bustWidth" , WomenClothingSalesSystem.NONE +bustWidth ,
                        WomenClothingSalesSystem.BLUE+"sleeveLength" , WomenClothingSalesSystem.NONE +sleeveLength
                );
    }

    /**
     * judge whether a Dress equals another
     * @param o the Dress to be judged
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dress)) return false;
        Dress dress = (Dress) o;
        return Double.compare(dress.getWaistCircumference(), getWaistCircumference()) == 0 &&
                Double.compare(dress.getHips(), getHips()) == 0 &&
                Double.compare(dress.getShoulderAcross(), getShoulderAcross()) == 0 &&
                Double.compare(dress.getBustWidth(), getBustWidth()) == 0 &&
                Double.compare(dress.getSleeveLength(), getSleeveLength()) == 0 &&
                getHemline().equals(dress.getHemline());
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
