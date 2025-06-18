package model;

import java.time.LocalDateTime;

/**
 * Doctor class extending User with additional doctor-specific attributes
 */
public class Doctor extends User {
    private String specialty;
    private LocalDateTime loginTime;

    public Doctor(int id, String name, String specialty, String username, String password) {
        super(id, name, username, password);
        this.specialty = specialty;
        this.loginTime = null; // Not logged in by default
    }

    public Doctor(int id, String name, String specialty, LocalDateTime loginTime, String username, String password) {
        super(id, name, username, password);
        this.specialty = specialty;
        this.loginTime = loginTime;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public boolean isLoggedIn() {
        return loginTime != null;
    }

    public String toString() {
        String status = isLoggedIn() ? "Logged in since: " + loginTime : "Not logged in";
        return super.toString() + ", Specialty: " + specialty + ", " + status;
    }

    /**
     * Convert doctor to a string format for saving to file
     */
    public String toFileString() {
        String loginTimeStr = loginTime != null ? loginTime.toString() : "null";
        return getId() + "," + getName() + "," + specialty + "," + loginTimeStr + "," + getUsername() + "," + getPassword();
    }
}
