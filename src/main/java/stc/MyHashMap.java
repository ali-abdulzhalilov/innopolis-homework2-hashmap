package stc;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.HashMap;

public class MyHashMap {
    private int size;
    private int capacity;
    private float loadFactor;
    private Entry[] entries;
    // TODO: write rebuild after approaching max capacity (size > capacity * loadFactor)

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

    public Object put(Object key, Object value) {
        if (containsKey(key)) {
            Object old_value = entries[hash(key)].value;
            entries[hash(key)].value = value;
            return old_value;
        }
        else {
            Entry lowest = getLowestEntry(hash(key));
            addEntry(key, value, lowest);
            return null;
        }
    }

    public void putIfAbsent(Object key, Object value) {
        if (containsKey(key)) return;

        Entry lowest = getLowestEntry(hash(key));
        addEntry(key, value, lowest);
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

    public Object getOrDefault(Object key, Object defaultValue) {
        Object value = get(key);
        return value == null ? defaultValue : value;
    }

    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    public boolean containsValue(Object value) {
        Entry entry;

        for (int i = 0; i < entries.length; i++) {
            entry = entries[i];

            while (entry != null) {
                if (entry.value.equals(value))
                    return true;
                entry = entry.next;
            }
        }

        return false;
    }

    private void addEntry(Object key, Object value, Entry prev_node) {
        Entry entry = new Entry(key, value);

        if (prev_node == null)
            entries[hash(key)] = entry;
        else
            prev_node.next = entry;

        size++;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < entries.length; i++)
            entries[i] = null;
        size = 0;
    }

    public Object remove(Object key) {
        int hash = hash(key);
        Entry entry = entries[hash];

        if (entry == null) return null;

        Entry prev_entry = null;
        while (entry != null) {
            if (entry.key.equals(key)) {
                Object value = entries[hash].value;

                if (prev_entry != null)
                    prev_entry.next = entry.next;
                else
                    entries[hash] = null;

                size--;
                return value;
            }
            prev_entry = entry;
            entry = entry.next;
        }

        return null;
    }

    public Object remove(Object key, Object value) {
        Object current_value = get(key);

        if (current_value == null) return null;
        if (!current_value.equals(value)) return null;

        return remove(key);
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(super.toString()+" : "+size+'\n');

        for (int i = 0; i < capacity; i++) {
            Entry entry = entries[i];
            if (entry == null) continue;

            s.append(i+": ");
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


