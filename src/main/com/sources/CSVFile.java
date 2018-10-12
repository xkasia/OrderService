package sources;

import service.Main;
import service.Order;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CSVFile extends Source {

    String[] columnsNamesForCSV;

    public CSVFile(final String[] columnsNames) {
        this.columnsNamesForCSV = columnsNames;
    }

    /**
     * This method read the csv file, check if orders in a file have proper
     * format and add proper files to list of orders.
     *
     * @param listOfProperPathsToFiles list of proper path to files,
     *                                 which should be read.
     * @param numberOfFilePath         number of file path from list of paths
     *                                 to files which should be read.
     * @param listOfOrders             list of orders, where proper order
     *                                 should be added.
     * @return counter of added proper orders to list of orders.
     */
    @Override
    public int readFromFile(final List<String> listOfProperPathsToFiles,
                            final int numberOfFilePath,
                            final List<Order> listOfOrders) {
        int orderCounter = 0;
        int counterOfCSVLine = 0;
        FileReader filereader;
        CSVReader csvReader;
        String[] elementsOfOrderArray;
        String[] columnsName = columnsNamesForCSV;

        try {
            filereader = new FileReader(listOfProperPathsToFiles
                    .get(numberOfFilePath));
        } catch (FileNotFoundException e) {
            if (Main.DEBUG) {
                e.printStackTrace();
            }
            System.out.println("\n#WARNING: File "
                    + listOfProperPathsToFiles.get(numberOfFilePath)
                    + "not exists.\n");
            return orderCounter;
        }

        csvReader = new CSVReader(filereader);

        // check if we can read from csv file
        try {
            elementsOfOrderArray = csvReader.readNext();
        } catch (IOException e) {
            return orderCounter;
        }

        counterOfCSVLine++;

        // check if read element is not null and length is correct
        if ((elementsOfOrderArray) == null
                || elementsOfOrderArray.length != 5) {
            System.out.println("\n#WARNING: Header of "
                    + listOfProperPathsToFiles.get(numberOfFilePath)
                    + " file has wrong format! \n"
                    + "Program can not lead the orders from this "
                    + "file, because this is not proper list.\n");
            return orderCounter;
        }

        // check if csv header is correct
        if (!(elementsOfOrderArray[0].equals(columnsName[0])
                && elementsOfOrderArray[1].equals(columnsName[1])
                && elementsOfOrderArray[2].equals(columnsName[2])
                && elementsOfOrderArray[3].equals(columnsName[3])
                && elementsOfOrderArray[4].equals(columnsName[4]))) {
            System.out.println("\n#WARNING: Header of "
                    + listOfProperPathsToFiles.get(numberOfFilePath)
                    + " file has wrong format! \n"
                    + "The current format is: "
                    + Arrays.toString(elementsOfOrderArray) + ".\n"
                    + "The proper format should be [Client_Id, "
                    + "Request_id, Name, Quantity, Price].\n"
                    + "Program can not lead the orders from this "
                    + "file, because this is not proper list.\n");
            return orderCounter;
        }

        // main loop to read from csv file
        while (true) {
            //check if we still can read from file
            try {
                elementsOfOrderArray = csvReader.readNext();
            } catch (IOException e) {
                System.out.println("\n#WARNING: "
                        + "Program can not red from file.\n");
                if (Main.DEBUG) {
                    e.printStackTrace();
                }
                return orderCounter;
            }
            counterOfCSVLine++;

            //check if there is another line of csv
            if (elementsOfOrderArray == null) {
                // we are here at the end of csv file
                break;
            }
            //check if every line of csv has 5 elements
            if (elementsOfOrderArray.length != 5) {
                System.out.println("\n#WARNING: Line "
                        + counterOfCSVLine + " in "
                        + listOfProperPathsToFiles.get(numberOfFilePath)
                        + " file has wrong format, because "
                        + "the order has not 5 elements! \n");
                continue;
            }

            Order order = new Order();
            //check first field of line
            if (elementsOfOrderArray[0].length() > 5
                    || elementsOfOrderArray[0].length() <= 0) {
                System.out.println("\n#WARNING: Line " + counterOfCSVLine
                        + " in "
                        + listOfProperPathsToFiles.get(numberOfFilePath)
                        + " file has wrong format,"
                        + " because the field ClientID looks like "
                        + elementsOfOrderArray[0]
                        + " and do not have from 1 to 6 signs.\n");
                continue;
            }
            if (elementsOfOrderArray[0].contains(" ")) {
                System.out.println("\n#WARNING: Line " + counterOfCSVLine + " in "
                        + listOfProperPathsToFiles.get(numberOfFilePath)
                        + " file has wrong format, because the field ClientID"
                        + " contains space character.\n");
                continue;
            }

            //check third field of line
            if (elementsOfOrderArray[2].length() > 255
                    || elementsOfOrderArray[2].length() <= 0) {
                System.out.println("\n#WARNING: Line " + counterOfCSVLine + " in "
                        + listOfProperPathsToFiles.get(numberOfFilePath)
                        + " file has wrong format, "
                        + "because the field Name looks like "
                        + elementsOfOrderArray[2]
                        + " and do not have from 1 to 255 signs.\n");
                continue;
            }

            try {
                order.setClientID(elementsOfOrderArray[0]);
                order.setRequestID(Long.parseLong(elementsOfOrderArray[1]));
                order.setName(elementsOfOrderArray[2]);
                order.setQuantity(Integer.parseInt(elementsOfOrderArray[3]));
                order.setPrice(Double.parseDouble(elementsOfOrderArray[4]));
            } catch (NumberFormatException e) {
                System.out.println("\n#WARNING: Line " + counterOfCSVLine + " in "
                        + listOfProperPathsToFiles.get(numberOfFilePath)
                        + " file has wrong format.\n");
                if (Main.DEBUG) {
                    e.printStackTrace();
                }
                continue;
            }

            //if everything is correct finally and order to order list
            listOfOrders.add(order);
            orderCounter++;
        }
        return orderCounter;
    }
}


