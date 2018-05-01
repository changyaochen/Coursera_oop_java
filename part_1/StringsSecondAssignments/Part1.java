
/**
 * Write a description of Part1 here.
 * 
 * @author Changyao Chen 
 * @version 0.1
 */
public class Part1 {

    public int findStopCodon(String dna, int startIdx, String stopCodon) {
        // start codon is alway "ATG"
        int stopIdx = dna.indexOf(stopCodon, startIdx);
        //System.out.println(stopIdx);
        if (stopIdx == -1) {
            return dna.length();
        }
        if ((stopIdx - startIdx)%3 == 0) {
            return stopIdx;
        }
        return dna.length();
    }
    
    public int findGene(String dna, int startIdx){
        // find the first occurance of "TAA"
        int taaIdx = findStopCodon(dna, startIdx, "TAA");
        // find the first occurance of "TAG"
        int tagIdx = findStopCodon(dna, startIdx, "TAG");
        // find the first occurance of "TGA"
        int tgaIdx = findStopCodon(dna, startIdx, "TGA");
        
        // now find the result
        // is any of the stopCodon found?
        if (taaIdx == dna.length() && tagIdx == dna.length() && tgaIdx == dna.length()) {
            return -1;
        }
        // find the earliest stopCodon
        int stopIdx = Math.min(taaIdx, Math.min(tagIdx, tgaIdx));
        
        return stopIdx + 3;
    }
    
    public void testFindGene(String dna) {
        //print the string
        System.out.println(dna);
        // find the idx for startCodon "ATG"
        int startIdx = dna.indexOf("ATG");        
        int stopIdx = findGene(dna, startIdx);
        if (stopIdx == -1) {
            System.out.println("");
        }
        System.out.println(dna.substring(startIdx, stopIdx));
    }

    public void printAllGenes(String dna) {
        // we will print all the valid dnas
        // find the idx for startCodon "ATG"
        System.out.println(dna);
        int startIdx = dna.indexOf("ATG");
        while (startIdx != -1 && startIdx < dna.length()) {
            int stopIdx = findGene(dna, startIdx);
            if (stopIdx == -1) {
                break;
            }
            System.out.println(dna.substring(startIdx, stopIdx));
            startIdx = dna.indexOf("ATG", stopIdx + 1);
        }
    }

    public int countAllGenes(String dna) {
        // we will print all the valid dnas
        int cnt = 0;
        // find the idx for startCodon "ATG"
        System.out.println(dna);
        int startIdx = dna.indexOf("ATG");
        while (startIdx != -1 && startIdx < dna.length()) {
            int stopIdx = findGene(dna, startIdx);
            if (stopIdx == -1) {
                break;
            }
            System.out.println(dna.substring(startIdx, stopIdx));
            startIdx = dna.indexOf("ATG", stopIdx + 1);
            cnt ++;
        }

        return cnt;
    }
   
    public static void main (String[] args) {
    // tester
    Part1 tester = new Part1();
    String dna = "AATGCTAACTAGCTGACTAAT";
    // tester.testFindGene(dna);
    // tester.printAllGenes(dna);
    int cnt = tester.countAllGenes(dna);
    System.out.println(cnt);
    }
}