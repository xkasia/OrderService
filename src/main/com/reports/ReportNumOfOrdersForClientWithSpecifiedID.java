package reports;

import service.Main;
import connectors.DataBaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportNumOfOrdersForClientWithSpecifiedID extends Report {

    public ReportNumOfOrdersForClientWithSpecifiedID(String clientID) {
        super(clientID);
    }

    /**
     * This method count number of orders for the client
     * with specified ID and generate report.
     *
     * @param DB is database handler, which we are using to connect to database.
     * @return the String representation of report.
     */
    @Override
    public String generateRaport(DataBaseConnector DB) {

        ResultSet result;
        int numberOfOrders;

        if (clientID == null) {
            System.out.println("\n#WARNING: Client ID has not proper value.\n");
            return null;
        }
        if ((result = DB.executeSQL("SELECT COUNT(CLIENT_ID) "
                + "FROM ORDERS_DATABASE WHERE CLIENT_ID = '"
                + clientID + "'")) == null) {
            System.out.println("\n#WARNING: SQL query is not valid.\n");
            return null;
        }

        try {
            result.next();
            numberOfOrders = result.getInt(1);
        } catch (SQLException e) {
            if (Main.DEBUG) {
                e.printStackTrace();
            }
            System.out.println("\n#WARNING: Database access error.\n");
            return null;
        }

        report = "The total number of orders"
                + " for client with ID " + clientID + " =  "
                + numberOfOrders;

        return report;
    }
}
