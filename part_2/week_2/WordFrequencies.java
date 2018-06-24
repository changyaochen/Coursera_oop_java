
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        // clear variables
        myWords.clear();
        myFreqs.clear();
        
        FileResource fr = new FileResource();
        for (String s : fr.words()) {
            s = s.toLowerCase();
            int idx = myWords.indexOf(s);
            if (idx == -1) {  // new word
                myWords.add(s);
                myFreqs.add(1);
            }
            else {  // this is an old word
                int cnt = myFreqs.get(idx);
                myFreqs.set(idx, cnt+1);
            }
        }
        System.out.println("Number of unique words: "+myWords.size());
        
        // // printouts
        // for (int i=0; i<myWords.size(); i++) {
            // System.out.println("The count for word "+myWords.get(i)
            // +" is "+myFreqs.get(i));
        // }
    }
    
    public void findIndexOfMax() {
        int idxMax = 0;
        int valMax = 0;
        
        findUnique();  // run the count first
        for (int i=0; i<myFreqs.size(); i++) {
            if (myFreqs.get(i) > valMax) {
                idxMax = i;
                valMax = myFreqs.get(i);
            }
        }
        System.out.println("The most frequent word is "+myWords.get(idxMax)
        +", with count of "+myFreqs.get(idxMax));
    }
    
    public void tester() {
        System.out.println("\n\n===\n");
        findIndexOfMax();
    }
}


