package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Predicate;

import datastructure.LinkedList;
import datastructure.BinarySearchTree;
import model.Patient;

/**
 * Service class for managing patients using a Singly Linked List
 */
public class PatientService {
    private LinkedList<Patient> patients;
    private BinarySearchTree<PatientBSTWrapper> patientBST;
    private int nextId;
    private static final String PATIENT_FILE = "data/Patient.txt";

    public PatientService() {
        this.patients = new LinkedList<>();
        this.patientBST = new BinarySearchTree<>();
        this.nextId = 1;
        loadPatientsFromFile();
    }

    /**
     * Add a new patient
     */
    public Patient addPatient(String name, int age, String address, String phoneNumber, String username, String password) {
        Patient patient = new Patient(nextId++, name, age, address, phoneNumber, username, password);
        patients.add(patient);
        patientBST.insert(new PatientBSTWrapper(patient));
        savePatients();
        return patient;
    }

    /**
     * Remove a patient by ID
     */
    public boolean removePatientById(int id) {
        boolean removed = patients.remove(patient -> patient.getId() == id);
        if (removed) {
            // Note: We don't remove from BST as most BST implementations don't support easy removal
            // For a production app, this would need to be addressed
            savePatients();
        }
        return removed;
    }

    /**
     * Find a patient by name (partial match)
     */
    public Patient findPatientByName(String name) {
        return patients.find(patient -> 
            patient.getName().toLowerCase().contains(name.toLowerCase()));
    }

    /**
     * Find a patient by ID using BST
     */
    public Patient findPatientById(int id) {
        PatientBSTWrapper wrapper = patientBST.search(new PatientBSTWrapper(id));
        return wrapper != null ? wrapper.getPatient() : null;
    }

    /**
     * Find a patient by username
     */
    public Patient findPatientByUsername(String username) {
        return patients.find(patient -> 
            patient.getUsername().equals(username));
    }    /**
     * Get all patients
     */
    public Patient[] getAllPatients() {
        Object[] objArray = patients.toArray();
        Patient[] result = new Patient[objArray.length];
        for (int i = 0; i < objArray.length; i++) {
            result[i] = (Patient) objArray[i];
        }
        return result;
    }

    /**
     * Load patients from file
     */
    private void loadPatientsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATIENT_FILE))) {
            String line;
            int maxId = 0;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String address = parts[3];
                    String phoneNumber = parts[4];
                    String username = parts[5];
                    String password = parts[6];
                    
                    Patient patient = new Patient(id, name, age, address, phoneNumber, username, password);
                    patients.add(patient);
                    patientBST.insert(new PatientBSTWrapper(patient));
                    
                    if (id > maxId) {
                        maxId = id;
                    }
                }
            }
            
            nextId = maxId + 1;
        } catch (IOException e) {
            // File might not exist yet, which is fine
            System.out.println("Patient file not found. Starting with empty list.");
        }
    }

    /**
     * Save patients to file
     */
    private void savePatients() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENT_FILE))) {
            Patient[] allPatients = getAllPatients();
            for (Patient patient : allPatients) {
                writer.write(patient.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving patients: " + e.getMessage());
        }
    }

    /**
     * Display all patients using in-order traversal of BST
     */
    public void displayPatientsInOrder() {
        System.out.println("All Patients (Sorted by ID):");
        patientBST.inOrderTraversal(wrapper -> 
            System.out.println(wrapper.getPatient()));
    }
    
    /**
     * Wrapper class to make Patient objects comparable for BST by ID
     */
    private static class PatientBSTWrapper implements Comparable<PatientBSTWrapper> {
        private Patient patient;
        private int id;
        
        public PatientBSTWrapper(Patient patient) {
            this.patient = patient;
            this.id = patient.getId();
        }
        
        public PatientBSTWrapper(int id) {
            this.patient = null;
            this.id = id;
        }
        
        public Patient getPatient() {
            return patient;
        }
        
        public int compareTo(PatientBSTWrapper other) {
            return Integer.compare(this.id, other.id);
        }
    }
}
