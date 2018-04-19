
/**
 * Write a description of Part2 here.
 * 
 * @author Changyao Chen 
 * @version 0.1
 */
public class Part2 {
    public String findSimpleGene(String dna, 
           String startCodon, String stopCodon) {
    String res = "";
    
    int startIdx = dna.indexOf(startCodon);
    int stopIdx = dna.indexOf(stopCodon, startIdx + 3);
    
    if (startIdx == -1 || stopIdx == -1) {  // not valid
        return res;
    }
    
    if ((stopIdx - startIdx) % 3 != 0) {
        return res;
    }
    
    return dna.substring(startIdx, stopIdx + 3);
    }

    public void testGene(String dna, String startCodon, String stopCodon) {
    System.out.println("Test gene is " + dna); 
    String res = findSimpleGene(dna, startCodon, stopCodon);
    System.out.println("Found gene is " + res);
    }
   
    public static void main (String[] args) {
    // tester
    Part2 fa = new Part2();
    fa.testGene("ATGATAA", "ATG", "TAA");
    }
}
