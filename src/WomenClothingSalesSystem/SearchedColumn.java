package WomenClothingSalesSystem;
/**
 * This class implements a SearchColumn.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 */
public class SearchedColumn extends Column {
    private String type;

    /**
     * Construct a SearchedColumn object
     */
    public SearchedColumn(String name, double price, String type) {
        super(name, price);
        this.type = type;
    }

    /**
     * get the format of the table header
     * @return the formatted String
     */
    @Override
    public String toString() {
        return String.format("%11s%15s%15s",super.getName() , super.getPrice() , this.type);
    }

    /**
     * Get the type
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }
}
