
/**
 * Write a description of Part1 here.
 * 
 * @author Changyao Chen 
 * @version 0.1
 */
public class Part1 {
    public String findSimpleGene(String dna) {
    // start codon is "ATG"
    // stop codon is "TAA"
    String res = "";
    
    int startIdx = dna.indexOf("ATG");
    int stopIdx = dna.indexOf("TAA", startIdx + 3);
    
    if (startIdx == -1 || stopIdx == -1) {  // not valid
        return res;
    }
    
    if ((stopIdx - startIdx) % 3 != 0) {
        return res;
    }
    
    return dna.substring(startIdx, stopIdx + 3);
    }

    public void testGene(String dna) {
    System.out.println("Test gene is " + dna); 
    String res = findSimpleGene(dna);
    System.out.println("Found gene is " + res);
    }
   
    public static void main (String[] args) {
    // tester
    Part1 fa = new Part1();
    fa.testGene("AAATGCCCTAACTAGATTAAGAAACC");
    }
}