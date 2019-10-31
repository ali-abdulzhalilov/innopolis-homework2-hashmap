package stc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyHashMapTest {

    //contains
    //  containsValue
    //values?
    //keySet?

    private MyHashMap map;

    @Before
    public void setUp() throws Exception {
        map = new MyHashMap();
    }

    @Test
    public void getNullWithNonexistentKey() {
        assertNull(map.get("wrong_key"));
    }

    @Test
    public void getDefault() {
        String key = "some_key";
        String defaultValue = "some_value";
        assertNull(map.get(key));
        assertEquals(map.getOrDefault(key, defaultValue), defaultValue);
    }

    @Test
    public void containsKey() {
        String key = "some_key";
        String value = "some_value";
        assertFalse(map.containsKey(key));

        map.put(key, value);
        assertTrue(map.containsKey(key));
    }

    @Test
    public void containgValue() {
        String key = "some_key";
        String value = "some_value";
        assertFalse(map.containsValue(value));

        map.put(key, value);
        assertEquals(map.get(key), value);
        assertTrue(map.containsValue(value));

        String other_key = "other_key";
        map.put(other_key, value);
        assertEquals(map.get(key), value);
        assertTrue(map.containsValue(value));

        map.remove(key, value);
        assertNull(map.get(key));
        assertTrue(map.containsValue(value));

        map.remove(other_key, value);
        assertNull(map.get(other_key));
        assertFalse(map.containsValue(value));
    }

    @Test
    public void putIfAbsent() {
        String key = "some_key";
        String value = "some_value";
        assertNull(map.get(key));

        map.putIfAbsent(key, value); // positive putIfAbsent test
        assertEquals(map.get(key), value);

        String new_value = "some_new_value";
        map.putIfAbsent(key, new_value);
        assertEquals(map.get(key), value);
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

    @Test
    public void getSize() {
        assertEquals(map.size(), 0);

        map.put("first", 42);
        assertEquals(map.size(), 1);

        map.put("second", 43);
        assertEquals(map.size(), 2);

        map.put("second", 44);
        assertEquals(map.size(), 2);

        //map.remove("first")
        //assertEquals(map.size(), 1);
    }

    @Test
    public void putAndClear() {
        String key = "some_key";
        int value = 42;
        map.put(key, value);
        assertEquals(map.size(), 1);
        assertEquals(map.get(key), value);

        map.clear();
        assertEquals(map.size(), 0);
        assertNull(map.get(key));
    }

    @Test
    public void removeByKey() {
        String key = "some_key";
        String value = "some_value";
        map.put(key, value);
        assertEquals(map.get(key), value);

        assertEquals(map.remove(key), value);
        assertNull(map.get(key));
    }

    @Test
    public void removeByKeyAndValue() {
        String key = "some_key";
        String value = "some_value";
        String other_value = "other_value";

        map.put(key, value);
        assertNull(map.remove(key, other_value));
        assertEquals(map.get(key), value);

        map.put(key, other_value);
        assertEquals(map.get(key), other_value);
        assertEquals(map.remove(key, other_value), other_value);
        assertNull(map.get(key));
    }
}
