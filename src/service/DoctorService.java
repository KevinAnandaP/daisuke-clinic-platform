package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import datastructure.LinkedList;
import model.Doctor;

/**
 * Service class for managing doctors using a Linked List
 */
public class DoctorService {
    private LinkedList<Doctor> allDoctors;      // All doctors in the system
    private LinkedList<Doctor> loggedInDoctors; // Currently logged-in doctors
    private int nextId;
    private static final String DOCTOR_FILE = "data/Doctor.txt";

    public DoctorService() {
        this.allDoctors = new LinkedList<>();
        this.loggedInDoctors = new LinkedList<>();
        this.nextId = 1;
        loadDoctorsFromFile();
    }

    /**
     * Add a new doctor to the system
     */
    public Doctor addDoctor(String name, String specialty, String username, String password) {
        Doctor doctor = new Doctor(nextId++, name, specialty, username, password);
        allDoctors.add(doctor);
        saveDoctors();
        return doctor;
    }

    /**
     * Login a doctor
     */
    public boolean loginDoctor(int doctorId, String password) {
        Doctor doctor = findDoctorById(doctorId);
        if (doctor != null && doctor.getPassword().equals(password)) {
            // Check if already logged in
            if (doctor.isLoggedIn()) {
                return true; // Already logged in
            }
            
            doctor.setLoginTime(LocalDateTime.now());
            loggedInDoctors.add(doctor);
            saveDoctors();
            return true;
        }
        return false;
    }

    /**
     * Login a doctor using username
     */
    public Doctor loginDoctorByUsername(String username, String password) {
        Doctor doctor = findDoctorByUsername(username);
        if (doctor != null && doctor.getPassword().equals(password)) {
            // Check if already logged in
            if (!doctor.isLoggedIn()) {
                doctor.setLoginTime(LocalDateTime.now());
                loggedInDoctors.add(doctor);
                saveDoctors();
            }
            return doctor;
        }
        return null;
    }

    /**
     * Logout a doctor
     */
    public boolean logoutDoctor(int doctorId) {
        Doctor doctor = findDoctorById(doctorId);
        if (doctor != null && doctor.isLoggedIn()) {
            doctor.setLoginTime(null);
            loggedInDoctors.remove(d -> d.getId() == doctorId);
            saveDoctors();
            return true;
        }
        return false;
    }

    /**
     * Find a doctor by ID
     */
    public Doctor findDoctorById(int id) {
        return allDoctors.find(doctor -> doctor.getId() == id);
    }

    /**
     * Find a doctor by username
     */
    public Doctor findDoctorByUsername(String username) {
        return allDoctors.find(doctor -> doctor.getUsername().equals(username));
    }    /**
     * Get all logged-in doctors
     */
    public Doctor[] getLoggedInDoctors() {
        Object[] objArray = loggedInDoctors.toArray();
        Doctor[] result = new Doctor[objArray.length];
        for (int i = 0; i < objArray.length; i++) {
            result[i] = (Doctor) objArray[i];
        }
        return result;
    }

    /**
     * Get all doctors
     */
    public Doctor[] getAllDoctors() {
        Object[] objArray = allDoctors.toArray();
        Doctor[] result = new Doctor[objArray.length];
        for (int i = 0; i < objArray.length; i++) {
            result[i] = (Doctor) objArray[i];
        }
        return result;
    }

    /**
     * Load doctors from file
     */
    private void loadDoctorsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DOCTOR_FILE))) {
            String line;
            int maxId = 0;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String specialty = parts[2];
                    
                    LocalDateTime loginTime = null;
                    if (!parts[3].equals("null")) {
                        loginTime = LocalDateTime.parse(parts[3]);
                    }
                    
                    String username = parts[4];
                    String password = parts[5];
                    
                    Doctor doctor = new Doctor(id, name, specialty, loginTime, username, password);
                    allDoctors.add(doctor);
                    
                    if (doctor.isLoggedIn()) {
                        loggedInDoctors.add(doctor);
                    }
                    
                    if (id > maxId) {
                        maxId = id;
                    }
                }
            }
            
            nextId = maxId + 1;
        } catch (IOException e) {
            // File might not exist yet, which is fine
            System.out.println("Doctor file not found. Starting with empty list.");
        }
    }

    /**
     * Save doctors to file
     */
    private void saveDoctors() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOCTOR_FILE))) {
            Doctor[] doctors = getAllDoctors();
            for (Doctor doctor : doctors) {
                writer.write(doctor.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving doctors: " + e.getMessage());
        }
    }
}
