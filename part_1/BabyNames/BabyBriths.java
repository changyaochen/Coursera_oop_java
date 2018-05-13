import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


/**
 * Write a description of BabyBriths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyBriths {
    
    public void printName() {
        FileResource fr = new FileResource();
        for (CSVRecord cr : fr.getCSVParser(false)) {
            System.out.println("Name is " + cr.get(0) +
                               " Gender is " + cr.get(1));
        }
    }
    
    public int getRank(int year, String name, String gender) {
        String filePath = "../us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(filePath);
        
        return getRankFromFr(fr, name, gender);
    }
    
    public int getRankFromFr(FileResource fr, String name, String gender) {
        
        int boyRank = 0;
        int girlRank = 0;
        
        for (CSVRecord cr : fr.getCSVParser(false)) {
            // get name
            String tmpName = cr.get(0);
            
            // get gender and rank
            String tmpGender = cr.get(1);
            if (tmpGender.equals("M")) {boyRank++;}
            else {girlRank++;}
            
            // test name and gender
            if (tmpName.equals(name)) {
                if (tmpGender.equals("M") & gender.equals("M")) {return boyRank;}
                if (tmpGender.equals("F") & gender.equals("F")) {return girlRank;}
            }
        }
        
        return -1;
    }
    
    public String getName(int year, int rank, String gender) {
        
        String filePath = "../us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(filePath);
        
        int boyRank = 0;
        int girlRank = 0;
        
        for (CSVRecord cr : fr.getCSVParser(false)) {
            // get name
            String tmpName = cr.get(0);
            
            // get gender and rank
            String tmpGender = cr.get(1);
            if (tmpGender.equals("M")) {boyRank++;}
            else {girlRank++;}
            
            if (gender.equals("M") & boyRank == rank) {return tmpName;}
            if (gender.equals("F") & girlRank == rank) {return tmpName;}
        }
        
        return "NO NAME";
    }
    
    public String yearOfHighestRank(String name, String gender) {
        
        DirectoryResource dr = new DirectoryResource();
        int highestRank = Integer.MAX_VALUE;
        String yearFile = "NO FOUND";
        
        for (File f : dr.selectedFiles()) {
            System.out.println("Processing " + f.getName());
            
            FileResource fr = new FileResource(f);
            int tmpRank = getRankFromFr(fr, name, gender);
            if ((tmpRank < highestRank) & (tmpRank != -1)) {
                highestRank = tmpRank;
                yearFile = f.getName();
                System.out.println(tmpRank + " " + f.getName());
            }
        }
        
        return yearFile;
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double totalRank = 0;
        double totalCount = 0;
        
        for (File f : dr.selectedFiles()) {
            System.out.println("Processing " + f.getName());
            
            FileResource fr = new FileResource(f);
            int tmpRank = getRankFromFr(fr, name, gender);
            if (tmpRank != -1) {
                totalRank += tmpRank;
                totalCount++;
            }
        }
        
        if (totalCount == 0) {return -1.0;}
        else {
            return totalRank / totalCount;
        }
    }
    
    public String whatIsNameInYear(String name, int year, int newYear, String gender) {
        // firstly, get the rank
        int rank = getRank(year, name, gender);
        if (rank == -1) {return "NO NAME";}
        
        // next, get the name at the rank in newYear
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she/he was born in " + newYear);
        return newName;    
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        
        String filePath = "../us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(filePath);
        
        int boyCount = 0;
        int girlCount = 0;
        
        for (CSVRecord cr : fr.getCSVParser(false)) {
            // get name
            String tmpName = cr.get(0);
            
            // get gender and rank
            String tmpGender = cr.get(1);
            
            if (tmpName.equals(name)) {
                if (gender.equals("M") & tmpGender.equals(gender)) {return boyCount;}
                if (gender.equals("F") & tmpGender.equals(gender)) {return girlCount;}
            }
            
            int tmpCount = Integer.parseInt(cr.get(2));
            if (tmpGender.equals("M")) {boyCount += tmpCount;}
            else {girlCount += tmpCount;}
        }
        
        // the name is not found
        if (gender.equals("M")) {return boyCount;}
        else {return girlCount;}
    }
    
    // ====== below are test methods ======
    
    public void totalBirth(FileResource fr) {
        int totalBirths = 0;
        int totalNames = 0;
        int totalBoys = 0;
        int totalBoyNames = 0;
        int totalGirls = 0;
        int totalGirlNames = 0;
        
        for (CSVRecord cr : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(cr.get(2));
            String gender = cr.get(1);
            
            totalBirths += numBorn;
            if (gender.equals("M")) {totalBoys += numBorn; totalBoyNames++;}
            else {totalGirls += numBorn; totalGirlNames++;}            
        }
        
        System.out.println("Total birth is " + totalBirths);
        System.out.println("Total birth of boys is " + totalBoys);
        System.out.println("Total names of boys is " + totalBoyNames);
        System.out.println("Total birth of girls is " + totalGirls);
        System.out.println("Total birth of girls is " + totalGirlNames);
    }
    
    public void testTotalBirth() {
        FileResource fr = new FileResource();
        totalBirth(fr);
    }
}
