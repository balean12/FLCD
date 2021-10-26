package first;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LanguageSpecification {
    private List<String> separators = new ArrayList<String>();
    private List<String> operators = new ArrayList<String>();
    private List<String> reservedWords = new ArrayList<String>();
    String filePath = "src/first/tokens.in";
    private Map<String, Integer> codes = new HashMap<String, Integer>();

    public LanguageSpecification() {
        readLineByLineJava8();
        encode();
    };

    //Read file content into the string with - Files.lines(Path path, Charset cs)

    public List<String> getSeparators() {
        return separators;
    }

    public List<String> getOperators() {
        return operators;
    }

    public List<String> getReservedWords() {
        return reservedWords;
    }

    public void setSeparators(List<String> separators) {
        this.separators = separators;
    }

    public void setOperators(List<String> operators) {
        this.operators = operators;
    }

    public void setReservedWords(List<String> reservedWords) {
        this.reservedWords = reservedWords;
    }

    public void readLineByLineJava8() {
        try {
            String separatorsFullLine="";
            String operatorsFullLine="";
            String reservedWordsFullLine="";
            FileInputStream fis = new FileInputStream(this.filePath);
            Scanner sc = new Scanner(fis);    //file to be scanned
            //returns true if there is another line to read
            if (sc.hasNextLine()) {
                separatorsFullLine = sc.nextLine();
                operatorsFullLine = sc.nextLine();
                reservedWordsFullLine = sc.nextLine();
            }
            this.setSeparators(Arrays.asList(separatorsFullLine.split(",")));
            this.setOperators(Arrays.asList(operatorsFullLine.split(",")));
            this.setReservedWords(Arrays.asList(reservedWordsFullLine.split(",")));
            sc.close();     //closes the scanner
        } catch (IOException e) {
            e.printStackTrace();
        }
       }
       public Map<String, Integer> getCodes() {
           return this.codes;
       }

       public Integer getCodeForToken(String token){
           return this.codes.get(token);
       }

       public boolean isSeparator(String token) {
           return getSeparators().contains(token);
       };

       public boolean isOperator(String token) {
           return getOperators().contains(token);
       };

       public boolean isWhiteSpace(String token) {
           return token.equals(" ");
       };

       public boolean isReservedWord(String token) {
           return getReservedWords().contains(token);
       };

       public boolean isIdentifier(String token) {
           return Pattern.compile("^[a-zA-Z]+[a-zA-Z0-9]{0,99}$").matcher(token).matches();
       };

       public boolean isConstant(String token) {
           return Pattern.compile("(^0|[+-]?[1-9]+[0-9]*$)|(^'[a-zA-Z0-9 #&^%]'$)|(^\"[a-zA-Z0-9 #&^%]{2,99}\"$)").matcher(token).matches();
       };

       public boolean isPartOfOperator(String token) {
           for(var op : this.operators){
               if(op.indexOf(token) != -1)
                   return true;
           }
           return false;
       };

       public String getStringToken(String line, Integer index) {
           StringBuilder token = new StringBuilder("\"");
           int charsCount = 0;
           while(index < line.length() && line.charAt(index) != '"'){
               token.append(line.charAt(index));
               charsCount++;
               index++;
           }
           token.append("\"");
           return token.toString();
       }

       public String getOperatorToken(String startToken, String line, Integer index){
           StringBuilder token = new StringBuilder(startToken);
           for(int i = index; i< line.length(); i++){
               char charAtIndex = line.charAt(i);
               if(!this.isPartOfOperator(String.valueOf(charAtIndex))){
                   return token.toString();
               }
               token.append(charAtIndex);
           }
           return token.toString();
       }


       public void encode(){
           this.codes.put("identifier", 0);
           this.codes.put("constant", 1);
           int currentCode = 0;

           for (String separator : this.separators) {
               this.codes.put(separator, currentCode);
               currentCode++;
           }
           for (String operator : this.operators) {
               this.codes.put(operator, currentCode);
               currentCode++;
           }
           for (String reservedWords : this.reservedWords) {
               this.codes.put(reservedWords, currentCode);
               currentCode++;
           }
       }
    }

