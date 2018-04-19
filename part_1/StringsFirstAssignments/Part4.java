import edu.duke.*;
import java.io.File;

/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void findYoutube(String url) {
    URLResource file = new  URLResource(url);
    for (String item : file.words()) {
           String itemLower = item.toLowerCase();
           int pos = itemLower.indexOf("youtube.com");
           if (pos != -1) {
            int startIdx = itemLower.lastIndexOf("\"", pos);
            int stopIdx = itemLower.indexOf("\"", pos+1);
            System.out.println(item.substring(startIdx+1, stopIdx));
            }
        }
    }
    
    public static void main(String args[]) {
    Part4 p4 = new Part4();
    String url = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
    p4.findYoutube(url);
    
    }

}
