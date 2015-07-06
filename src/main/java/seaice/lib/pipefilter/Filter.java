package seaice.lib.pipefilter;

import java.util.Map;

/**
 * The {@code Filter} object contains a method to process data.
 *
 * @param <T> the type of data
 * @author zhb
 */
public interface Filter<T> {

    T filter(T data, Map<String, Object> context);

}
