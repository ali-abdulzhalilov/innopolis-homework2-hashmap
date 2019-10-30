package stc;

public class App
{
    public static void main( String[] args )
    {
        MyHashMap map = new MyHashMap();

        for (int i = 0; i < 100; i++) {
            map.put(i, i+1);
        }
        System.out.println(map);
    }
}
