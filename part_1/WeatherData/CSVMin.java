
/**
 * Write a description of CSVMin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {
    
    public CSVRecord compareTwoCSVRecods(CSVRecord record1, CSVRecord record2, String field) {
        
        double temp1 = Double.parseDouble(record1.get(field));
        double temp2 = Double.parseDouble(record2.get(field));        
        if (temp1 <= temp2) {
            return record1;
        }
        else {
            return record2;
        }
    }
        
    
    public CSVRecord coldestHourInFile (CSVParser parser) {
        CSVRecord coldestSoFar = null;
        
        for (CSVRecord row : parser) {
            if (coldestSoFar == null) {
                coldestSoFar = row;
            }
            else if (Double.parseDouble(row.get("TemperatureF")) < -1000) {
                continue;
            }
            else {
                coldestSoFar = compareTwoCSVRecods(coldestSoFar, row, "TemperatureF");
            }
        }
        return coldestSoFar;
    }
    
    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        double coldestSoFar = Double.POSITIVE_INFINITY;
        String fileName = null;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            double coldest = Double.parseDouble(coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));
            if (coldest < coldestSoFar) {
                coldestSoFar = coldest;
                fileName = f.getName();
            }
        }
        return fileName;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        
        for (CSVRecord row : parser) {
            if (lowestSoFar == null) {
                lowestSoFar = row;
            }            
            else {
                try { Double.parseDouble(row.get("Humidity")); 
                    lowestSoFar = compareTwoCSVRecods(lowestSoFar, row, "Humidity");
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        return lowestSoFar;
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        double lowestSoFar = Double.POSITIVE_INFINITY;
        CSVRecord record = null;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            double lowest = Double.parseDouble(lowestHumidityInFile(fr.getCSVParser()).get("Humidity"));
            if (lowest < lowestSoFar) {
                lowestSoFar = lowest;
                record = lowestHumidityInFile(fr.getCSVParser());
            }
        }
        return record;
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        
        double sum = 0;
        int count = 0;
        
        for (CSVRecord row : parser) {
            double temp = Double.parseDouble(row.get("TemperatureF"));
            sum = sum + temp;
            count++;
            }       
        return sum / count;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        int count = 0;
        
        for (CSVRecord row : parser) {
            double temp = Double.parseDouble(row.get("TemperatureF"));
            double humidity = Double.parseDouble(row.get("Humidity"));
            if (humidity >= value) {
                sum = sum + temp;
                count++;
                }
            }       
        if (sum > 0) {return sum / count;}
        else {return -999.0;}
    }
    
    // ========== test functions ===========
    public void testFileWithColdestTemperature() {
        // test the fileWithColdestTemperature() function
        String coldestFile = fileWithColdestTemperature();
        System.out.println(coldestFile);
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println(coldest.get("TemperatureF"));
        
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest humidity of " + coldest.get("Humidity") + " at " + coldest.get("DateUTC"));
        
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity is " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double avg = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature is " + avg);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double avg = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (avg != -999) {
            System.out.println("Average temperature is " + avg);
        }
        else {
            System.out.println("No temperatures with that humidity");
        }
    }
}
