package WomenClothingSalesSystem;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class implements a ClothingDataBase.
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 * @see WomenClothing
 */
public class ClothingDataBase {
    private ArrayList<WomenClothing> womenClothings;

    /**
     * Constructs a ClothingDataBase object
     */
    public ClothingDataBase() {
        this.womenClothings = new ArrayList<WomenClothing>();
    }

    /**
     * add a WomenClothing to the database
     * @param womenClothing
     */
    public void addWomenClothing(WomenClothing womenClothing) {
        this.womenClothings.add(womenClothing);
    }

    /**
     * get a WomenClothing from the database
     * @param index the index of the WomenClothing in the database
     * @return the WomenClothing
     */
    public WomenClothing getWomenClothing(int index) {
        return this.womenClothings.get(index);
    }

    /**
     * get the size of the WomenClothings
     * @return the size of the WomenClothings
     */
    public int getNumberOfWomenClothings() {
        return this.womenClothings.size();
    }

    /**
     * get the Iterator
     * @return the Iterator
     */
    public Iterator<WomenClothing> iterator() {
        return this.womenClothings.iterator();
    }
}
