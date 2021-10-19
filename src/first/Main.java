package first;

import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        Map map=new Map();
        map.add("this");
        map.add("coder");
        map.add("this");
        map.add("hi");
        System.out.println(map.getOverallPosition("this"));
        map.print();
    }
}
