package destinations;

public class Screen extends Destination {

    /**
     * This method show on the screen the report.
     *
     * @param report is the String representation of report.
     */
    @Override
    public void writeToDestination(String report) {
        System.out.println(report);
    }
}
