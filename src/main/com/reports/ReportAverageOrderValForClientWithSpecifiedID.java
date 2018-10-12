package reports;

import service.Main;
import connectors.DataBaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportAverageOrderValForClientWithSpecifiedID extends Report {

    public ReportAverageOrderValForClientWithSpecifiedID(String clientID) {
        super(clientID);
    }

    /**
     * This method count average order value for the client
     * with specified ID and generate report.
     *
     * @param DB is database handler, which we are using to connect to database.
     * @return the String representation of report.
     */
    @Override
    public String generateRaport(final DataBaseConnector DB) {

        ResultSet result;
        double averageValue;
        if (clientID == null) {
            System.out.println("\n#WARNING: Client ID has not proper value.\n");
            return null;
        }
        if ((result = DB.executeSQL("SELECT AVG(PRICE) "
                + "FROM ORDERS_DATABASE WHERE CLIENT_ID = '"
                + clientID + "'")) == null) {
            System.out.println("\n#WARNING: SQL query is not valid.\n");
            return null;
        }

        try {
            result.next();
            averageValue = DB.getResultSet().getDouble(1);
        } catch (SQLException e) {
            if (Main.DEBUG) {
                e.printStackTrace();
            }
            System.out.println("\n#WARNING: Database access error.\n");
            return null;
        }

        report = String.format("The average order Value for client with ID "
                + clientID + " = %.2f", averageValue);

        return report;
    }
}
