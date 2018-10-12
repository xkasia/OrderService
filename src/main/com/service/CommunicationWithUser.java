package service;

import org.apache.commons.lang3.ArrayUtils;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommunicationWithUser {

    int[] reportNumbers;
    int[] numbersWithID;
    int[] reportDestinationNumbers;
    boolean userTypedExit;
    boolean reportWithUserID;
    SelectedOptions options;

    public CommunicationWithUser(int[] reportNumbers, int[] numersWithID,
                                 int[] reportDestinationNumbers,
                                 SelectedOptions options) {
        this.reportNumbers = reportNumbers;
        this.numbersWithID = numersWithID;
        this.reportDestinationNumbers = reportDestinationNumbers;
        this.userTypedExit = false;
        this.options = options;
        this.reportWithUserID = false;
    }

    /**
     * This method check if chosen options by user are correct.
     *
     * @return flag if chosen options are correct or not.
     */
    public boolean checkOptionsAreCorrect() {
        if (!checkIfReportWithUserID()) {
            return true;
        } else if ((checkIfReportWithUserID())
                && (options.getGivenClientID() != null)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * This method check if given report is for specific client id.
     *
     * @return true if chosen report is for specific client id, otherwise false.
     */
    public boolean checkIfReportWithUserID() {
        return reportWithUserID;
    }

    /**
     * This method ask user for number of the report, which should be generated.
     */
    public void askForReportNumber() {
        showMainMenu();
        options.setReportNumber(giveTheNumberInMenu(reportNumbers));
        if (options.getReportNumber() == 0) {
            userTypedExit = true;
            return;
        }
        if (ArrayUtils.contains(numbersWithID, options.getReportNumber())) {
            askForClientID();
            reportWithUserID = true;
            if (options.getGivenClientID() == null) {
                return;
            }
        } else {
            reportWithUserID = false;
        }
    }

    /**
     * This method ask user for client ID
     * for whom the report should be generated.
     */
    public void askForClientID() {
        options.setGivenClientID(getclientID());
    }

    /**
     * This method ask user for destination where the report
     * should be generated - csv file or screen.
     */
    public void askForDestination() {
        showReportDesinationMenu();
        options.setDestination(giveTheNumberInMenu(reportDestinationNumbers));
    }

    /**
     * This method asks user for putting in the console the number from
     * the menu and check if given number is proper or not.
     *
     * @param numberArray is array, which contains the possible numbers
     *                    to choose in menu.
     * @return the chosen number in menu.
     */
    public static int giveTheNumberInMenu(int[] numberArray) {
        boolean incorrectInputWasGiven = true;
        int givenNumber = -1;

        ExitLabel:
        while (incorrectInputWasGiven) {
            Scanner scanner = new Scanner(System.in);
            try {
                givenNumber = Integer.parseInt(scanner.nextLine());
            } catch (NoSuchElementException | NumberFormatException e) {
                if (Main.DEBUG) {
                    e.printStackTrace();
                }
                System.out.println("\n#WARNING: Wrong format."
                        + " Please try again.\n");
                continue;
            }
            for (int i = 0; i < numberArray.length; i++) {
                if (numberArray[i] == givenNumber) {
                    incorrectInputWasGiven = false;
                    break ExitLabel;
                }
            }
            System.out.println("\n#WARNING: Can not find such number. "
                    + "Please give the proper number.\n");
        }
        return givenNumber;
    }

    /**
     * This method asks user for putting in the console
     * the number of the client ID for whom the report should be generated
     * and check if the given ID is proper or not.
     *
     * @return the String representation of the number of given client ID.
     */
    public String getclientID() {
        String givenClientID;
        System.out.println("Please give the number of the client: ");
        Scanner scanner = new Scanner(System.in);
        try {
            givenClientID = scanner.nextLine();
        } catch (NoSuchElementException | NumberFormatException e) {
            if (Main.DEBUG) {
                e.printStackTrace();
            }
            System.out.println("\n#WARNING: Wrong format.\n");
            return null;
        }
        if (givenClientID.length() > 6) {
            System.out.println("\n#WARNING: The format of given client ID "
                    + "is wrong. It should have maximum 6 signs.\n");
            return null;
        }
        return givenClientID;
    }

    /**
     * This method shows in console the main menu
     * which contains possible report to generate.
     */
    public void showMainMenu() {
        String reportMenu =
                "\n\n================================="
                        + "===============================\n"
                        + "Please give the proper number to generate report.\n"
                        + "Generate report about:\n\n"
                        + "0.\tQuit the program.\n\n"
                        + "1.\tThe total number of orders.\n"
                        + "2.\tThe total number of orders for the client "
                        + "with specified ID.\n"
                        + "3.\tThe total cost of orders.\n"
                        + "4.\tThe total cost of orders for the client "
                        + "with specified ID.\n"
                        + "5.\tThe list of all orders.\n"
                        + "6.\tThe list of all orders for the client"
                        + " with specified ID.\n"
                        + "7.\tThe average order value.\n"
                        + "8.\tThe average order value for the client"
                        + " with specified ID.\n"
                        + "======================================="
                        +"=========================\n\n";
        System.out.println(reportMenu);
    }

    /**
     * This method shows in console menu about possible destination
     * for generated report.
     */
    public void showReportDesinationMenu() {
        String reportDestinationMenu =
                "\nPlease choose what you want to do with report:\n"
                        + "1.\tSave to CSV file.\n"
                        + "2.\tShow on the screen.\n \n";

        System.out.println(reportDestinationMenu);
    }

    /**
     * This method afford the access to attribute.
     *
     * @return userTypedExit attribute.
     */
    public boolean isUserTypedExit() {
        return userTypedExit;
    }
}
