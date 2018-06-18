
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    
    public int[] countLetters(String message) {
        int[] freqs = new int[26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        for (int i=0; i<message.length(); i++) {
            //finish the count
            char ch = Character.toLowerCase(message.charAt(i));
            int idx = alphabet.indexOf(ch);
            if (idx != -1) {
                freqs[idx] += 1;
            }
        }
        return freqs;
    }
    
    public int maxIndex(int[] freqs) {
        int maxIdx = 0;
        int maxVal = 0;
        for (int i=0; i<freqs.length; i++) {
            if (freqs[i] > maxVal) {
                maxVal = freqs[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public String decrypt(String encrypted) {        
        int key = getKey(encrypted);
        System.out.println("The key is "+key);
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, 26 - key);
        System.out.println(message);
        
        return message;
    }
    
    public String halfOfString(String input, int start) {
        String res = "";
        start = start%2;
        
        for (int i=start; i<input.length(); i += 2) {
            res += input.charAt(i);
        }
        return res;
    }
    
    public int getKey(String encrypted) {
        int[] freqs = countLetters(encrypted);
        int key = maxIndex(freqs) - 4;  // 4 is the index of the letter 'e'
        if (key<0) {key += 26;}
        
        return key;
    }
    
    public void testDecrypt() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        System.out.println(encrypted);
        decrypt(encrypted);
    }
    
    public void decryptTwoKeys() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        // first half
        String encrypted_1 = halfOfString(encrypted, 0);
        System.out.println(encrypted_1);
        String decrypted_1 = decrypt(encrypted_1);
        // second half
        String encrypted_2 = halfOfString(encrypted, 1);
        System.out.println(encrypted_2);
        String decrypted_2 = decrypt(encrypted_2);
        
        String res = "";
        for (int i=0; i<encrypted.length(); i++) {
            if (i%2 == 0) {
                res += decrypted_1.charAt(i/2);
            }
            else {
                res += decrypted_2.charAt(i/2);
            }
        }
        System.out.println(res);
    }
}
