package destinations;

import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import service.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSV extends Destination {

    /**
     * This method save the report to CSV file.
     *
     * @param report is the String representation,
     *               which should be saved to report.
     */

    @Override
    public void writeToDestination(String report) {

        String fileName;
        int counterOfFiles = 1;

        File file;
        FileWriter fileWriter = null;
        while (true) {
            fileName = "createdFileCsv" + counterOfFiles + ".csv";
            file = new File(fileName);
            if (!file.exists()) {
                break;
            } else {
                counterOfFiles++;
                fileName = "createdFileCsv" + counterOfFiles + ".csv";
            }
        }
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(report);
            System.out.println("\nCSV file " + fileName
                    + " was created successfully.");
        } catch (IOException e) {
            if (Main.DEBUG) {
                e.printStackTrace();
            }
            System.out.println("\n#WARNING: Error. "
                    + "Program can not create CSV file.\n");
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                if (Main.DEBUG) {
                    e.printStackTrace();
                }
            }
        }
    }
}
