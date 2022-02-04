import java.util.Scanner;

public class Menu {

    // Method to print menu header
    public void printHeader() {
        System.out.println("\n********************************************************\n");
        System.out.println("| Welcome to the Poised Project Management System Menu |\n");
        System.out.println("********************************************************");

    }

    // Prints menu options
    public void printMenu() {
        System.out.println("\nPlease make a selection: \n");
        System.out.println("1) Add a new Project");
        System.out.println("2) View all Projects");
        System.out.println("3) Edit a Project");
        System.out.println("4) View all Open Projects");
        System.out.println("5) View Overdue Projects");
        System.out.println("6) View Architects");
        System.out.println("7) View Contractors");
        System.out.println("8) View Customers");
        System.out.println("9) Finalise a Project");
        System.out.println("10) View Invoices");
        System.out.println("0) Exit program");

    }
}
