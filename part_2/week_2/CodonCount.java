
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CodonCount {
    private HashMap<String, Integer> counts;
    
    public CodonCount() {
        counts = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna) {
        counts.clear();
        System.out.println("start index is "+start);
        while(start+3 < dna.length()) {
            String codon = dna.substring(start, start+3).toUpperCase();
            if (counts.containsKey(codon)) {
                counts.put(codon, counts.get(codon)+1);
            }
            else {  // new codon
                counts.put(codon, 1);
            }
            start += 3;
        }
        System.out.println("Number of unique codons: "+counts.size());
    }
    
    private String getMostCommonCodon() {
        String codon = "";
        int maxCount = 0;
        for (String s : counts.keySet()) {
            if (counts.get(s) > maxCount) {
                codon = s;
                maxCount = counts.get(s);
            }
        }
        System.out.println("The most common codon is "+codon+
                    ", with count of "+maxCount);
        return codon;
    }
    
    private void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between "+start+
                    " and "+end+" inclusive are:");
        for (String s : counts.keySet()) {
            if (counts.get(s) >= start & counts.get(s) <= end) {
                System.out.println(s+"\t"+counts.get(s));
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String contents = fr.asString();
        int[] startIdx = {0, 1, 2}; 
        
        for (int start : startIdx) {
            buildCodonMap(start, contents);
            getMostCommonCodon();
            printCodonCounts(6, 7);
        }
    }
}
