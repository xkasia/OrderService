package sources;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import service.Main;
import service.Order;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLFile extends Source {

    String[] columnsNamesForXML;

    public XMLFile(String[] columnsNames) {
        this.columnsNamesForXML = columnsNames;
    }

    /**
     * This method read the xml file, check if orders in a file
     * have proper format and add proper fies to list of orders.
     *
     * @param listOfProperPathsToFiles list of proper path to files,
     *                                 which should be read.
     * @param numberOfFilePath         number of file path from list of paths
     *                                 to files which should be read.
     * @param listOfOrders             list of orders, where proper order
     *                                 should be added.
     * @return counter of added proper orders to list of orders.
     */

    @Override
    public int readFromFile(List<String> listOfProperPathsToFiles,
                            int numberOfFilePath, List<Order> listOfOrders) {
        File xmlFile = new File(listOfProperPathsToFiles.get(numberOfFilePath));

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document document;
        int orderCounter = 0;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(xmlFile);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            if (Main.DEBUG) {
                e.printStackTrace();
            }
            System.out.println("\n#WARNING: Program can not read "
                    + listOfProperPathsToFiles.get(numberOfFilePath)
                    + " file.\n");
            return orderCounter;
        }

        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("request");

        for (int j = 0; j < nodeList.getLength(); j++) {
            Order order;
            if ((order = getOrder(listOfProperPathsToFiles, nodeList.item(j),
                    numberOfFilePath, j)) != null) {
                listOfOrders.add(order);
                orderCounter++;
            }
        }
        return orderCounter;
    }

    /**
     * This method check if specified order in xml file have proper format.
     *
     * @param listOfProperPathsToFiles list of proper path to files,
     *                                 which should be read.
     * @param node                     the node in xml file,
     *                                 which should be read.
     * @param numberOfFilePath         number of file path from list of paths
     *                                 to files which should be read.
     * @param counterOfOrderInXMLFile  count which order from xml file
     *                                 should be read.
     * @return proper order, which should be added to list of proper orders.
     */
    private Order getOrder(final List<String> listOfProperPathsToFiles,
                           final Node node, final int numberOfFilePath,
                           int counterOfOrderInXMLFile) {
        counterOfOrderInXMLFile++;
        Order order;

        if (node.getNodeType() != Node.ELEMENT_NODE) {
            return null;
        }

        Element element = (Element) node;
        String[] columnsName = columnsNamesForXML;

        for (int i = 0; i < columnsName.length; i++) {
            if (getTagValue(columnsName[i], element) == null) {
                System.out.println("          found in "
                        + listOfProperPathsToFiles.get(numberOfFilePath)
                        + " file.");
                return null;
            }
        }
        if (getTagValue(columnsName[0], element).length() > 6
                || getTagValue(columnsName[0], element).length() <= 0) {
            System.out.println("\n#WARNING: Order " + counterOfOrderInXMLFile
                    + " in " + listOfProperPathsToFiles.get(numberOfFilePath)
                    + "file has wrong format,"
                    + " because the field ClientID looks like "
                    + getTagValue(columnsName[0], element)
                    + " and do not have from 1 to 6 signs.\n");
            return null;
        }
        if (getTagValue(columnsName[0], element).contains(" ")) {
            System.out.println("\n#WARNING: Line " + counterOfOrderInXMLFile
                    + " in " + listOfProperPathsToFiles.get(numberOfFilePath)
                    + " file has wrong format,"
                    + " because the field ClientID contains space character.\n");
            return null;
        }
        if (getTagValue(columnsName[2], element).length() > 255
                || getTagValue(columnsName[2], element).length() <= 0) {
            System.out.println("\n#WARNING: Order " + counterOfOrderInXMLFile
                    + " in " + listOfProperPathsToFiles.get(numberOfFilePath)
                    + "file has wrong format, "
                    + "because the field Name looks like "
                    + getTagValue(columnsName[2], element)
                    + " and do not have from 1 to 255 signs.\n");
            return null;
        }
        try {
            order = new Order();
            order.setClientID((getTagValue(columnsName[0], element)));
            order.setRequestID(Long.parseLong(getTagValue(columnsName[1], element)));
            order.setName((getTagValue(columnsName[2], element)));
            order.setQuantity(Integer.parseInt(getTagValue(columnsName[3], element)));
            order.setPrice(Double.parseDouble(getTagValue(columnsName[4], element)));
        } catch (NumberFormatException e) {
            if (Main.DEBUG) {
                e.printStackTrace();
            }
            System.out.println("\n#WARNING: Order " + counterOfOrderInXMLFile
                    + " in " + listOfProperPathsToFiles.get(numberOfFilePath)
                    + " file has wrong format! \n");
            return null;
        }
        return order;
    }

    /**
     * This method get value for given tag in xml file.
     *
     * @param tag     name of the tag in xml file, which is read.
     * @param element container for a tags in one order.
     * @return suitable value for given tag.
     */
    private static String getTagValue(String tag, Element element) {
        if (element.getElementsByTagName(tag) == null
                || element.getElementsByTagName(tag).item(0) == null
                || element.getElementsByTagName(tag).
                item(0).getChildNodes() == null) {
            System.out.println("\n#WARNING: Program can not find tag "
                    + tag + " in a file");
            return null;
        }
        NodeList nodeList = element.getElementsByTagName(tag)
                .item(0).getChildNodes();
        Node node;
        if ((node = nodeList.item(0)) == null) {
            System.out.println("\n#WARNING: Value of tag "
                    + tag + " equals null");
            return null;
        }
        return node.getNodeValue();
    }
}
