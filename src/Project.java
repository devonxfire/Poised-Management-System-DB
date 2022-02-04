public class Project {

    // Attributes for Projects
    String projectNumber;
    String projectName;
    String buildingType;
    String projectAddress;
    String erf;
    long totalFee;
    long amountPaid;
    String deadline;
    Person architect;
    Person contractor;
    Person customer;
    String projectStatus;

    // Constructor
    public Project(String projectNumber, String projectName, String buildingType, String projectAddress, String erf, long totalFee, long amountPaid, String deadline, Person architect, Person contractor, Person customer, String projectStatus) {

        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.buildingType = buildingType;
        this.projectAddress = projectAddress;
        this.erf = erf;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
        this.deadline = deadline;
        this.architect = architect;
        this.contractor = contractor;
        this.customer = customer;
        this.projectStatus = projectStatus;
    }

    // toString method to print out project details to console
    public String toString() {
        String printline = "\nProject Number: " + projectNumber;
        printline += "\nProject Name: " + projectName;
        printline += "\nBuilding Type: " + buildingType;
        printline += "\nProject Physical Address: " + projectAddress;
        printline += "\nERF Number: " + erf;
        printline += "\nTotal Fee : R" + totalFee;
        printline += "\nAmount Paid: R" + amountPaid;
        printline += "\nDeadline: " + deadline;
        printline += "\n\nArchitect\n" + architect;
        printline += "\n\nContractor\n" + contractor;
        printline += "\n\nCustomer\n" + customer;
        printline += "\n\nProject Status\n" + projectStatus;
        return printline;
    }

    // Getters and setters for project attributes
    public String getProjectNumber() {
        return projectNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public String getErf() { return erf; }

    public long getTotalFee() {
        return totalFee;
    }

    public long getAmountPaid() {
        return amountPaid;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getProjectStatus() {
        return projectStatus;
    }
}
