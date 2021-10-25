package first;

import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        Map map=new Map();
        map.add("this");
        map.add("coder");
        map.add("this");
        map.add("add");
        map.add("add1");
        map.add("add2");
        map.add("add3");
        map.add("add4");
        map.add("add5");
        map.add("add6");
        map.add("hi");
        System.out.println(map.getOverallPosition("hi"));
        map.print();
    }
}
