package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import datastructure.LinkedList;
import model.Admin;

/**
 * Service class for managing admin users
 */
public class AdminService {
    private LinkedList<Admin> admins;
    private int nextId;
    private static final String ADMIN_FILE = "data/Admin.txt";

    public AdminService() {
        this.admins = new LinkedList<>();
        this.nextId = 1;
        loadAdminsFromFile();
        
        // Ensure there's at least one admin in the system
        if (admins.isEmpty()) {
            addAdmin("Admin", "admin", "admin123");
        }
    }

    /**
     * Add a new admin
     */
    public Admin addAdmin(String name, String username, String password) {
        Admin admin = new Admin(nextId++, name, username, password);
        admins.add(admin);
        saveAdmins();
        return admin;
    }

    /**
     * Find an admin by username
     */
    public Admin findAdminByUsername(String username) {
        return admins.find(admin -> admin.getUsername().equals(username));
    }

    /**
     * Login as admin
     */
    public Admin login(String username, String password) {
        Admin admin = findAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }

    /**
     * Load admins from file
     */
    private void loadAdminsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ADMIN_FILE))) {
            String line;
            int maxId = 0;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String username = parts[2];
                    String password = parts[3];
                    
                    Admin admin = new Admin(id, name, username, password);
                    admins.add(admin);
                    
                    if (id > maxId) {
                        maxId = id;
                    }
                }
            }
            
            nextId = maxId + 1;
        } catch (IOException e) {
            // File might not exist yet, which is fine
            System.out.println("Admin file not found. Creating default admin.");
        }
    }    /**
     * Save admins to file
     */
    private void saveAdmins() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADMIN_FILE))) {
            Object[] objArray = admins.toArray();
            for (Object obj : objArray) {
                Admin admin = (Admin) obj;
                writer.write(admin.getId() + "," + admin.getName() + "," + 
                             admin.getUsername() + "," + admin.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving admins: " + e.getMessage());
        }
    }
}
