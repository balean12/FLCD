package first;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScanningAnalyze {
    LanguageSpecification languageSpecification;
    ProgramInternalForm pif;
    SymbolTable symbolTable;

    public ScanningAnalyze(LanguageSpecification languageSpecification, ProgramInternalForm pif, SymbolTable symbolTable) {
        this.languageSpecification = languageSpecification;
        this.pif = pif;
        this.symbolTable = symbolTable;
    }

    public ProgramInternalForm getPif() {
        return this.pif;
    }

    public LanguageSpecification getLanguageSpecification() {
        return this.languageSpecification;
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public List<String> getInputAsTokenList(String filePath) {
        List<String> tokens = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                List<String> lineTokens = this.splitIntoTokens(scanner.nextLine());
                tokens.addAll(lineTokens);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tokens;
    }

    private List<String> splitIntoTokens(String line) {
        List<String> tokens = new ArrayList<String>();
        String tokenAcummulator= "";

        for (int i = 0; i < line.length(); i++)
        {
            String charAtIndex = String.valueOf(line.charAt(i));
            if (!this.languageSpecification.isWhiteSpace(charAtIndex))
            {
                if (this.languageSpecification.isSeparator(charAtIndex))
                {
                    if (tokenAcummulator.length() != 0)
                    {
                        if (!tokenAcummulator.equals(" "))
                        {
                            if(tokenAcummulator.equals("+0") || tokenAcummulator.equals("-0")){
                                tokenAcummulator = tokenAcummulator.substring(1);
                            }
                            tokens.add(tokenAcummulator);
                            tokenAcummulator = "";
                        }
                    }
                    tokens.add(charAtIndex);
                }
                else if (this.languageSpecification.isPartOfOperator(charAtIndex))
                    {
                        if (tokenAcummulator.length() != 0)
                        {
                            if (!tokenAcummulator.equals(" "))
                            {
                                if(tokenAcummulator.equals("+0") || tokenAcummulator.equals("-0")){
                                    tokenAcummulator = tokenAcummulator.substring(1);
                                }
                                tokens.add(tokenAcummulator);
                                tokenAcummulator = "";
                            }
                        }
                        String operatorToken = this.languageSpecification.getOperatorToken(charAtIndex, line, i + 1);
                        if((operatorToken.equals("+") || operatorToken.equals("-")) && tokens.get(tokens.size() - 1).equals("=")){
                            tokenAcummulator += operatorToken;
                        }else{
                            i += operatorToken.length() - 1;
                            tokens.add(operatorToken);
                        }
                    }
                else if (line.charAt(i) == '"')
                {
                    if (tokenAcummulator.length() != 0)
                    {
                        if (!tokenAcummulator.equals(" "))
                        {
                            if(tokenAcummulator.equals("+0") || tokenAcummulator.equals("-0")){
                                tokenAcummulator = tokenAcummulator.substring(1);
                            }
                            tokens.add(tokenAcummulator);
                            tokenAcummulator = "";
                        }
                    }
                    String stringToken = this.languageSpecification.getStringToken(line, i + 1);
                    i += stringToken.length() - 1;
                    tokens.add(stringToken);
                }
                else
                    {
                        tokenAcummulator += charAtIndex;
                    }
            }
            else if (tokenAcummulator.length() != 0 && tokenAcummulator != " ")
            {
                if(tokenAcummulator.equals("+0") || tokenAcummulator.equals("-0")){
                    tokenAcummulator = tokenAcummulator.substring(1);
                }
                tokens.add(tokenAcummulator);
                tokenAcummulator = "";
            }
        }
        return tokens;
    }

    public void scanningAlgorithm(String filePath) throws Exception {
        int lineIdx = 1;
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            List<String> lineTokens = this.splitIntoTokens(scanner.nextLine());
            if((!lineTokens.get(0).equals("begin") || !lineTokens.get(1).equals("main")) && lineIdx == 1)
                throw new Exception("Lexical error at line " + String.valueOf(lineIdx) );
            for (String token : lineTokens) {
                if (this.languageSpecification.isReservedWord(token) || this.languageSpecification.isOperator(token) || this.languageSpecification.isSeparator(token)) {
                    this.pif.add(token, -1);
                } else if (this.languageSpecification.isIdentifier(token)) {
                    this.symbolTable.add(token);
                    int positionInSymbolTable = this.symbolTable.getOverallPosition(token);
                    this.pif.add(token, positionInSymbolTable);
                } else if (this.languageSpecification.isConstant(token)) {
                    this.symbolTable.add(token);
                    int positionInSymbolTable = this.symbolTable.getOverallPosition(token);
                    this.pif.add(token, positionInSymbolTable);
                } else {
                    throw new Exception("Lexical error at line " + String.valueOf(lineIdx) + " token " + token);
                }
            }
            lineIdx++;
        }
    }
}
