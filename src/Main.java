import java.io.*;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        // Initialising the menu
        menuInit();
    }

    // Main Menu
    public static void menuInit() throws IOException {

        Menu menu = new Menu();
        menu.printHeader();
        menu.printMenu();

        // Menu will run until user exits
        while (true) {

            // Asking user for Main Menu choice
            Scanner input = new Scanner(System.in);
            int menuChoice = input.nextInt();


            // try catch block to connect to database
            try {
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/poisepms_db?useSSL=false",
                        "poiseduser",
                        "poised"
                );

                Statement statement = connection.createStatement();
                ResultSet results;
                int rowsAffected;

                // Fetches all data from books table
                results = statement.executeQuery("SELECT * FROM projects");


            if (menuChoice == 1) {

                // Getting user input for a new project
                input = new Scanner(System.in);

                System.out.println("What is the project number?");
                String projectNumber = input.nextLine();

                // While loop to keep project numbers to 3 digits or less
                while (projectNumber.length() > 3) {

                    System.out.println("Please keep project numbers to 3 or less digits");
                    System.out.println("What is the project number?");
                    input = new Scanner(System.in);
                    projectNumber = input.nextLine();
                }

                System.out.println("What is the project name?");
                input = new Scanner(System.in);
                String projectName = input.nextLine();
                System.out.println("What is the building type?");
                String buildingType = input.nextLine();
                System.out.println("What is the project address?");
                String projectAddress = input.nextLine();
                System.out.println("What is the ERF number?");
                String erf = input.nextLine();
                long totalFee;

                // While loop and try catch to ensure user enters a valid long number for total fee
                while (true) {
                    try {
                        System.out.println("What is the total fee for this project?");
                        totalFee = input.nextLong();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number");
                        input.nextLine();
                    }
                }
                long amountPaid;

                // While loop and try catch to ensure user enters a valid long number for amount paid
                while (true) {
                    try {
                        System.out.println("How much has been paid towards this project to date?");
                        amountPaid = input.nextLong();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number");
                        input.nextLine();
                    }
                }

                System.out.println("When is the deadline date for this project? (YYYYMMDD) ");
                input = new Scanner(System.in);
                String deadline = input.nextLine();

                // User input to add architect
                System.out.println("What is the name of the architect?");
                input = new Scanner(System.in);
                String architectName = input.nextLine();
                System.out.println("What is the phone number of the architect?");
                input = new Scanner(System.in);
                String architectPhone = input.nextLine();

                // While loop until user enters valid cell phone number and breakpoint
                while (architectPhone.length() != 10) {

                    System.out.println("Please enter a valid 10 digit cell phone number");
                    System.out.println("What is the phone number of the architect?");
                    input = new Scanner(System.in);
                    architectPhone = input.nextLine();
                }

                System.out.println("What is the email address of the architect?");
                input = new Scanner(System.in);
                String architectEmail = input.nextLine();
                System.out.println("What is the physical address of the architect?");
                input = new Scanner(System.in);
                String architectAddress = input.nextLine();

                // User input to add contractor
                System.out.println("What is the name of the contractor?");
                input = new Scanner(System.in);
                String contractorName = input.nextLine();
                System.out.println("What is the phone number of the contractor?");
                input = new Scanner(System.in);
                String contractorPhone = input.nextLine();

                // While loop until user enters valid cell phone number
                while (contractorPhone.length() != 10) {

                    System.out.println("Please enter a valid 10 digit cell phone number");
                    System.out.println("What is the phone number of the contractor?");
                    input = new Scanner(System.in);
                    contractorPhone = input.nextLine();
                }

                System.out.println("What is the email address of the contractor?");
                input = new Scanner(System.in);
                String contractorEmail = input.nextLine();
                System.out.println("What is the physical address of the contractor?");
                input = new Scanner(System.in);
                String contractorAddress = input.nextLine();

                // User input to add customer
                System.out.println("What is the name of the customer?");
                input = new Scanner(System.in);
                String customerName = input.nextLine();
                System.out.println("What is the phone number of the customer?");
                input = new Scanner(System.in);
                String customerPhone = input.nextLine();


                // While loop until user enters valid cell phone number
                while (customerPhone.length() != 10) {

                    System.out.println("Please enter a valid 10 digit cell phone number");
                    System.out.println("What is the phone number of the contractor?");
                    input = new Scanner(System.in);
                    customerPhone = input.nextLine();
                }

                System.out.println("What is the email address of the customer?");
                input = new Scanner(System.in);
                String customerEmail = input.nextLine();
                System.out.println("What is the physical address of the customer?");
                input = new Scanner(System.in);
                String customerAddress = input.nextLine();

                // Creating Person objects and adding breakpoints
                Person architect = new Person(architectName, architectPhone, architectEmail, architectAddress);
                Person contractor = new Person(contractorName, contractorPhone, contractorEmail, contractorAddress);
                Person customer = new Person(customerName, customerPhone, customerEmail, customerAddress);

                // If project name is blank, then the name defaults to building type + last name of customer
                String[] customerLastName = customerName.split(" ");
                if (projectName.equals("")) {
                    projectName = buildingType + " " + customerLastName[1];
                }

                String projectStatus = "Open";
                // Creating project object and breakpoint
                Project project = new Project(projectNumber, projectName, buildingType, projectAddress, erf, totalFee, amountPaid, deadline, architect, contractor, customer, projectStatus);

                // Writing new project data to projects table
                rowsAffected = statement.executeUpdate("INSERT INTO projects VALUES (" + projectNumber + ", '" + projectName + "', '" + buildingType + "', '" + projectAddress + "', '" + erf + "', " + totalFee + ", " + amountPaid + ", '" + deadline + "', '" + architectName + "', '" + contractorName + "', '" + customerName + "', '" + projectStatus + "' )");
                System.out.println("Query complete, " + rowsAffected + " rows added.\n");
                printAllFromTable(statement);

                // Writing new architect data to  architects table
                statement.executeUpdate("INSERT INTO architects VALUES (" + projectNumber + ", '" + projectName + "', '"+ architectName + "', '" + architectPhone + "', '" + architectEmail + "', '" + architectAddress + "' )");

                // Writing new architect data to  contractors table
                statement.executeUpdate("INSERT INTO contractors VALUES (" + projectNumber + ", '" + projectName + "', '"+ contractorName + "', '" + contractorPhone + "', '" + contractorEmail + "', '" + contractorAddress + "' )");

                // Writing new architect data to  contractors table
                statement.executeUpdate("INSERT INTO customers VALUES (" + projectNumber + ", '" + projectName + "', '"+ customerName + "', '" + customerPhone + "', '" + customerEmail + "', '" + customerAddress + "' )");

                // Returns to main menu
                menuInit();
            }


            // If user wants to view all projects in the project table, with try catch block
            if (menuChoice == 2) {

                    System.out.println("\n***************************************************\n");
                    System.out.println("          | Poised: All Projects |               \n");
                    System.out.println("***************************************************\n");

                    // Fetches and prints all project data from table
                    printAllFromTable(statement);
                    menuInit();
            }

            // Edit a project
            if (menuChoice == 3) {

                Scanner projChoice = new Scanner(System.in);
                System.out.println("Please enter either the project number or the project name you want to edit: ");
                String projectChoice = projChoice.nextLine();

                ResultSet search = statement.executeQuery("SELECT ProjectNumber, ProjectName, BuildingType, ProjectAddress, ERF, TotalFee, AmountPaid, Deadline, ArchitectName, ContractorName, CustomerName, ProjectStatus FROM projects");

                while (search.next()) {

                    // Finds user selected project
                    if (search.getString("ProjectName").equalsIgnoreCase(projectChoice) || search.getString("ProjectNumber").equalsIgnoreCase(projectChoice)) {
                        System.out.println("\n" + search.getInt("ProjectNumber") + ", "
                                + search.getString("ProjectName") + ", "
                                + search.getString("BuildingType") + ", "
                                + search.getString("ProjectAddress") + ", "
                                + search.getString("ERF") + ", "
                                + search.getLong("TotalFee") + ", "
                                + search.getLong("AmountPaid") + ", "
                                + search.getDate("Deadline") + ", "
                                + search.getString("ArchitectName") + ", "
                                + search.getString("ContractorName") + ", "
                                + search.getString("CustomerName") + ", "
                                + search.getString("ProjectStatus") + "\n");



                        System.out.println("""
                        Please choose an edit option below:
                                                
                        1) Update the Project Number
                        2) Update the Project Name
                        3) Update the Building Type
                        4) Update the Project Address
                        5) Update the ERF Number
                        6) Update the Total Fee
                        7) Update the Amount Paid
                        8) Change the Deadline Date
                        9) Change Architect Name
                        10) Change Architect Phone
                        11) Change Architect Email
                        12) Change Architect Address
                        13) Change Contractor Name
                        14) Change Contractor Phone
                        15) Change Contractor Email
                        16) Change Contractor Address
                        17) Change Customer Name
                        18) Change Customer Phone
                        19) Change Customer Email
                        20) Change Customer Address
                        21) Return to Main Menu""");

                        Scanner eChoice = new Scanner(System.in);
                        String editChoice = eChoice.nextLine();

                        if (editChoice.equals("1")) {
                            Scanner pNum = new Scanner(System.in);
                            System.out.println("Please enter the new Project Number: ");
                            String newProjectNumber = pNum.nextLine();

                            // Updates project table with new values
                            statement.executeUpdate("UPDATE projects SET ProjectNumber = '"+ newProjectNumber +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            menuInit();
                            break;
                        }

                        if (editChoice.equals("2")) {
                            Scanner pName = new Scanner(System.in);
                            System.out.println("Please enter the new Project Name: ");
                            String newProjectName = pName.nextLine();

                            // Updates project table with new values
                            statement.executeUpdate("UPDATE projects SET ProjectName = '"+ newProjectName +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            menuInit();
                            break;

                        }

                        if (editChoice.equals("3")) {
                            Scanner pType = new Scanner(System.in);
                            System.out.println("Please enter the new Building Type: ");
                            String newBuildingType = pType.nextLine();

                            // Updates project table with new values
                            statement.executeUpdate("UPDATE projects SET BuildingType = '"+ newBuildingType +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            menuInit();
                            break;
                        }
//
                        if (editChoice.equals("4")) {
                            Scanner pAddress = new Scanner(System.in);
                            System.out.println("Please enter the new Project Address: ");
                            String newProjectAddress = pAddress.nextLine();

                            // Updates project table with new values
                            statement.executeUpdate("UPDATE projects SET ProjectAddress = '"+ newProjectAddress +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            menuInit();
                            break;
                        }
//
                        if (editChoice.equals("5")) {
                            Scanner pErf = new Scanner(System.in);
                            System.out.println("Please enter the new ERF Number: ");
                            String newErf = pErf.nextLine();

                            statement.executeUpdate("UPDATE projects SET ERF = '"+ newErf +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            menuInit();
                            break;
                        }
//
                        if (editChoice.equals("6")) {
                            Scanner pTotal = new Scanner(System.in);
                            System.out.println("Please enter the new Total Fee: ");
                            long newTotalFee = pTotal.nextLong();

                            statement.executeUpdate("UPDATE projects SET TotalFee = '"+ newTotalFee +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            menuInit();
                            break;
                        }
//
                        if (editChoice.equals("7")) {
                            Scanner pAmountPaid = new Scanner(System.in);
                            System.out.println("Please enter the new Amount Paid: ");
                            long newAmountPaid = pAmountPaid.nextLong();

                            statement.executeUpdate("UPDATE projects SET AmountPaid = '"+ newAmountPaid +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            menuInit();
                            break;
                        }
//
                        if (editChoice.equals("8")) {
                            Scanner pDDate = new Scanner(System.in);
                            System.out.println("Please enter the new Deadline Date (YYYYMMDD): ");
                            String newDeadline = pDDate.nextLine();

                            statement.executeUpdate("UPDATE projects SET Deadline = '"+ newDeadline +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            menuInit();
                            break;
                        }
//
                        if (editChoice.equals("9")) {
                            Scanner pArchitectName = new Scanner(System.in);
                            System.out.println("Please enter the Architect's Name: ");
                            String newArchitectName = pArchitectName.nextLine();

                            // Updates both projects and invoices table with new values
                            statement.executeUpdate("UPDATE projects SET ArchitectName = '"+ newArchitectName +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            statement.executeUpdate("UPDATE architects SET ArchitectName = '"+ newArchitectName +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");

                            menuInit();
                            break;
                        }
//
                        if (editChoice.equals("10")) {
                            Scanner pArchitectPhone = new Scanner(System.in);
                            System.out.println("Please enter the Architect's Phone: ");
                            String newAchitectPhone = pArchitectPhone.nextLine();

                            // Updates the architects table only, has this table has the more specific contact details
                            statement.executeUpdate("UPDATE architects SET ArchitectPhone = '"+ newAchitectPhone +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");

                            menuInit();
                            break;
                        }
//
                        if (editChoice.equals("11")) {
                            Scanner pArchitectMail = new Scanner(System.in);
                            System.out.println("Please enter the Architect's Email: ");
                            String newArchitectMail = pArchitectMail.nextLine();

                            statement.executeUpdate("UPDATE architects SET ArchitectEmail = '"+ newArchitectMail +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");

                            menuInit();
                            break;
                        }
//
                        if (editChoice.equals("12")) {
                            Scanner pArchitectAddress = new Scanner(System.in);
                            System.out.println("Please enter the Architect's Address: ");
                            String newArchitectAddress = pArchitectAddress.nextLine();

                            statement.executeUpdate("UPDATE architects SET ArchitectAddress = '"+ newArchitectAddress +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");

                            menuInit();
                            break;
                        }
//
                        if (editChoice.equals("13")) {
                            Scanner pContractorName = new Scanner(System.in);
                            System.out.println("Please enter the Contractor's Name: ");
                            String newContractorName = pContractorName.nextLine();

                            statement.executeUpdate("UPDATE projects SET ContractorName = '"+ newContractorName +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            statement.executeUpdate("UPDATE contractors SET ContractorName = '"+ newContractorName +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");


                            menuInit();
                            break;
                        }
//
                        if (editChoice.equals("14")) {
                            Scanner pContractorPhone = new Scanner(System.in);
                            System.out.println("Please enter the Contractor's Phone: ");
                            String newContractorPhone = pContractorPhone.nextLine();

                            statement.executeUpdate("UPDATE contractors SET ContractorPhone = '"+ newContractorPhone +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            menuInit();
                            break;
                        }
//
                        if (editChoice.equals("15")) {
                            Scanner pContractorMail = new Scanner(System.in);
                            System.out.println("Please enter the Contractor's Email: ");
                            String newContractorMail = pContractorMail.nextLine();

                            statement.executeUpdate("UPDATE contractors SET ContractorEmail = '"+ newContractorMail +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            menuInit();
                            break;
                        }

                        if (editChoice.equals("16")) {
                            Scanner pContractorAddress = new Scanner(System.in);
                            System.out.println("Please enter the Contractor's Address: ");
                            String newContractorAddress = pContractorAddress.nextLine();

                            statement.executeUpdate("UPDATE contractors SET ContractorAddress = '"+ newContractorAddress +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            menuInit();
                            break;
                        }
//
                        if (editChoice.equals("17")) {
                            Scanner pCustomerName = new Scanner(System.in);
                            System.out.println("Please enter the Customer's Name: ");
                            String newCustomerName = pCustomerName.nextLine();

                            statement.executeUpdate("UPDATE projects SET CustomerName = '"+ newCustomerName +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            statement.executeUpdate("UPDATE customers SET CustomerName = '"+ newCustomerName +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");


                            menuInit();
                            break;
                        }
//
                        if (editChoice.equals("18")) {
                            Scanner pCustomerPhone = new Scanner(System.in);
                            System.out.println("Please enter the Customer's Phone: ");
                            String newCustomerPhone = pCustomerPhone.nextLine();

                            statement.executeUpdate("UPDATE customers SET CustomerPhone = '"+ newCustomerPhone +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            menuInit();
                            break;
                        }

                        if (editChoice.equals("19")) {
                            Scanner pCustomerMail = new Scanner(System.in);
                            System.out.println("Please enter the Customer's Email: ");
                            String newCustomerMail = pCustomerMail.nextLine();

                            statement.executeUpdate("UPDATE customers SET CustomerEmail = '"+ newCustomerMail +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            menuInit();
                            break;
                        }

                        if (editChoice.equals("20")) {
                            Scanner pCustomerAddress = new Scanner(System.in);
                            System.out.println("Please enter the Customer's Address: ");
                            String newCustomerAddress = pCustomerAddress.nextLine();

                            statement.executeUpdate("UPDATE customers SET CustomerAddress = '"+ newCustomerAddress +"' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");
                            menuInit();
                            break;
                        }

                        // Returns user to main menu
                        if (editChoice.equals("21")) {
                            menuInit();
                        }
                    }
                }

            }

            // View all open projects
            if (menuChoice == 4) {

                System.out.println("\n***************************************************\n");
                System.out.println("          | Poised: Open Projects |               \n");
                System.out.println("***************************************************\n");

                printOpenFromTable(statement);
                menuInit();
            }

            // Check and view overdue projects
            if (menuChoice == 5) {

                System.out.println("\n***************************************************\n");
                System.out.println("          | Poised: Overdue Projects |               \n");
                System.out.println("***************************************************\n");

                printOverdueFromTable(statement);
                menuInit();
            }

            // Fetches and prints all project data from architects table
            if (menuChoice == 6) {

            System.out.println("\n***************************************************\n");
            System.out.println("          | Poised: Architects |               \n");
            System.out.println("***************************************************\n");

            printAllFromArchitectsTable(statement);
            menuInit();
            }

            // Fetches and prints all project data from contractors table
            if (menuChoice == 7) {

            System.out.println("\n***************************************************\n");
            System.out.println("          | Poised: Contractors |               \n");
            System.out.println("***************************************************\n");

            printAllFromContractorsTable(statement);
            menuInit();
            }

            // Fetches and prints all project data from contractors table
            if (menuChoice == 8) {

            System.out.println("\n***************************************************\n");
            System.out.println("          | Poised: Customers |               \n");
            System.out.println("***************************************************\n");

            // Fetches and prints all project data from customers table
            printAllFromCustomersTable(statement);
            menuInit();
            }

            // Finalising a project and breakpoint
            if (menuChoice == 9) {

                Scanner projChoice = new Scanner(System.in);
                System.out.println("Please enter either the project number or the project name you want to finalise: ");
                String projectChoice = projChoice.nextLine();

                // Updates selected project to 'finalised'
                statement.executeUpdate("UPDATE projects SET ProjectStatus = 'Finalised' WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");

                ResultSet search = statement.executeQuery("SELECT ProjectNumber, ProjectName, CustomerName, CustomerPhone, CustomerEmail, CustomerAddress FROM customers WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");

                while (search.next()) {

                    search.getString("ProjectNumber");
                    search.getString("ProjectName");
                    search.getString("CustomerName");
                    search.getString("CustomerPhone");
                    search.getString("CustomerEmail");
                    search.getString("CustomerAddress");

                    // Inserts row into invoices table
                    statement.executeUpdate("INSERT INTO invoices (ProjectNumber, ProjectName, CustomerName, CustomerPhone, CustomerEmail, CustomerAddress, AmountDue, DateCompleted) VALUES ('" + search.getString("ProjectNumber") + "', '" + search.getString("ProjectName") + "', '" + search.getString("CustomerName") + "', '" + search.getString("CustomerPhone") + "', '" + search.getString("CustomerEmail") + "', '" + search.getString("CustomerAddress") + "', 99999,  CURDATE())");

                    break;
                }

                // Fetches and inserts specific customer details into the invoices row
                ResultSet amountStillDue = statement.executeQuery("SELECT TotalFee, AmountPaid, ProjectStatus FROM projects WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");

                while (amountStillDue.next()) {

                    double calcAmountOwed = amountStillDue.getDouble("TotalFee") - amountStillDue.getDouble("AmountPaid");

                    statement.executeUpdate("UPDATE invoices SET AmountDue = " + calcAmountOwed + " WHERE ProjectName = '" + projectChoice + "' OR ProjectNumber = '" + projectChoice + "'");

                    break;
                }

                System.out.println("This project has been finalised. If there is an outstanding balance, an invoice has been generated in the 'Invoices' table");

                menuInit();
                break;
            }

            if (menuChoice == 10) {

                System.out.println("\n***************************************************\n");
                System.out.println("          | Poised: Invoices |               \n");
                System.out.println("***************************************************\n");

                // Fetches and prints all project data from invoices table
                printInvoicesTable(statement);
                menuInit();
            }

            // Exits program
            if (menuChoice == 0) {
                System.out.println("| Thanks for using the Poised Project Management System. Goodbye! |");
                break;
            }

            // Closing database connection
            results.close();
            statement.close();
            connection.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    // Print method to print all current data in projects table
    public static void printAllFromTable(Statement statement) throws SQLException {

        ResultSet results = statement.executeQuery("SELECT ProjectNumber, ProjectName, BuildingType, ProjectAddress, ERF, TotalFee, AmountPaid, Deadline, ArchitectName, ContractorName, CustomerName, ProjectStatus FROM projects");

        while (results.next()) {
            System.out.println(
                    results.getInt("ProjectNumber") + ", "
                            + results.getString("ProjectName") + ", "
                            + results.getString("BuildingType") + ", "
                            + results.getString("ProjectAddress") + ", "
                            + results.getString("ERF") + ", "
                            + results.getLong("TotalFee") + ", "
                            + results.getLong("AmountPaid") + ", "
                            + results.getDate("Deadline") + ", "
                            + results.getString("ArchitectName") + ", "
                            + results.getString("ContractorName") + ", "
                            + results.getString("CustomerName") + ", "
                            + results.getString("ProjectStatus")
            );
        }
    }

    // Print method to print all current data in architects table
    public static void printAllFromArchitectsTable(Statement statement) throws SQLException {

        ResultSet results = statement.executeQuery("SELECT ProjectNumber, ProjectName, ArchitectName, ArchitectPhone, ArchitectEmail, ArchitectAddress FROM architects");

        while (results.next()) {
            System.out.println(
                    results.getInt("ProjectNumber") + ", "
                            + results.getString("ProjectName") + ", "
                            + results.getString("ArchitectName") + ", "
                            + results.getString("ArchitectPhone") + ", "
                            + results.getString("ArchitectEmail") + ", "
                            + results.getString("ArchitectAddress")
            );
        }
    }

    // Print method to print all current data in contractors table
    public static void printAllFromContractorsTable(Statement statement) throws SQLException {

        ResultSet results = statement.executeQuery("SELECT ProjectNumber, ProjectName, ContractorName, ContractorPhone, ContractorEmail, ContractorAddress FROM contractors");

        while (results.next()) {
            System.out.println(
                    results.getInt("ProjectNumber") + ", "
                            + results.getString("ProjectName") + ", "
                            + results.getString("ContractorName") + ", "
                            + results.getString("ContractorPhone") + ", "
                            + results.getString("ContractorEmail") + ", "
                            + results.getString("ContractorAddress")
            );
        }
    }

    // Print method to print all current data in customers table
    public static void printAllFromCustomersTable(Statement statement) throws SQLException {

        ResultSet results = statement.executeQuery("SELECT ProjectNumber, ProjectName, CustomerName, CustomerPhone, CustomerEmail, CustomerAddress FROM customers");

        while (results.next()) {
            System.out.println(
                    results.getInt("ProjectNumber") + ", "
                            + results.getString("ProjectName") + ", "
                            + results.getString("CustomerName") + ", "
                            + results.getString("CustomerPhone") + ", "
                            + results.getString("CustomerEmail") + ", "
                            + results.getString("CustomerAddress")
            );
        }
    }

    // Print method to print all current data in invoices table
    public static void printInvoicesTable(Statement statement) throws SQLException {

        ResultSet results = statement.executeQuery("SELECT ProjectNumber, ProjectName, CustomerName, CustomerPhone, CustomerEmail, CustomerAddress, AmountDue, DateCompleted FROM invoices");

        while (results.next()) {
            System.out.println(
                    results.getInt("ProjectNumber") + ", "
                            + results.getString("ProjectName") + ", "
                            + results.getString("CustomerName") + ", "
                            + results.getString("CustomerPhone") + ", "
                            + results.getString("CustomerEmail") + ", "
                            + results.getString("CustomerAddress") + ", "
                            + "R" + results.getLong("AmountDue") + ", "
                            + results.getDate("DateCompleted")
            );
        }
    }

    // Print method to print all overdue projects in table
    public static void printOverdueFromTable(Statement statement) throws SQLException {

        ResultSet results = statement.executeQuery("SELECT ProjectNumber, ProjectName, BuildingType, ProjectAddress, ERF, TotalFee, AmountPaid, Deadline, ArchitectName, ContractorName, CustomerName, ProjectStatus FROM projects WHERE Deadline < CURDATE()");

        while (results.next()) {
            System.out.println(
                    results.getInt("ProjectNumber") + ", "
                            + results.getString("ProjectName") + ", "
                            + results.getString("BuildingType") + ", "
                            + results.getString("ProjectAddress") + ", "
                            + results.getString("ERF") + ", "
                            + results.getLong("TotalFee") + ", "
                            + results.getLong("AmountPaid") + ", "
                            + results.getDate("Deadline") + ", "
                            + results.getString("ArchitectName") + ", "
                            + results.getString("ContractorName") + ", "
                            + results.getString("CustomerName") + ", "
                            + results.getString("ProjectStatus")
            );
        }
    }

    // Print method to print all open projects in table
    public static void printOpenFromTable(Statement statement) throws SQLException {

        ResultSet results = statement.executeQuery("SELECT ProjectNumber, ProjectName, BuildingType, ProjectAddress, ERF, TotalFee, AmountPaid, Deadline, ArchitectName, ContractorName, CustomerName, ProjectStatus FROM projects WHERE ProjectStatus = 'Open'");

        while (results.next()) {
            System.out.println(
                    results.getInt("ProjectNumber") + ", "
                            + results.getString("ProjectName") + ", "
                            + results.getString("BuildingType") + ", "
                            + results.getString("ProjectAddress") + ", "
                            + results.getString("ERF") + ", "
                            + results.getLong("TotalFee") + ", "
                            + results.getLong("AmountPaid") + ", "
                            + results.getDate("Deadline") + ", "
                            + results.getString("ArchitectName") + ", "
                            + results.getString("ContractorName") + ", "
                            + results.getString("CustomerName") + ", "
                            + results.getString("ProjectStatus")
            );
        }
    }
}