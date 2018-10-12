package reports;

import service.Main;
import connectors.DataBaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportTotalNumOfOrders extends Report {

    /**
     * This method count number of all orders in database table
     * and generate report.
     *
     * @param DB is database handler, which we are using to connect to database.
     * @return the String representation of report.
     */
    public String generateReport(DataBaseConnector DB) {
        ResultSet result;
        int numberOfOrders;

        if ((result = DB.executeSQL("SELECT COUNT(CLIENT_ID) "
                + "FROM ORDERS_DATABASE")) == null) {
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
            System.out.println("\n#WARNING: Database access error.\n ");
            return null;
        }

        report = "The total number of Orders = " + numberOfOrders;

        return report;
    }
}
