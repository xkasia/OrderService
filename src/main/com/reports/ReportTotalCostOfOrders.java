package reports;

import service.Main;
import connectors.DataBaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportTotalCostOfOrders extends Report {

    /**
     * This method count total cost of all orders
     * in database table and generate report.
     *
     * @param DB is database handler, which we are using to connect to database.
     * @return the String representation of report.
     */
    public String generateRaport(DataBaseConnector DB) {

        ResultSet result;
        double totalCostOfOrders;

        if ((result = DB.executeSQL("SELECT SUM(PRICE) "
                + "FROM ORDERS_DATABASE")) == null) {
            System.out.println("#WARNING: SQL query is not valid.\n");
            return null;
        }

        try {
            result.next();
            totalCostOfOrders = result.getDouble(1);
        } catch (SQLException e) {
            if (Main.DEBUG) {
                e.printStackTrace();
            }
            System.out.println("#WARNING: Database access error.\n");
            return null;
        }

        report = String.format("The total cost of orders "
                + "= %.2f", totalCostOfOrders);

        return report;
    }
}
