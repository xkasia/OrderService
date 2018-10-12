package service;

import connectors.DataBaseConnector;
import reports.Report;
import reports.ReportGenerator;
import reports.ReportPutToDestination;

public class Main {
    public static final boolean DEBUG = false;
    private static final String[] columnsNamesForCSV =
            {"Client_Id", "Request_id", "Name", "Quantity", "Price"};
    private static final String[] columnsNamesForXML =
            {"clientId", "requestId", "name", "quantity", "price"};
    private static final int[] reportNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private static final int[] numbersWithId = {2, 4, 6, 8};
    private static final int[] reportDestinationNumbers = {1, 2};

    /**
     * This method check if files are proper, connect to database,
     * load proper orders to database, show console menu,
     * ask user about options of report to create
     * and generate it in the chosen destination.
     *
     * @param args args the array of file paths.
     */
    public static void main(String[] args) {

        //database connection
        DataBaseConnector DB = new DataBaseConnector();
        if (!DB.getIsConneted()) {
            return;
        }

        //load orders from files
        OrdersService ordersService =
                new OrdersService(DB, columnsNamesForXML, columnsNamesForCSV);
        if (ordersService.checkFiles(args) == 0) {
            System.out.println("\n#WARNING: There is no one"
                               + " proper path to file.\n");
            return;
        }
        ordersService.loadOrdersToList();

        //load orders to database
        if (ordersService.listOfOrders.size() == 0) {
            System.out.println("\n#WARNING: The list of orders is empty.\n");
            return;
        }
        ordersService.loadOrdersToDatabase();

        // user options declaration
        SelectedOptions options = new SelectedOptions();
        CommunicationWithUser consolemanipulation =
                new CommunicationWithUser(reportNumbers, numbersWithId,
                        reportDestinationNumbers, options);
        while (true) {
            consolemanipulation.askForReportNumber();
            if (consolemanipulation.isUserTypedExit()) {
                break;
            }

            if (consolemanipulation.checkIfReportWithUserID()) {
                if (!DB.checkIfClientIDExists(options.getGivenClientID())) {
                    continue;
                }
            }

            consolemanipulation.askForDestination();
            if (!consolemanipulation.checkOptionsAreCorrect()) {
                continue;
            }

            ReportGenerator generator = new ReportGenerator(options);
            Report raport = generator.generateReport();
            ReportPutToDestination.callReport(raport.generateRaport(DB),
                    options.getDestination());
        }

        //close database connection
        DB.close();
    }
}
