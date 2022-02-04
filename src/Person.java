public class Person {

    // Person attributes
    String name;
    String telephone;
    String email;
    String address;

    // Default person constructor
    public Person() {

    }

    // Constructor with arguments
    public Person(String name, String telephone, String email, String address) {

        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
    }

    @Override
    // toString Method to print person details
    public String toString() {

        String println = "\nName: " + name;
        println += "\nTelephone Number: " + telephone;
        println += "\nEmail Address: " + email;
        println += "\nAddress: " + address;
        return println;
    }

    // Getters and setters for person object
    public String getName() {
        return name;
    }

    public String getTelephone() { return telephone; }

    public  String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}


