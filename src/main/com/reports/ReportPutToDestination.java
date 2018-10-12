package reports;

import destinations.CSV;
import destinations.Destination;
import destinations.Screen;

public class ReportPutToDestination {

    /**
     * This method call the report and display
     * on the screen or save to CSV file.
     *
     * @param report            is the String representation,
     *                          which should to be placed into report.
     * @param outputDestination defines whether the report should be
     *                          saved to CSV file or display on the screen.
     * @return indicated state of method,
     * on success method return 0, otherwise 1.
     */
    public static int callReport(String report, int outputDestination) {

        Destination destination;
        if (outputDestination == 1) {
            destination = new CSV();
        } else if (outputDestination == 2) {
            destination = new Screen();
        } else {
            return 1;
        }
        destination.writeToDestination(report);
        return 0;
    }
}
