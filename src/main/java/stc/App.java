package stc;

public class App
{
    public static void main( String[] args )
    {
        MyHashMap map = new MyHashMap();
        System.out.println(map.get("wrong_key"));
    }
}
