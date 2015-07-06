package seaice.lib.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * A class help to build list objects.
 *
 * @author zhb
 */
public class ListBuilder<E> extends Builder<List<E>> {

    /**
     * The internal representation
     */
    List<E> mData;

    ListBuilder() {
        // package visible constructor
        mData = new ArrayList<>();
    }

    /**
     * Add one element into the list
     *
     * @param elem the element value
     * @return self
     */
    public ListBuilder<E> add(E elem) {
        mData.add(elem);
        return this;
    }

    /**
     * The final list object
     *
     * @return the list object
     */
    @Override
    public List<E> val() {
        return mData;
    }
}
