package WomenClothingSalesSystem;

/**
 * This class implements a FunctionBar.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 */
public class FunctionBar {
    private String name;

    /**
     * Construct a FunctionBar object
     */
    public FunctionBar() {
    }

    /**
     * Functions for different pages
     */
    public void useFunction(int functionChoice,WomenClothing womenClothing){
    };

    /**
     * Get the name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
