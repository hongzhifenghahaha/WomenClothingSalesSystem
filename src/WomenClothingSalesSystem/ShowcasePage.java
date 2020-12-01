package WomenClothingSalesSystem;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * This class implements a ShowcasePage.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 */
public class ShowcasePage extends Page {
    private Column[] columns;

    /**
     * Construct a ShowcasePage object
     */
    public ShowcasePage(String name, String backgroundColor) {
        super(name, backgroundColor);
        columns = new Column[6];
    }

    /**
     * set the showingcolumn with this index to new value
     * @param index the index of showingColumn
     * @param showingColumn the new value
     */
    public void setShowingColumn(int index,ShowingColumn showingColumn){
        this.columns[index] = showingColumn;
    }

    /**
     * get the column by index
     * @param index the index
     * @return the column
     */
    public Column getColumn(int index){
        return this.columns[index];
    }

}
