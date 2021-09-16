package WomenClothingSalesSystem;
/**
 * This class implements a Page.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 * @see SearchPage
 * @see ShowcasePage
 * @see ShoppingCartPage
 * @see DetailsPage
 */
public class Page {
    private String name;
    private String backgroundColor;

    /**
     * Construct a Page object
     */
    public Page(String name, String backgroundColor) {
        this.name = name;
        this.backgroundColor = backgroundColor;
    }

    /**
     * The followings are some getter and setter
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
