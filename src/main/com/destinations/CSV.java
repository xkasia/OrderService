package destinations;

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

        final String NEW_LINE_SEPARATOR = "\n";
        File file;
        FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

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
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            csvFilePrinter.printRecord(report);
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
                csvFilePrinter.close();
            } catch (IOException e) {
                if (Main.DEBUG) {
                    e.printStackTrace();
                }
            }
        }
    }
}
