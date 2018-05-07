/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    
    public void countryInfo (CSVParser parser, String country) {

        for (CSVRecord record : parser) {
            String cnty = record.get("Country");
            //System.out.println(cnty);
            // check whether the country of interest is in the list
            if (country.toUpperCase().equals(cnty.toUpperCase())) {
                String export = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.println(cnty + ": " + export + ": " + value);
                return;
            }
        }
        // not found!
        System.out.println("NOT FOUND");
        return;
    }
    
    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.toUpperCase().contains(exportItem1.toUpperCase()) && 
                export.toUpperCase().contains(exportItem2.toUpperCase())) {
                String cnty = record.get("Country");
                System.out.println(cnty + ": " + export);
            }
        }
    }
    
    public void numberOfExporters (CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.toUpperCase().contains(exportItem.toUpperCase())) {
                count++;
            }
        }
        System.out.println(count);
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                String cnty = record.get("Country");
                System.out.println(cnty + ": " + value);
            }
        }
    }
    
    public void tester () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //countryInfo(parser, "Nauru");
        //numberOfExporters(parser, "cocoa");
        bigExporters(parser, "$999,999,999,999");
        //listExportersTwoProducts(parser, "cotton", "flowers");
    }
}
