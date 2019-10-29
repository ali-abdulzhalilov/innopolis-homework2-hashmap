package stc;

public class App 
{
    public static void main( String[] args )
    {
        MyHashMap h = new MyHashMap();
        h.put(1, 2);
        h.put("test", 2f);
        h.put(false, 2l);
        h.put(false, 2e23f);
        h.put("test", 42);
        System.out.println( h );
    }
}
