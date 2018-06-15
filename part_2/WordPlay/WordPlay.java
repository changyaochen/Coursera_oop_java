
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
    
    public void test() {
        System.out.println(isVowel('a'));
    }
}
