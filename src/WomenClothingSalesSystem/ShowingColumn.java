package WomenClothingSalesSystem;
/**
 * This class implements a ShowingColumn.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 */
public class ShowingColumn extends Column {
    private String status;

    /**
     * Construct a ShowingColumn object
     */
    public ShowingColumn(String name, double price, String status) {
        super(name, price);
        this.status = status;
    }

    /**
     * get the status of the ShowingColumn
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * set the status of the ShowingColumn to new value
     * @param status the new value
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
