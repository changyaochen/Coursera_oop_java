
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;
import edu.duke.*;

public class WordsInFiles {
    // word: [files]
    private HashMap<String, ArrayList<String>> counts;
    
    public WordsInFiles() {
        counts = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for (String w : fr.words()) {
            //w = w.trim().toLowerCase();
            if (counts.containsKey(w)) {  // already seen this word
                ArrayList<String> val = counts.get(w);
                if (val.indexOf(f.getName()) == -1) {  // but in other files
                    val.add(f.getName());
                }
                counts.put(w, val);
            }
            else {  // first time seeing this word
                ArrayList<String> val = new ArrayList<String>();
                val.add(f.getName());
                counts.put(w, val);
            }
        }
    }
    
    private void buildWordFileMap() {
        counts.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int maxVal = 0;
        for (String w : counts.keySet()) {
            int val = counts.get(w).size();
            if (val > maxVal) {
                maxVal = val;
            }
        }
        System.out.println("The maximum number of files any word appears in is "
                +maxVal);
        return maxVal;
    }
    
    private ArrayList wordsInNumFiles(int num) {
        ArrayList<String> words = new ArrayList<String>();
        for (String w : counts.keySet()) {
            int val = counts.get(w).size();
            if (val ==  num) {
                words.add(w);
            }
        }
        System.out.println("There are "+words.size()+" words appeared "+num+" times.");
        return words;
    }
    
    private void printFilesIn(String w) {
        ArrayList<String> files = counts.get(w);
        for (String f : files) {
            System.out.println(w+":\t"+f);
        }
    }
    
    public void tester(String testWord) {
        System.out.println("\n\n===\n");
        // first build the hashmap
        buildWordFileMap();
        // find the maxium number of files that contains the same word
        int maxVal = maxNumber();
        // find the words
        ArrayList<String> words = wordsInNumFiles(maxVal);
        words = wordsInNumFiles(4);
        // print out the file names
        for (String w: words) {
            //printFilesIn(w);
        }
        System.out.println(counts.get(testWord));
        
    }
}
