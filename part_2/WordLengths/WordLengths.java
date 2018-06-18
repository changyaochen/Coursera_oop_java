
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public int[] countWordLengths(FileResource resource, int[] counts) {
        int maxLength = counts.length + 1;
        int[] res = new int[counts.length];
        
        for (String word : resource.words()) {
            
            if (Character.isLetter(word.charAt(0))) {
                System.out.println(word);
                int wordLen = word.length();
                if (wordLen < maxLength) {
                    res[wordLen] += 1;
                }
                else {res[maxLength] += 1;}
            }
        }
        
        for (int i=0; i<res.length; i++) {
            System.out.println("Length of "+(i+1)+": "+res[i]);
        }
        
        return res;
    }
    
    public int indexOfMax(int[] values) {
        int maxIdx = 0;
        int maxVal = 0;
        
        for (int i=0; i<values.length; i++) {
            if (values[i] > maxVal) {
                maxIdx = i;
                maxVal = values[i];
            }
        }
        return maxIdx;
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        int[] res = countWordLengths(fr, counts);
        System.out.println("Max length is "+(1+indexOfMax(res)));

    }
}
