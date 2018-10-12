package reports;

import service.SelectedOptions;

public class ReportGenerator {

    private SelectedOptions options;

    public ReportGenerator(SelectedOptions options) {
        this.options = options;
    }

    /**
     * This method call the constructor of chosen report.
     *
     * @return the references to newly created report.
     */
    public Report generateReport() {
        Report newReport = null;

        switch (options.getReportNumber()) {
            case 1:
                newReport = new ReportTotalNumOfOrders();
                break;
            case 2:
                newReport = new ReportNumOfOrdersForClientWithSpecifiedID(options.getGivenClientID());
                break;
            case 3:
                newReport = new ReportTotalCostOfOrders();
                break;
            case 4:
                newReport = new ReportTotalCostOfOrdersForClientWithSpecifiedID(options.getGivenClientID());
                break;
            case 5:
                newReport = new ReportListOfAllOrders();
                break;
            case 6:
                newReport = new ReportListOfOrdersForClientWithSpecifiedID(options.getGivenClientID());
                break;
            case 7:
                newReport = new ReportAverageOrderVal();
                break;
            case 8:
                newReport = new ReportAverageOrderValForClientWithSpecifiedID(options.getGivenClientID());
                break;
        }
        return newReport;
    }
}
