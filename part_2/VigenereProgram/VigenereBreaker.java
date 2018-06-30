import java.util.*;
import edu.duke.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class VigenereBreaker {
    private String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i=whichSlice; i<message.length(); i++) {
            if ((i-whichSlice) % totalSlices == 0) {
                sb.append(message.charAt(i));
            }
        }
        return sb.toString();
    }

    private int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i=0; i<klength; i++) {
            CaesarCracker cracker = new CaesarCracker(mostCommon);
            String thisEncrypted = sliceString(encrypted, i, klength);
            int thisKey = cracker.getKey(thisEncrypted);
            key[i] = thisKey;
        }
        return key;
    }
    
    private HashSet readDictionary(String lang) {
        HashSet<String> dict = new HashSet<String>();
        
        FileResource fr = new FileResource("./dictionaries/"+lang);
        for (String line : fr.lines()) {
            line = line.toLowerCase();
            if (!dict.contains(line)) {
                dict.add(line);
            }
        }
        return dict;
    }
    
    private int countWords(String message, HashSet dict) {
        int count = 0;
        for (String word : message.split("\\W+")) {
            word = word.toLowerCase();
            if (dict.contains(word)) {
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet dict, char commonChar) {
        String answer = "";
        int maxCount = 0;
        int klength = 1;
        
        for (int i=1; i<=100; i++) {
            int[] key = tryKeyLength(encrypted, i, commonChar);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int thisCount = countWords(decrypted, dict);
            if (thisCount > maxCount) {
                maxCount = thisCount;
                klength = i;
                answer = decrypted;
            }
            //if (i==38) {
            //    System.out.println("key length is 38, valid counts: "+thisCount);
            //}
        }
        System.out.println("Correct key length is "+klength);
        System.out.println("Valid word count: "+maxCount);
        return answer;
    }
    
    private char mostCommonCharIn(HashSet<String> dict) {
        HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();
        for (String word : dict) {
            for (int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                if (! charCount.keySet().contains(c)) {
                    charCount.put(c, 1);
                }
                else {
                    charCount.put(c, charCount.get(c)+1);
                }
            }
        }
        
        int maxCount = 0;
        char maxChar = 'a';
        for (char a : charCount.keySet()) {
            int tmpCount = charCount.get(a);
            if (tmpCount > maxCount) {
                maxCount = tmpCount;
                maxChar = a;
            }
        }
        
        return maxChar;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet> languages) {
        // try to break for each language
        int maxValidWords = 0;
        String decryptedValid = "";
        for (String lang : languages.keySet()) {
            System.out.println("\n===\nTrying with "+lang);
            HashSet dict = languages.get(lang);
            char mostCommonChar = mostCommonCharIn(dict);
            String decrypted = breakForLanguage(encrypted, dict, mostCommonChar);
            // check for valid words
            int tmpCounts = countWords(decrypted, dict);
            System.out.println("\nTried with "+lang+", valid count is "+tmpCounts);
            if (tmpCounts > maxValidWords) {
                maxValidWords = tmpCounts;
                decryptedValid = decrypted; 
            }
        }
        return decryptedValid;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        // make lang dict
        HashMap<String, HashSet> languages = new HashMap<String, HashSet>();
        String[] allLang = {"Danish", "Dutch", "English", "French", "German",
                            "Italian", "Portuguese", "Spanish"};
        for (String lang : allLang) {
            HashSet dict = readDictionary(lang);
            languages.put(lang, dict);
        }
        // break all!
        String decrypted = breakForAllLangs(encrypted, languages);
        
        // save the decrypted message

        try {
		File file = new File("test.txt");
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(decrypted);
		fileWriter.flush();
		fileWriter.close();
	} catch (IOException e) {
		e.printStackTrace();
	}        
        
    }
    
    public void tester() {
        System.out.println("Testing sliceString");
        String input = "abcdefghijklm";
        System.out.println(input);
        System.out.println(sliceString(input, 0, 3));
        
        // test tryKeyLength
        // System.out.println("Testing tryKeyLength");
        // FileResource fr = new FileResource();
        // String encrypted = fr.asString();
        // int[] key = tryKeyLength(encrypted, 4, 'e');
        // System.out.println(Arrays.toString(key));
        
        // test mostCommonCharIn
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        HashSet dict = readDictionary("English");
        char a = mostCommonCharIn(dict);
        System.out.println("Most common character is "+a);
    }
    
}
