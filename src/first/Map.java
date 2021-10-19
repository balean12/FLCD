package first;
import java.util.ArrayList;
import java.util.Objects;

public class Map {
    ArrayList<ArrayList<String>> symbolTable = new ArrayList<>(30);
    int numBuckets = 30;
    int size;
    public Map(){
        for(int i=0; i<numBuckets; i++){
            symbolTable.add(new ArrayList<String>(10));
        }
    }
    public void print(){
        symbolTable.forEach(System.out::println);
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private int getBucketIndex(String key){
        int hashCode = key.hashCode();
        return Math.abs(hashCode % numBuckets);
    }

    public int getOverallPosition(String token){
        int index = getBucketIndex(token);
        int lengthOfBuckets = 1;
        for(int i = 0; i<index; i++){
            lengthOfBuckets += symbolTable.get(i).size();
        }
        int positionInBucket = findInBucket(token);
        if(positionInBucket == -1) return -1;
        return positionInBucket + lengthOfBuckets;
    }

    public int findInBucket(String token){
        //can be modified
        int index = getBucketIndex(token);
        var head = symbolTable.get(index);
        for(int i = 0; i< head.size(); i++){
            if(token.equals(head.get(i))) return i;
        }
        return -1;
    }

    public void add(String token)
    {
        int index = getBucketIndex(token);
        if(findInBucket(token) == -1) {
            symbolTable.get(index).add(token);
        }
    }
}
