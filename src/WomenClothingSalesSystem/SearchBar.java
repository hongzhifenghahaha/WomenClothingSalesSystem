package WomenClothingSalesSystem;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * This class implements a SearchBar.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 */
public class SearchBar {
    private String searchContent;
    private ArrayList<String> searchHistory;

    /**
     * Construce a SearchBar object
     */
    public SearchBar() {
        this.searchContent = new String();
        this.searchHistory = new ArrayList<String>();
    }

    /**
     * Get the SearchContent
     * @return the searched content
     */
    public String getSearchContent() {
        return searchContent;
    }

    /**
     * Set the searchContent to new value
     * @param searchContent the new searchContent
     */
    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    /**
     * Add the searched content into the search history
     * @param searchContent the searched content
     */
    public void addToSearchHistory(String searchContent){
        if (searchContent.length()<=4 ) return;
        Iterator<String> it = this.searchHistory.iterator();
        while (it.hasNext()){
            if (it.next().equals(searchContent)){
                return;
            }
        }
        this.searchHistory.add(searchContent);
    }

    /**
     * get the iterator of the searchHistory
     * @return the iterator
     */
    public Iterator<String> iterator(){return this.searchHistory.iterator();}
}
