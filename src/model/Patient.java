package model;

/**
 * Patient class extending User with additional patient-specific attributes
 */
public class Patient extends User {
    private int age;
    private String address;
    private String phoneNumber;

    public Patient(int id, String name, int age, String address, String phoneNumber, String username, String password) {
        super(id, name, username, password);
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return super.toString() + ", Age: " + age + ", Address: " + address + ", Phone: " + phoneNumber;
    }

    /**
     * Convert patient to a string format for saving to file
     */
    public String toFileString() {
        return getId() + "," + getName() + "," + age + "," + address + "," + phoneNumber + "," + getUsername() + "," + getPassword();
    }
}
