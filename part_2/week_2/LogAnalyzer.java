
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
     
     public HashMap countVisitsPerIP() {
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         
         for (LogEntry le : records) {
            String ip  = le.getIpAddress();
            if (counts.containsKey(ip)) {
                counts.put(ip, counts.get(ip)+1);
            }
            else {counts.put(ip, 1);}
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
         String maxIp = "";
         int maxVal = 0;
         for (String ip : counts.keySet()) {
            int cnt = counts.get(ip);
            if (cnt > maxVal) {
                maxVal = cnt;
                maxIp = ip;
            }
         }
         
         return maxVal;
     }
     
     public ArrayList iPsMostVisits(HashMap<String, Integer> counts) {
         int maxVal = mostNumberVisitsByIP(counts);
         ArrayList<String> res = new ArrayList<String>();
         
         for (String ip : counts.keySet()) {
             int cnt = counts.get(ip);
             if (cnt == maxVal) {
                 res.add(ip);
             }
         }
         return res;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> res = new HashMap<String, ArrayList<String>>();
         
         for (LogEntry le : records) {
            String date = le.getAccessTime().toString().substring(4, 10);
            if (res.containsKey(date)) {
                ArrayList<String> tmp = res.get(date);
                tmp.add(le.getIpAddress());
                res.put(date, tmp);
            }
            else {
                ArrayList<String> tmp = new ArrayList<String>();
                tmp.add(le.getIpAddress());
                res.put(date, tmp);
            }
         }
         return res;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> res) {
         int maxVal = 0;
         String mostDay = "";
         for (String date: res.keySet()) {
             int tmp = res.get(date).size();
             if (tmp > maxVal) {
                 maxVal = tmp;
                 mostDay = date;
             }
         }
         return mostDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> res, 
                        String date) {
         ArrayList<String> ipOnDay = new ArrayList<String>();
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         
         for (String tmpDate : res.keySet()) {
             if (tmpDate.equals(date)) {
                 ArrayList<String> ips = res.get(tmpDate);
                 for (String ip : ips) {
                     if (counts.containsKey(ip)) {
                         counts.put(ip, counts.get(ip)+1);
                     }
                     else {counts.put(ip, 1);}
                 }
                 
                 ipOnDay = iPsMostVisits(counts);
             }
         }
         return ipOnDay;
     }
}
