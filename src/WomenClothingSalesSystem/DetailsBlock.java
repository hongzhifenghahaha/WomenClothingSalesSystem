package WomenClothingSalesSystem;

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

    /**
     * Construct a DetailsBlock object
     */
    public DetailsBlock(WomenClothing womenClothings) {
        this.choosedQuantity = 0;
        this.womenClothings = womenClothings;
    }

    /**
     * The followings are some getter and setter
     */

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
