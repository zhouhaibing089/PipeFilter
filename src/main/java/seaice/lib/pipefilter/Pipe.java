package seaice.lib.pipefilter;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Pipe} object maintains all the filters and the data processing.
 *
 * @param <T> the type of data
 * @author zhb
 */
public class Pipe<T> {

    /**
     * The Filter List
     */
    List<Filter<T>> mFilterList;
    /**
     * The to-be-processed data
     */
    T mData;

    protected Pipe() {
        // no public constructor
    }

    /**
     * Get an instance of {@code Pipe}.
     *
     * @param data the to-be-processed data
     * @param <T>  the type of data
     * @return an instance of {@code Pipe}
     */
    public static <T> Pipe<T> in(T data) {
        Pipe<T> pipe = new Pipe<>();
        pipe.mData = data;
        pipe.mFilterList = new ArrayList<>();
        return pipe;
    }

    /**
     * Add the next filter into the pipe
     *
     * @param filter the filter object
     * @return the pipe itself
     */
    public Pipe<T> then(Filter<T> filter) {
        mFilterList.add(filter);
        return this;
    }

    /**
     * Wrap another pipe into self.
     *
     * @param pipe another pipe
     * @return the pipe itself
     */
    public Pipe<T> use(Pipe<T> pipe) {
        mFilterList.addAll(pipe.mFilterList);
        return this;
    }

    /**
     * Transform the data into another kind of data, of course a new {@code Pipe} got returned.
     *
     * @param transform the transform function
     * @param <TO>      the type of transformed data
     * @return another pipe which processing the transformed data.
     */
    public <TO> Pipe<TO> transform(Transform<T, TO> transform) {
        mData = out();
        return Pipe.in(transform.transform(mData));
    }

    /**
     * Get the output from {@code Pipe}
     *
     * @return the output
     */
    public T out() {
        // no filter specified
        if (mFilterList == null) {
            return mData;
        }
        // apply all the filters
        for (Filter<T> filter : mFilterList) {
            mData = filter.filter(mData);
        }
        // yeah, we got the output
        return mData;
    }
}
