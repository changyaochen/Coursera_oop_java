
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
            String IP = le.getIpAddress();
            if (!uniqueIPs.contains(IP)) {
                uniqueIPs.add(IP);
            }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
            int status = le.getStatusCode();
            if (status > num) {
                System.out.print(le);
            }
         }
     }
     
     public int uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
            String IP = le.getIpAddress();
            String date = le.getAccessTime().toString().substring(4, 10);
            //System.out.println(date);
            if (date.equals(someday) & !uniqueIPs.contains(IP)) {
                uniqueIPs.add(IP);
            }
         }
         return uniqueIPs.size();
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<LogEntry> les = new ArrayList<LogEntry>();
         ArrayList<String> IPs = new ArrayList<String>();
         for (LogEntry le : records) {
            int status = le.getStatusCode();
            String IP = le.getIpAddress();
            if (status >= low & status <= high & !IPs.contains(IP)) {
                les.add(le);
                IPs.add(IP);
            }
         }
         return les.size();
     }
}
