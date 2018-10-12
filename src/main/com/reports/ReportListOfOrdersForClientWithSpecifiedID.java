package reports;

import service.Main;
import connectors.DataBaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportListOfOrdersForClientWithSpecifiedID extends Report {

    public ReportListOfOrdersForClientWithSpecifiedID(String clientID) {
        super(clientID);
    }

    /**
     * This method generate report about list of orders
     * for client with specified ID.
     *
     * @param DB is database handler, which we are using to connect to database.
     * @return the String representation of report.
     */
    @Override
    public String generateReport(DataBaseConnector DB) {

        ResultSet result;

        if (clientID == null) {
            System.out.println("\n#WARNING: Client ID has not proper value.\n");
            return null;
        }

        StringBuilder listAsSting = new StringBuilder();
        listAsSting.append("Client_Id,Request_id,Name,Quantity,Price\r\n");

        if ((result = DB.executeSQL("SELECT * FROM ORDERS_DATABASE"
                + " WHERE CLIENT_ID = '" + clientID + "'")) == null) {
            System.out.println("#WARNING: SQL query is not valid.\n");
            return null;
        }

        try {
            while (result.next()) {
                listAsSting.append(DB.getResultSet()
                        .getString("CLIENT_ID")
                        + ", "
                        + result.getString("REQUEST_ID") + ","
                        + result.getString("NAME") + ", "
                        + result.getString("QUANTITY") + ","
                        + result.getString("PRICE") + "\r\n");
            }
        } catch (SQLException e) {
            if (Main.DEBUG) {
                e.printStackTrace();
            }
            System.out.println("\n#WARNING: Database access error.\n");
            return null;
        }

        report = String.valueOf(listAsSting);

        return report;
    }
}
