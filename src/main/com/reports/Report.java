package reports;

import connectors.DataBaseConnector;

abstract public class Report {

    String report;
    String clientID;

    public Report() {
    }

    public Report(String clientID) {
        this.clientID = clientID;
    }

    /**
     * This method generate the report.
     *
     * @param DB is database handler, which we are using to connect to database.
     * @return the String representation of report.
     */
    public abstract String generateReport(DataBaseConnector DB);
}
