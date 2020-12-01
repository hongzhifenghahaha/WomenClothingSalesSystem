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
public class ShoppingColumn extends Column {
    private boolean choosedStatus;
    private int ChoosedQuantity;

    /**
     * Construct a ShoppingColumn object
     */
    public ShoppingColumn(String name, double price, int choosedQuantity) {
        super(name, price);
        this.choosedStatus = false;
        ChoosedQuantity = choosedQuantity;
    }

    /**
     * Format the header of the table
     * @return the formatted String
     */
    @Override
    public String toString() {
        if (isChoosed()) {
            return (String.format("%17s%21s%12s%12s", WomenClothingSalesSystem.CYAN + "choosed", WomenClothingSalesSystem.NONE +
                    super.getName(), super.getPrice(), ChoosedQuantity));
        } else {
            return (String.format("%18s%19s%12s%12s", WomenClothingSalesSystem.NONE + "no choosed", WomenClothingSalesSystem.NONE +
                    super.getName(), super.getPrice(), ChoosedQuantity));
        }
    }

    /**
     * get the choosed status of the column
     * @return the choosed status
     */
    public boolean isChoosed() {
        return choosedStatus;
    }

    /**
     * set the choosed status to new value
     * @param choosedStatus the new value
     */
    public void setChoosedStatus(boolean choosedStatus) {
        this.choosedStatus = choosedStatus;
    }

    /**
     * get teh choosed quantity
     * @return the choosed quantity
     */
    public int getChoosedQuantity() {
        return ChoosedQuantity;
    }

    /**
     * set the choosed quantity
     * @param choosedQuantity the choosed quantity
     */
    public void setChoosedQuantity(int choosedQuantity) {
        ChoosedQuantity = choosedQuantity;
    }
}
