package stc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyHashMapTest {

    //read
    //  get
    //  getOrDefault
    //create/update
    //  put
    //  putIfAbsent
    //delete
    //  remove(key)
    //  remove(key, value)
    //contains
    //  containsKey
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
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
