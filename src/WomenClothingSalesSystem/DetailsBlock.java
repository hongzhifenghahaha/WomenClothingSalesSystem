package WomenClothingSalesSystem;

import javax.swing.*;
import java.awt.*;

/**
 * This class implements a DetailsBlock.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 * @see WomenClothing
 */
public class DetailsBlock {
    private int choosedQuantity;
    private WomenClothing womenClothings;
    private JCheckBox checkBox;

    /**
     * Construct a DetailsBlock object
     */
    public DetailsBlock(WomenClothing womenClothings) {
        this.choosedQuantity = 0;
        this.womenClothings = womenClothings;
        checkBox = new JCheckBox();
        checkBox.setBackground(Color.WHITE);
    }

    /**
     * The followings are some getter and setter
     */

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBoxBounds(int x,int y,int width,int height){
        checkBox.setBounds(x,y,width,height);
    }

    public int getChoosedQuantity() {
        return choosedQuantity;
    }

    public void setChoosedQuantity(int choosedQuantity) {
        this.choosedQuantity = choosedQuantity;
    }

    public WomenClothing getWomenClothings() {
        return womenClothings;
    }

    public void setWomenClothings(WomenClothing womenClothings) {
        this.womenClothings = womenClothings;
    }
}
