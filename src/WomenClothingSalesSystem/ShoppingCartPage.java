package WomenClothingSalesSystem;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class implements a ShoppingCartPage.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 * @see ShoppingColumn
 */
public class ShoppingCartPage extends Page {
    private ArrayList<Column> columns;
    private FunctionBar functionBar;

    /**
     * Consturct a ShoppingCartPage object
     */
    public ShoppingCartPage(String name, String backgroundColor) {//, FunctionBar functionBar) {
        super(name, backgroundColor);
        this.columns = new ArrayList<Column>();
    }

    /**
     * get the FunctionBar
     *
     * @return the FunctionBar
     */
    public FunctionBar getFunctionBar() {
        return functionBar;
    }

    /**
     * set the FunctionBar to new value
     *
     * @param functionBar the new value
     */
    public void setFunctionBar(FunctionBar functionBar) {
        this.functionBar = functionBar;
    }

    /**
     * add the shoppingColumn into the columns
     * @param shoppingColumn the shoppingColumn
     */
    public void addShoppingColumn(ShoppingColumn shoppingColumn) {
        this.columns.add(shoppingColumn);
    }

    /**
     * get the shoppingColumn by index
     * @param index the index of the shoppingColumn
     * @return the shoppingColumn
     */
    public ShoppingColumn getShoppingColumn(int index) {
        return ((ShoppingColumn) this.columns.get(index));
    }

    /**
     * release the columns
     */
    public void clearColumns() {
        this.columns = new ArrayList<Column>();
    }

    /**
     * get the size of the columns
     * @return the size
     */
    public int getNumberOfColumns() {
        return this.columns.size();
    }

    /**
     * get the iterator of the columns
     * @return the iterator
     */
    public Iterator<Column> iterator() {
        return this.columns.iterator();
    }
}
