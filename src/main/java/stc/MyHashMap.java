package stc;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.HashMap;

public class MyHashMap {
    private int size;
    private int capacity;
    private float loadFactor;
    private Entry[] entries;

    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public MyHashMap(int initialCapacity, float loadFactor) {
        this.size = 0;
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;

        this.entries = new Entry[capacity];
    }
    private int hash(Object key) {
        int h = key.hashCode() >>> 1;
        return key == null ? 0 : h  % (capacity-1);
    }

    public void put(Object key, Object value) {
        if (containsKey(key)) {
            entries[hash(key)].value = value;
        }
        else {
            Entry lowest = getLowestEntry(hash(key));
            addEntry(key, value, lowest);
        }
    }

    private Entry getLowestEntry(int hash) {
        Entry entry = entries[hash];
        Entry prev_entry = null;

        if (entry == null) return null;

        while (entry != null) {
            prev_entry = entry;
            entry = entry.next;
        }

        return prev_entry;
    }

    public Object get(Object key) {
        int hash = hash(key);
        Entry entry = entries[hash];

        if (entry == null)
            return null;

        while (entry != null) {
            if (entry.key.equals(key))
                return entry.value;
            entry = entry.next;
        }

        return null;
    }

    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    private void addEntry(Object key, Object value, Entry prev_node) {
        Entry entry = new Entry(key, value);

        if (prev_node == null)
            entries[hash(key)] = entry;
        else
            prev_node.next = entry;

        size++;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(super.toString()+" : "+size+'\n');

        for (int i = 0; i < capacity; i++) {
            Entry entry = entries[i];
            if (entry == null) continue;

            s.append("|{"+i+"} ");
            while (entry != null) {
                s.append(entry+" ");
                entry = entry.next;
            }

            s.append('\n');
        }

        return s.toString();
    }

    //hash
    //put
    //  add
    //  replace
    //get
}


