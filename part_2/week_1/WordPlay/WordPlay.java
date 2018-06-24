
/**
 * Write a description of WordPlay here.
 * 
 * @Changyao Chen
 * @0.1
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        char uch = Character.toUpperCase(ch);
        if ("AEIOU".indexOf(uch) == -1) {
            return false;
        }
        return true;
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i=0; i<phrase.length(); i++) {
            if (isVowel(phrase.charAt(i)) == true) {
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i=0; i<phrase.length(); i++) {
            if (Character.toUpperCase(phrase.charAt(i)) 
                == Character.toUpperCase(ch)) {
                if (i%2==0) {  // replace with *
                    sb.setCharAt(i, '*');
                }
                else {
                    sb.setCharAt(i, '+');
                }
            }
        }
        return sb.toString();
    }
    
    public void test() {
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
