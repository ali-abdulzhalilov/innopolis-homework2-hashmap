package stc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyHashMapTest {

    //read
    //  getOrDefault
    //create/update
    //  putIfAbsent
    //delete
    //  remove(key)
    //  remove(key, value)
    //contains
    //  containsValue
    //size
    //clear
    //values?
    //keySet?

    MyHashMap map;

    @Before
    public void setUp() throws Exception {
        map = new MyHashMap();
    }

    @Test
    public void getNullWithNonexistentKey() {
        assertNull(map.get("wrong_key"));
    }

    @Test
    public void putAndRetrieve() {
        int key = 42;
        int value = 24;
        map.put(key, value);
        assertEquals(map.get(key), value);
    }

    @Test
    public void putAndReplace() {
        int key = 42;
        int value = 24;
        map.put(key, value);
        int new_value = 25;
        map.put(key, new_value);
        assertEquals(map.get(key), new_value);
    }
}
