package service;

import connectors.DataBaseConnector;
import sources.CSVFile;
import sources.Source;
import sources.XMLFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OrdersService {

    private List<String> listOfProperPathsToFiles;
    List<Order> listOfOrders;
    DataBaseConnector DB;
    String[] columnsNamesForXML;
    String[] columnsNamesForCSV;

    public OrdersService(final DataBaseConnector DB,
                         final String[] columnsNamesForXML,
                         final String[] columnsNamesForCSV) {
        this.DB = DB;
        this.columnsNamesForXML = columnsNamesForXML;
        this.columnsNamesForCSV = columnsNamesForCSV;
    }

    /**
     * This method verify if given files exist
     * and have right file extension: csv or xml.
     *
     * @param filePath is the path of the file to check.
     * @return number of added proper paths to list.
     */
    public int checkFiles(final String[] filePath) {

        listOfProperPathsToFiles = new ArrayList<String>();
        int counter = 0;

        for (int i = 0; i < filePath.length; i++) {
            File file = new File(filePath[i]);
            if (file != null && file.exists() && (filePath[i].endsWith(".csv")
                    || filePath[i].endsWith(".xml"))) {
                listOfProperPathsToFiles.add(filePath[i]);
                counter++;
            } else {
                System.out.println("\n\n" + filePath[i]
                                   + " is not proper path.");
            }
        }
        return counter;
    }

    /**
     * This method reads csv and xml files and loads orders data to List.
     *
     * @return number of orders added to list.
     */
    public int loadOrdersToList() {
        listOfOrders = new ArrayList<>();

        Source source;
        int numerOfOrders = 0;

        for (int i = 0; i < listOfProperPathsToFiles.size(); i++) {
            if (listOfProperPathsToFiles.get(i).endsWith(".csv")) {
                source = new CSVFile(columnsNamesForCSV);
                numerOfOrders += source.readFromFile(listOfProperPathsToFiles,
                        i, listOfOrders);
            } else if (listOfProperPathsToFiles.get(i).endsWith(".xml")) {
                source = new XMLFile(columnsNamesForXML);
                numerOfOrders += source.readFromFile(listOfProperPathsToFiles,
                        i, listOfOrders);
            }
        }
        return numerOfOrders;
    }

    /**
     * This method reads orders from the list and add them to the database.
     */
    public void loadOrdersToDatabase() {
        String sqlQuery;
        for (int i = 0; i < listOfOrders.size(); i++) {
            Order order = listOfOrders.get(i);
            sqlQuery = "INSERT INTO ORDERS_DATABASE VALUES (null , '"
                    + order.getClientID() + "' , "
                    + order.getRequestID() + ", ' "
                    + order.getName() + " ' , "
                    + order.getQuantity() + ", "
                    + order.getPrice() + " )";
            DB.executeSQL(sqlQuery);
        }
    }
}
