package seaice.lib.builder;

/**
 * A class help to construct objects.
 *
 * @author zhb
 */
public abstract class Builder<T> {

    /**
     * Construct a list of objects which have type of {@code E}
     *
     * @param <E> type of objects in list
     * @return a list builder
     */
    public static <E> ListBuilder<E> list() {
        return new ListBuilder<>();
    }

    /**
     * Construct an array of objects which have type of {@code E}
     *
     * @param <E>        type of objects in array
     * @param typedClass the type of Element
     * @return an array builder
     */
    public static <E> ArrayBuilder<E> array(Class<E> typedClass) {
        return new ArrayBuilder<>(typedClass);
    }

    /**
     * Construct a map objects which has the key type of {@code K} and the value
     * type of {@code V}
     *
     * @param <K> type of key in map
     * @param <V> type of value in map
     * @return a map builder
     */
    public static <K, V> MapBuilder<K, V> map() {
        return new MapBuilder<>();
    }

    /**
     * Every subclass should provide the final object access.
     *
     * @return the built object
     */
    public abstract T val();
}
