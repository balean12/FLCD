package FA;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class MenuFA {
    public static FiniteAutomaton fa;

    static {
        try {
            fa = new FiniteAutomaton();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MenuFA() throws FileNotFoundException {
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Menu: \n" +
                           "1. Show set of states\n" +
                           "2. Show alphabet\n" +
                           "3. Show transitions\n" +
                           "4. Show initial state\n" +
                           "5. Show final states\n" +
                           "6. Check if deterministic \n" +
                           "7. Check sequence \n" +
                           "8. Exit");
        while(true) {
            System.out.println("Input choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(fa.getSetOfStates());
                    break;
                case 2:
                    System.out.println(fa.getAlphabet());
                    break;
                case 3:
                    System.out.println(fa.getTransitions());
                    break;
                case 4:
                    System.out.println(fa.getInitialState());
                    break;
                case 5:
                    System.out.println(fa.getSetOfFinalStates());
                    break;
                case 6:
                    System.out.println(checkDeterminism());
                    break;
                case 7:
                    System.out.println(checkSequence("01"));
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Choose a number in the menu!");
                    break;
            }
        }
    }
    public static boolean checkDeterminism(){
        var transitions = fa.getTransitions();
        var alphabet = fa.getAlphabet();
        Set<String> keys = transitions.keySet();
        for(String key: transitions.keySet()) {
            var values = transitions.get(key);
            for(var value : values){
                for(var value2 : values){
                    if(!value.equals(value2)){
                        if(value.charAt(0) == value2.charAt(0)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static String getNextTransition(String current, String sequenceElement) {
        for (var transition: fa.getTransitions().entrySet()) {
            var values = transition.getValue();
            for( String value: values){
                if(transition.getKey().equals(current) && String.valueOf(value.charAt(0)).equals(sequenceElement)) {
                    return String.valueOf(values.iterator().next().charAt(1));
                }
            }
        }
        return "";
    }

    public static boolean checkSequence(String sequence){
        if(!checkDeterminism()) return false;

        var current = fa.getInitialState();

        if(sequence.equals("") && fa.getSetOfFinalStates().contains(current)) {
            return true;
        }

        while(sequence.length()!=0) {
            String sequenceElem = Character.toString(sequence.charAt(0));
            var transition = getNextTransition(current,sequenceElem);
            if(transition.equals(""))
                return false;
            else {
                current = transition;
                sequence = sequence.substring(1);
            }
        }
        return true;
    };
}
