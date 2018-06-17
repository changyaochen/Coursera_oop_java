
/**
 * Write a description of CaesarCipher here.
 * 
 * @Changyao
 * @version 0.1
 */

import edu.duke.*;

public class CaesarCipher {
    
    public String CaesarCipher(String input){
        return input;
    }
    
    public String encrypt(String input, int key) {
        // get the shifted alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted = alphabet.substring(key) + alphabet.substring(0, key);
        StringBuilder sb = new StringBuilder(input);
        
        for (int i=0; i<input.length(); i++) {
            char ch = input.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(ch));
            if (idx != -1) { // a valid char
                char newCh = shifted.charAt(idx);
                if (Character.isUpperCase(ch)) { // input is uppercase
                    sb.setCharAt(i, newCh);
                }
                else {
                    sb.setCharAt(i, Character.toLowerCase(newCh));
                }
            }
        }
        
        return sb.toString();
        
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        String evenIdxString = encrypt(input, key1);
        String oddIdxString  = encrypt(input, key2);
        String res = "";
        for (int i=0; i<input.length(); i++) {
            if (i%2 == 0) {
                res += evenIdxString.charAt(i);
            }
            else {
                res += oddIdxString.charAt(i);
            }
        }
        
        return res;
    }
    
    public void test() {
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }
    
    public void testCaesar(int key) {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
}
