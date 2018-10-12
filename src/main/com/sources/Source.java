package sources;

import service.Order;

import java.util.List;

public abstract class Source {

    /**
     * This method read the file, check if orders in a file have proper format
     * and add proper fies to list of orders.
     *
     * @param listOfProperPathsToFiles list of proper path to files,
     *                                 which should be read.
     * @param numberOfFilePath         number of file path from list of paths
     *                                 to files which should be read.
     * @param listOfOrders             list of orders, where proper order
     *                                 should be added.
     * @return counter of added proper orders to list of orders.
     */

    public abstract int readFromFile(List<String> listOfProperPathsToFiles,
                                     int numberOfFilePath,
                                     List<Order> listOfOrders);
}
