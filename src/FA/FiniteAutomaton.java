package FA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class FiniteAutomaton {
    private Set<String> setOfStates = new HashSet<>();
    private Set<String> alphabet = new HashSet<>();
    private Map<String, HashSet<String>> transitions = new HashMap<>();
    private Set<String> setOfFinalStates = new HashSet<>();
    private String initialState;

    public FiniteAutomaton() throws FileNotFoundException {
        readFiniteAutomatonFromFile("./src/FA.input");
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
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

    public Map<String, HashSet<String>> getTransitions() {
        return transitions;
    }

    public void setTransitions(Map<String, HashSet<String>> transitions) {
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
        Map<String, HashSet<String>> transitions = new HashMap<String, HashSet<String>>();
        for(int i = 0; i< transitionsSize; i++) {
            String[] transition = input.nextLine().split("");
            if (transitions.containsKey(transition[0])) {
                transitions.get(transition[0]).add(transition[1] + transition[2]);
            } else {
                transitions.put(transition[0], new HashSet<String>(Collections.singleton(transition[1] + transition[2])));
            }
        }
        setTransitions(transitions);
        setInitialState(input.nextLine());
        Set<String> finalStates = new HashSet<String>(Arrays.asList(input.nextLine().split(" ")));
        setSetOfFinalStates(finalStates);
    }
}
