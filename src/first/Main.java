package first;

public class Main {

    public static void main(String[] args) {
        LanguageSpecification languageSpecification =new LanguageSpecification();
        var codes = languageSpecification.getCodes();
        ProgramInternalForm pif = new ProgramInternalForm();
        SymbolTable symbolTable =new SymbolTable();
        ScanningAnalyze scanningAnalyze =new ScanningAnalyze(languageSpecification, pif, symbolTable);
        var tokens = scanningAnalyze.getInputAsTokenList("src/first/input1.txt");
        //for(var token: tokens){
        System.out.println(tokens);

        try{
            scanningAnalyze.scanningAlgorithm("src/first/input1.txt");
            System.out.println("Lexically correct!\n");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(pif.toString());
        for(var c : languageSpecification.getCodes().entrySet()){
            System.out.println(c.getKey() + " : " + c.getValue());
        }
        symbolTable.print();
    }
}
