package seaice.lib.pipefilter;

/**
 * The {@code Filter} object contains a method to process data.
 *
 * @param <T> the type of data
 * @author zhb
 */
public interface Filter<T> {

    T filter(T data);

}
