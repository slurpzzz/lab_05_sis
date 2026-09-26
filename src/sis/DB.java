package sis;

import java.util.Collection;

/**
 * A generic interface used by the two databases in sis.SIS, sis.course.CourseDB and sis.user.UserDB.
 *
 * @param <K> key type
 * @param <V> value type
 *
 * @author RIT CS
 */
public interface DB<K, V> {
    /**
     * Add a value entry to the database <b>in constant time</b>.  The database
     * will determine the key based on the value type.
     *
     * @param value the value to add
     * @return the previous value associated with the key, otherwise null
     */
    V addValue(V value);

    /**
     * Get the value for an associated key <b>in constant time</b>.
     *
     * @param key the key
     * @return the value that is associated with the key, or null if
     * not present
     */
    V getValue(K key);

    /**
     * Indicates whether a key is in the database or not, <b>in constant time</b>.
     *
     * @param key the key to search for
     * @return whether the key is present or not
     */
    boolean hasKey(K key);

    /**
     * Get all the values in the database <b>in linear time</b>.
     *
     * @return all the values
     */
    Collection<V> getAllValues();
}
