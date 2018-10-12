package destinations;

abstract public class Destination {

    /**
     * This method save the report in chosen destination.
     *
     * @param report is the String representation,
     *               which should be saved to report.
     */
    public abstract void writeToDestination(String report);
}
