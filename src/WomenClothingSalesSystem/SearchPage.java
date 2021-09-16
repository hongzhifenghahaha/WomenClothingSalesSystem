package WomenClothingSalesSystem;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class implements a SearchPage.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 * @see SearchedColumn
 * @see SearchBar
 */
public class SearchPage extends Page {
    private SearchBar searchBar;
    private ArrayList<Column> columns;

    /**
     * Construct a SearchPage object
     */
    public SearchPage(String name, String backgroundColor) {
        super(name, backgroundColor);
        this.columns = new ArrayList<Column>();
    }

    /**
     * get the SearchBar
     *
     * @return the SearchBar
     */
    public SearchBar getSearchBar() {
        return searchBar;
    }

    /**
     * set the SearchBar to new value
     *
     * @param searchBar the new value
     */
    public void setSearchBar(SearchBar searchBar) {
        this.searchBar = searchBar;
    }

    /**
     * add a new searchedColumn to the columns
     * @param searchedColumn the new searchedColumn
     */
    public void addSearchedColumn(SearchedColumn searchedColumn) {
        this.columns.add(searchedColumn);
    }

    /**
     * release the columns
     */
    public void clearColumns() {
        this.columns = new ArrayList<Column>();
    }

    /**
     * get the iterator of the columns
     * @return the iterator
     */
    public Iterator<Column> iterator() {
        return this.columns.iterator();
    }
}
