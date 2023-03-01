package org.itstep;

public class Application {
    public static void main(String[] args) {
        // Реализовать интерфейс Map и класс ArrayMap обобщенными
        Map map = new ArrayMap();
        map.put("one", "two");
        map.put(1, "один");
        System.out.println(map.get("one"));
        System.out.println(map.get("two"));
        System.out.println(map.get(1));
    }
}





