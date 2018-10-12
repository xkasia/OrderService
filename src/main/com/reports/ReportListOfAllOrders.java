package reports;

import service.Main;
import connectors.DataBaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportListOfAllOrders extends Report {

    /**
     * This method generate report about list of all orders in database table.
     *
     * @param DB is database handler, which we are using to connect to database.
     * @return the String representation of report.
     */
    @Override
    public String generateRaport(DataBaseConnector DB) {

        ResultSet result;

        StringBuilder listAsSting = new StringBuilder();
        listAsSting.append("Client_Id,Request_id,Name,Quantity,Price\n");

        if ((result = DB.executeSQL("SELECT * FROM ORDERS_DATABASE")) == null) {
            System.out.println("\n#WARNING: SQL query is not valid.\n");
            return null;
        }

        try {
            while (result.next()) {
                listAsSting.append(result.getString("CLIENT_ID")
                        + ", "
                        + result.getString("REQUEST_ID") + ", "
                        + result.getString("NAME") + ", "
                        + result.getString("QUANTITY") + ", "
                        + result.getString("PRICE") + " \n");
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
