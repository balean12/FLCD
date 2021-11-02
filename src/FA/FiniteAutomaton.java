package FA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class FiniteAutomaton {
    private Set<String> setOfStates = new HashSet<>();
    private Set<String> alphabet = new HashSet<>();
    private Map<String, ArrayList<String>> transitions = new HashMap<>();
    private Set<String> setOfFinalStates = new HashSet<>();

    public FiniteAutomaton() {
    }

    public Set<String> getSetOfStates() {
        return setOfStates;
    }

    public void setSetOfStates(Set<String> setOfStates) {
        this.setOfStates = setOfStates;
    }

    public Set<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(Set<String> alphabet) {
        this.alphabet = alphabet;
    }

    public Map<String, ArrayList<String>> getTransitions() {
        return transitions;
    }

    public void setTransitions(Map<String, ArrayList<String>> transitions) {
        this.transitions = transitions;
    }

    public Set<String> getSetOfFinalStates() {
        return setOfFinalStates;
    }

    public void setSetOfFinalStates(Set<String> setOfFinalStates) {
        this.setOfFinalStates = setOfFinalStates;
    }

    public void readFiniteAutomatonFromFile(String filePath) throws FileNotFoundException {
        Scanner input = new Scanner(new File(filePath));
        Set<String> setOfStates = new HashSet<String>(Arrays.asList(input.nextLine().split(" ")));
        this.setSetOfStates(setOfStates);
        Set<String> alphabet = new HashSet<String>(Arrays.asList(input.nextLine().split(" ")));
        this.setAlphabet(alphabet);
        int transitionsSize = parseInt(input.nextLine());
        Map<String, ArrayList<String>> transitions = new HashMap<String, ArrayList<String>>();
        while( transitionsSize > 0){
            Set<String> transition = new HashSet<String>(Arrays.asList(input.nextLine().split(" ")));
            Iterator<String> it = transition.iterator();
            String key = it.next();
            ArrayList<String> values = new ArrayList<String>();
            while(it.hasNext()){
                String value = it.next();
                values.add(value);
            }
            transitions.put(key,values);
        }

    }

}
