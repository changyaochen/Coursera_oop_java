package GladLib;

import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList> myMap;   
    private ArrayList<String> usedWords;
    private ArrayList<String> usedCategory;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "/Users/changyaochen/Google Drive/MOOC/Coursera OOP Java/part_2/week_2/GladLib/data";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] category = {"adjective", "noun", "color",
            "country", "name", "animal", "timeframe", "verb", "fruit"};
        myMap = new HashMap<String, ArrayList>();
        
        for (String w : category) {
            myMap.put(w, readIt(source+"/"+w+".txt"));
        }
        
        usedWords = new ArrayList<String>();
        usedCategory = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        
        if (usedCategory.indexOf(label) == -1) {usedCategory.add(label);}
        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        else if (myMap.containsKey(label)) {
            return randomFrom(myMap.get(label));
        }
        else {
            return "**UNKNOWN**";
        }
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (usedWords.indexOf(sub) != -1) {
            // this word is used
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedWords.add(sub);
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        usedWords.clear();
        System.out.println("\ntest");
        String story = fromTemplate(dataSourceDirectory+"/madtemplate2.txt");
        printOut(story, 60);
    }
    
    public int totalWordsInMap() {
        int res = 0;
        for (String w: myMap.keySet()) {
            int tmp = myMap.get(w).size();
            res += tmp;
        }
        System.out.println(res);
        return res;
    }
    
    public int totalWordsConsidered() {
        int res = 0;
        // first the special case:
        if (usedCategory.indexOf("noun") != -1 
            & usedCategory.indexOf("color") != -1
            & usedCategory.indexOf("adjective") != -1) {
            res = myMap.get("noun").size() 
                + myMap.get("color").size() 
                + myMap.get("adjective").size();
            return res;
        }
        for (String w : usedCategory) {
            res += myMap.get(w).size();
        }
        return res;
    }
}
