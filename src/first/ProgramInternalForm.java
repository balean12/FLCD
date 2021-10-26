package first;

import java.util.AbstractMap;
import java.util.ArrayList;

public class ProgramInternalForm {
    ArrayList<AbstractMap.SimpleEntry<String, Integer>> programInternalForm = new ArrayList<AbstractMap.SimpleEntry<String, Integer>>();

    public ProgramInternalForm() {}

    public void add(String symbol, int position){
        AbstractMap.SimpleEntry<String, Integer> entry = new AbstractMap.SimpleEntry<String, Integer>(symbol,position);
        programInternalForm.add(entry);
    }

    @Override
    public String toString() {
        return "ProgramInternalForm{" +
                "programInternalForm=" + programInternalForm +
                '}';
    }
}
