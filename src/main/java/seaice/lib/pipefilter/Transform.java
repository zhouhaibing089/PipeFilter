package seaice.lib.pipefilter;

/**
 * The {@code Transform} object convert data with one type to another type.
 *
 * @author zhb
 */
public interface Transform<From, TO> {

    TO transform(From from);

}
