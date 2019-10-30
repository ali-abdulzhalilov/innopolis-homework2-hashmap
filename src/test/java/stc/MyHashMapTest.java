package stc;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

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
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
