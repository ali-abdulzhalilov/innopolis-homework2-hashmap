package stc;

public class Entry {
    public Object key;
    public Object value;
    public Entry next;

    public Entry(Object key, Object value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    @Override
    public String toString() {
        return "["+key+":"+value+"]";
    }
}
