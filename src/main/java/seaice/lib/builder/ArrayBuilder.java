package seaice.lib.builder;

import java.lang.reflect.Array;

/**
 * A class help to build arrays.
 *
 * @author zhb
 */
public class ArrayBuilder<E> extends Builder<E[]> {
    /**
     * The internal representation
     */
    E[] mData;
    /**
     * Since array can not be generically, so the class type should provided
     */
    Class<E> mTypedClass;
    /**
     * The next position when use after method
     */
    int mNext;

    ArrayBuilder(Class<E> typedClass) {
        // package visible constructor.
        mNext = 0;
        mTypedClass = typedClass;
    }

    /**
     * Specify the size of array
     *
     * @param size the size
     * @return self
     */
    @SuppressWarnings(value = "unchecked")
    public ArrayBuilder<E> size(int size) {
        mData = (E[]) Array.newInstance(mTypedClass, size);
        return this;
    }

    /**
     * Construct the array from another array
     *
     * @param source the source to be copied
     * @return self
     */
    @SuppressWarnings(value = "unchecked")
    public ArrayBuilder<E> from(E[] source) {
        mData = (E[]) Array.newInstance(mTypedClass, source.length);
        return copy(source, 0);
    }

    /**
     * Copy the {@code source} array and insert it from {@code insertIndex}
     *
     * @param source      the source to be copied
     * @param insertIndex the insert position
     * @return self
     */
    public ArrayBuilder<E> copy(E[] source, int insertIndex) {
        System.arraycopy(source, 0, mData, insertIndex, source.length);
        mNext = source.length;
        return this;
    }

    /**
     * Set the value at {@code index} to be {@code val}
     *
     * @param index the index in array
     * @param val   the new value
     * @return self.
     */
    public ArrayBuilder<E> at(int index, E val) {
        mData[index] = val;
        mNext = index + 1;
        return this;
    }

    /**
     * Set the new value at the first position which has the value {@code null}
     *
     * @param val the new value
     * @return self
     */
    public ArrayBuilder<E> after(E val) {
        mData[mNext++] = val;
        return this;
    }

    /**
     * Yeah, we got the array
     *
     * @return the built array
     */
    @Override
    public E[] val() {
        return mData;
    }
}
