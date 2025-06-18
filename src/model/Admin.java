package model;

/**
 * Admin class extending User with admin-specific functionality
 */
public class Admin extends User {
    public Admin(int id, String name, String username, String password) {
        super(id, name, username, password);
    }

    public String toString() {
        return "Admin: " + super.toString();
    }
}
