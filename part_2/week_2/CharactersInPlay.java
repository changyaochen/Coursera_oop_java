
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    
    private ArrayList<String> charNames;
    private ArrayList<Integer> charCounts;
    
    public CharactersInPlay() {
        charNames = new ArrayList<String>();
        charCounts = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        person = person.toLowerCase();
        int idx = charNames.indexOf(person);
        if (idx == -1) {  // new person!
            charNames.add(person);
            charCounts.add(1);
        }
        else {  // seen this person before
            int cnt = charCounts.get(idx);
            charCounts.set(idx, cnt+1);
        }
    }
    
    public void findAllCharacters(boolean verbose) {
        FileResource fr = new FileResource();
        // loop through all lines
        for (String line : fr.lines()) {  
            // loop through all the chars in this line
            for(int i=0; i<line.length(); i++) {
                if (line.charAt(i)=='.') {
                    String person = line.substring(0, i);
                    update(person);
                    break;
                }
            }
        }
        // final printouts
        if (verbose) {
            for (int i=0; i<charNames.size(); i++) {
                System.out.println(charNames.get(i)+" : "+charCounts.get(i));
            }
        }
        return;
    }
    
    public void findMostPart() {
        int idxMax = 0;
        int valMax = 0;
        findAllCharacters(false);
        for (int i=0; i<charNames.size(); i++) {
            if (charCounts.get(i) > valMax) {
                idxMax = i;
                valMax = charCounts.get(i);
            }
        }
        System.out.println("The most spoken part is "+charNames.get(idxMax)
        +", with count of "+charCounts.get(idxMax));
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        findAllCharacters(false);
        for (int i=0; i<charNames.size(); i++) {
            if (charCounts.get(i) >= num1 & charCounts.get(i) <= num2) {
                System.out.println(charNames.get(i)+" : "+charCounts.get(i));
            }
        }
    }
    
    public void tester() {
        System.out.println("\n\n===\n");
        findAllCharacters(false);
    }
}
