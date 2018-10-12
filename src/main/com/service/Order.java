package service;

public class Order {

    private String clientID;
    private long requestID;
    private String name;
    private int quantity;
    private double price;

    /**
     * This method afford the access to private attribute.
     *
     * @return clientID attribute.
     */
    public String getClientID() {
        return clientID;
    }

    /**
     * This method afford to set a value of private attribute.
     *
     * @param clientID attribute with given argument.
     */
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    /**
     * This method afford the access to private attribute.
     *
     * @return requestID attribute.
     */
    public long getRequestID() {
        return requestID;
    }

    /**
     * This method afford to set a value of private attribute.
     *
     * @param requestID attribute with given argument.
     */
    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    /**
     * This method afford the access to private attribute.
     *
     * @return name attribute.
     */

    public String getName() {
        return name;
    }

    /**
     * This method afford to set a value of private attribute.
     *
     * @param name attribute with given argument.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method afford the access to private attribute.
     *
     * @return quantity attribute.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * This method afford to set a value of private attribute.
     *
     * @param quantity attribute with given argument.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * This method afford the access to private attribute.
     *
     * @return price attribute.
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method afford to set a value of private attribute.
     *
     * @param price attribute with given argument.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * This method represent object as String.
     *
     * @return the the String representation of Order object.
     */
    @Override
    public String toString() {
        return "Order{"
                + "clientID='" + clientID + '\''
                + ", requestID=" + requestID
                + ", name='" + name + '\''
                + ", quantity=" + quantity
                + ", price=" + price
                + '}';
    }
}
