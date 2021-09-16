package WomenClothingSalesSystem;
/**
 * This class implements a DetailsPage.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 * @see DetailsBlock
 */
public class DetailsPage extends Page {
    private FunctionBar functionBars;
    private DetailsBlock detailsBlock;

    /**
     * Construct a DetailsPage object
     */
    public DetailsPage(String name, String backgroundColor){ //, FunctionBar functionBar, DetailsBlock detailsBlock) {
        super(name, backgroundColor);
//        this.detailsBlock = detailsBlock;
        this.functionBars = new FunctionBar();
    }

    /**
     * some getter and setter
     */

    public FunctionBar getFunctionBars() {
        return functionBars;
    }

    public DetailsBlock getDetailsBlock() {
        return detailsBlock;
    }

    public void setDetailsBlock(DetailsBlock detailsBlock) {
        this.detailsBlock = detailsBlock;
    }
}
