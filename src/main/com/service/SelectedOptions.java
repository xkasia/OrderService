package service;

public class SelectedOptions {

    private int reportNumber;
    private int destination;
    private String givenClientID;

    /**
     * This method afford the access to private attribute.
     *
     * @return reportNumber attribute.
     */
    public int getReportNumber() {
        return reportNumber;
    }

    /**
     * This method afford to set a value of private attribute.
     *
     * @param reportNumber attribute with given argument.
     */
    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    /**
     * This method afford the access to private attribute.
     *
     * @return destination attribute.
     */
    public int getDestination() {
        return destination;
    }

    /**
     * This method afford to set a value of private attribute.
     *
     * @param destination attribute with given argument.
     */
    public void setDestination(int destination) {
        this.destination = destination;
    }

    /**
     * This method afford the access to private attribute.
     *
     * @return givenClintID attribute.
     */
    public String getGivenClientID() {
        return givenClientID;
    }

    /**
     * This method afford to set a value of private attribute.
     *
     * @param givenClientID attribute with given argument.
     */
    public void setGivenClientID(String givenClientID) {
        this.givenClientID = givenClientID;
    }

}
