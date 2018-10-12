package reports;

import service.Main;
import connectors.DataBaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportAverageOrderVal extends Report {

    /**
     * This method count average order value for all orders
     * in database table and generate report.
     *
     * @param DB is database handler, which we are using to connect to database.
     * @return the String representation of report.
     */
    @Override
    public String generateRaport(final DataBaseConnector DB) {

        ResultSet result;
        double averageValue;

        if ((result = DB.executeSQL("SELECT AVG(PRICE)"
                + " FROM ORDERS_DATABASE")) == null) {
            System.out.println("#WARNING: SQL query is not valid.\n");
            return null;
        }

        try {
            result.next();
            averageValue = result.getDouble(1);
        } catch (SQLException e) {
            if (Main.DEBUG) {
                e.printStackTrace();
            }
            System.out.println("\n#WARNING: Database access error.\n");
            return null;
        }

        report = String.format("The average order Value = %.2f", averageValue);

        return report;
    }
}






















































