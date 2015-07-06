package seaice.lib.builder;

import java.util.HashMap;
import java.util.Map;

/**
 * A class help to build map objects.
 *
 * @author zhb
 */
public class MapBuilder<K, V> extends Builder<Map<K, V>> {

    /**
     * The internal representation
     */
    Map<K, V> mData;

    MapBuilder() {
        // package visible constructor
        mData = new HashMap<>();
    }

    /**
     * Put a new key value pair into the map.
     *
     * @param key   key value
     * @param value value behind the key
     * @return self.
     */
    public MapBuilder<K, V> put(K key, V value) {
        mData.put(key, value);
        return this;
    }

    @Override
    public Map<K, V> val() {
        return null;
    }
}
