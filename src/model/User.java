package model;

/**
 * Base class for all users in the system
 */
public abstract class User {
    private int id;
    private String name;
    private String username;
    private String password;

    public User(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name;
    }
}
