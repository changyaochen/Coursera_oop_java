import edu.duke.*;

public class Part1 {

    public int findStopCodon(String dna, int startIdx, String stopCodon) {
        // start codon is alway "ATG"
        int stopIdx = dna.indexOf(stopCodon, startIdx);
        // System.out.println(stopCodon + " : " + stopIdx);
        while (stopIdx != -1) {
            if ((stopIdx - startIdx) % 3 == 0) {
                return stopIdx;
            }
            stopIdx = dna.indexOf(stopCodon, stopIdx+3);
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

    public StorageResource getAllGenes(String dna) {
        // we will print all the valid dnas
        // find the idx for startCodon "ATG"
        StorageResource sr = new StorageResource();
        String dnaUpper = dna.toUpperCase();
        System.out.println(dnaUpper);
        
        int startIdx = dnaUpper.indexOf("ATG");
        while (startIdx != -1 && startIdx < dnaUpper.length()) {
            //System.out.println("start index " + startIdx);
            int stopIdx = findGene(dnaUpper, startIdx);
            //System.out.println("stop index " + stopIdx);
            if (stopIdx == -1) {
                break;
            }
            //System.out.println(dnaUpper.substring(startIdx, stopIdx));
            sr.add(dnaUpper.substring(startIdx, stopIdx));
            startIdx = dnaUpper.indexOf("ATG", stopIdx + 1);
        }
        // look for "CTG" codon
        int cnt = 0;
        startIdx = dnaUpper.indexOf("CTG");
        while (startIdx != -1) {
            cnt++;
            startIdx = dnaUpper.indexOf("CTG", startIdx+3);
        }
        System.out.println("counts of CTG " + cnt);

        return sr;
    }
    
    public double cgRatio(String dna) {
        String dnaUpper = dna.toUpperCase();
        int cnt = 0;
        int i = 0;
        while (i < dnaUpper.length()) {
            if (dnaUpper.charAt(i) == 'C') {
                cnt++;
            }
            else if (dnaUpper.charAt(i) == 'G') {
                cnt++;
            }
            i++;
        }
        return (float) cnt / dnaUpper.length();
    }
    
    public void processGenes (StorageResource sr) {
        int cnt60 = 0;
        int cntp35 = 0;
        int longestGene = 0;
        for (String s: sr.data()) {
            if (s.length() > 60 ) {
                //System.out.println(s.length() + " : " + s);
                cnt60++;
            }
            if (cgRatio(s) > 0.35) {
                //System.out.println("cgRatio " + cgRatio(s));
                cntp35++;
            }
            if (s.length() > longestGene) {
                longestGene = s.length();
            }
        }
        System.out.println("cnt60: " + cnt60);
        System.out.println("cntp35: " + cntp35);
        System.out.println("Longest gene length: " + longestGene);
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        //dna = "CATGCCCCCCTAAACGATGAAATAG";
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
        System.out.println("size of sr: " + sr.size());
    }
   
    public static void main (String[] args) {
    // tester
    Part1 tester = new Part1();
    System.out.println("Starting tester...");
    tester.testProcessGenes();
    }
}