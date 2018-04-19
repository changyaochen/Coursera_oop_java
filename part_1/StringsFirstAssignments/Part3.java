
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
    public boolean twoOccurrences(String stringa, String stringb) {
    int firstIdx = stringa.indexOf(stringb);
    if (firstIdx == -1) {
        return false;
    }
    
    int secondIdx = stringa.indexOf(stringb, firstIdx + stringb.length());
    if (secondIdx == -1) {
        return false;
    }
    return true;
    }
    
    public String lastPart(String stringa, String stringb) {
    String res = "";
    int pos = stringb.indexOf(stringa);
    if (pos == -1) {
        res = stringb;
        return res;
    }
    
    res = stringb.substring(pos + stringa.length());
    return res;
    }
    
    public void testing() {
        
    System.out.println(twoOccurrences("banana", "ana"));
    System.out.println(lastPart("an", "banana"));
    System.out.println(lastPart("zoo", "forest"));
    }
    
    public static void main(String args[]) {
    Part3 p3 = new Part3();
    p3.testing();
    }

}
