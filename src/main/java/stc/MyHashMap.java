package stc;

import com.sun.xml.internal.bind.v2.TODO;

public class MyHashMap {
    private int index = 0;
    private Object[] keys = new Object[20];
    private Object[] values = new Object[20];
    // TODO: ^ make hashMap resizible

    public void put(Object key, Object value) {
        int c = getIndex(key);
        if (c != -1)
            values[c] = value;
        else {
            keys[index] = key;
            values[index] = value;
            index++;
            // TODO: check for approaching max capacity
        }
    }

    private int getIndex(Object key) {
        for (int i = 0; i < index; i++)
            if (keys[i] == key)
                return i;

        return -1;
    }

    // TODO: write functions containsKey and containsValue, just because

    @Override
    public String toString() {
        String res = super.toString() + '\n';
        for (int i = 0; i<index; i++) {
            res += "| " + keys[i] + " : " + values[i] + '\n'; // <-- bad practice, don't do that
        }
        return res;
    }
}
