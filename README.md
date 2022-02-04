# Poised-Management-System-DB

# Hyperion Development - Software Engineering Course
## Build a Project Management System with a Database

### Overview of this Task

This task focused on Object Oriented Programming (OOP) in java. We were asked to build a project management system program for a small structural engineering firm called "Poised".

The company wanted to be able to add new projects, edit existing projects, and to add numerous roles and people to projects (such as architects, contractors and customers).

### Project Requirements

Each project assigned to Poised needed to contain the following:

* Project number
* Project name
* The type of building (House, apartment, etc.)
* The physical address for the project.
* ERF number.
* The total fee being charged for the project.
* The total amount paid to date.
* Deadline for the project.
* The name, telephone number, email address and physical address of the architect, contractor and customer for the project.


### Development

The first required step was to plan out and create an UML Diagram (Unified Modelling Language). This diagram would lay out all the classes that would be used in the development of the program. It would also list all the attributes and methods assosciated with each class.

The database was created using the below tables:

* Architects
* Contractors
* Customers
* Invoices
* Projects

The above tables would have to be updated in the database as per the user's interaction with the application.

The classes used were:

* Main
* Project
* Menu
* Person

### Operation

The program starts off with the below steps:

1) Main Menu - User chooses from a range of options
* Add new Project Details
* Update Amount Paid to Date for the Project
* Update the deadline date
* Add contractor
* Edit contractor contact number

2) If 'Add new Project Details' was selected, the user would be prompted to input the data to various attributes of the project listed in the project requirements above.

3) Another menu would then appear, allowing the user to update or edit project details or a person's contact information


### Summary

As OOP is such a vital part of java programming, it was good to work on this project. I was happy about the integration of using the MySQL Workbench datase system.






