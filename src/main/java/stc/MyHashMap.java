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
        return hash(key, capacity);
    }

    private int hash(Object key, int capacity) {
        int h = key.hashCode() >>> 1;
        return key == null ? 0 : h  % (capacity-1);
    }

    // create/update
    public Object put(Object key, Object value) {
        Object res = replace(key, value);

        if (res == null)
            addEntry(key, value);

        return res;
    }

    public Object putIfAbsent(Object key, Object value) {
        if (containsKey(key)) return null;

        addEntry(key, value);

        return value;
    }

    public Object replace(Object key, Object value) {
        int hash = hash(key);

        Entry entry = entries[hash];
        while (entry != null) {
            if (entry.key.equals(key) && entry.value != null) {
                Object old_value = entry.value;
                entry.value = value;
                return value;
            }

            entry = entry.next;
        }

        return null;
    }

    public boolean replace(Object key, Object oldValue, Object newValue) {
        int hash = hash(key);

        Entry entry = entries[hash];
        while (entry != null) {
            if (entry.key.equals(key) && entry.value == oldValue) {
                entry.value = newValue;
                return true;
            }

            entry = entry.next;
        }

        return false;
    }

    // read
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

    public int size() {
        return size;
    }

    // delete
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

    public void clear() {
        for (int i = 0; i < entries.length; i++)
            entries[i] = null;
        size = 0;
    }

    // utils
    private void addEntry(Object key, Object value) {
        if (shouldRehash())
            rehash();

        Entry entry = new Entry(key, value);

        size++;

        putEntryInBucket(entry, entries, hash(key));
    }

    private Entry putEntryInBucket(Entry entry, Entry[] bucketList, int index) {
        if (entry == null) return null;

        if (bucketList[index] == null) {
          bucketList[index] = entry;
          return null;
        }

        Entry tmp_entry = bucketList[index];
        while (tmp_entry.next != null)
            tmp_entry = tmp_entry.next;

        tmp_entry.next = entry;
        return tmp_entry;
    }

    private boolean shouldRehash() {
        return size > capacity * loadFactor;
    }

    private void rehash() {
        Entry[] rehashed_entries = new Entry[capacity*2];

        Entry entry;
        Entry new_entry;
        for (int i = 0; i < entries.length; i++) {
            entry = entries[i];

            while (entry != null) {
                int new_hash = hash(entry.key, capacity*2);
                new_entry = new Entry(entry.key, entry.value);
                putEntryInBucket(new_entry, rehashed_entries, new_hash);

                entry = entry.next;
            }
        }

        entries = rehashed_entries;
        capacity *= 2;
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
}


