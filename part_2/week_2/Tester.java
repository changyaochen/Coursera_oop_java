
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        System.out.println(la.countUniqueIPs());
    }
    
    public void test() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        
        HashMap counts = la.countVisitsPerIP();
        
        int test = la.mostNumberVisitsByIP(counts);
        System.out.println(test);
        
        ArrayList<String> ips = la.iPsMostVisits(counts);
        System.out.println(ips);
       
        HashMap res = la.iPsForDays();
        System.out.println(res);
        
        String mostDay = la.dayWithMostIPVisits(res);
        System.out.println(mostDay);
        
        ArrayList<String> mostIps = la.iPsWithMostVisitsOnDay(res, "Mar 17");
        System.out.println(mostIps);
    }
}
