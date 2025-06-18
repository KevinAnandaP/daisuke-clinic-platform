package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.function.Predicate;

import datastructure.LinkedList;
import model.Diagnosis;

/**
 * Service class for managing diagnosis records
 */
public class DiagnosisService {
    private LinkedList<Diagnosis> diagnosisList;
    private int nextId;
    private static final String DIAGNOSIS_FILE = "data/Diagnosis.txt";

    public DiagnosisService() {
        this.diagnosisList = new LinkedList<>();
        this.nextId = 1;
        loadDiagnosisFromFile();
    }

    /**
     * Add a new diagnosis record
     */
    public Diagnosis addDiagnosis(int appointmentId, int patientId, int doctorId, 
                                 String complaint, String diagnosis, String medication) {
        LocalDateTime now = LocalDateTime.now();
        Diagnosis diagnosisRecord = new Diagnosis(nextId++, appointmentId, patientId, doctorId, 
                                                  now, complaint, diagnosis, medication);
        diagnosisList.add(diagnosisRecord);
        saveDiagnosis();
        return diagnosisRecord;
    }    /**
     * Get diagnosis records for a specific patient
     */
    public Diagnosis[] getPatientDiagnosis(int patientId) {
        // First, get all records as Object array
        Object[] allRecords = diagnosisList.toArray();
        
        // Count matching records
        int count = 0;
        for (Object obj : allRecords) {
            Diagnosis diagnosis = (Diagnosis) obj;
            if (diagnosis.getPatientId() == patientId) {
                count++;
            }
        }
        
        // Create and fill result array
        Diagnosis[] result = new Diagnosis[count];
        int index = 0;
        
        for (Object obj : allRecords) {
            Diagnosis diagnosis = (Diagnosis) obj;
            if (diagnosis.getPatientId() == patientId) {
                result[index++] = diagnosis;
            }
        }
        
        return result;
    }

    /**
     * Get diagnosis records for a specific doctor
     */
    public Diagnosis[] getDoctorDiagnosis(int doctorId) {
        // First, get all records as Object array
        Object[] allRecords = diagnosisList.toArray();
        
        // Count matching records
        int count = 0;
        for (Object obj : allRecords) {
            Diagnosis diagnosis = (Diagnosis) obj;
            if (diagnosis.getDoctorId() == doctorId) {
                count++;
            }
        }
        
        // Create and fill result array
        Diagnosis[] result = new Diagnosis[count];
        int index = 0;
        
        for (Object obj : allRecords) {
            Diagnosis diagnosis = (Diagnosis) obj;
            if (diagnosis.getDoctorId() == doctorId) {
                result[index++] = diagnosis;
            }
        }
        
        return result;
    }    /**
     * Get diagnosis record for a specific appointment
     */
    public Diagnosis getDiagnosisByAppointmentId(int appointmentId) {
        // Use the find method with a predicate
        Predicate<Diagnosis> findByAppointmentId = diagnosis -> diagnosis.getAppointmentId() == appointmentId;
        return diagnosisList.find(findByAppointmentId);
    }

    /**
     * Get all diagnosis records
     */
    public Diagnosis[] getAllDiagnosis() {
        Object[] objects = diagnosisList.toArray();
        Diagnosis[] result = new Diagnosis[objects.length];
        
        for (int i = 0; i < objects.length; i++) {
            result[i] = (Diagnosis) objects[i];
        }
        
        return result;
    }

    /**
     * Load diagnosis records from file
     */
    private void loadDiagnosisFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DIAGNOSIS_FILE))) {
            String line;
            int maxId = 0;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",(?=([^\\\\]|\\\\[^,])*$)"); // Split by commas not escaped with backslash
                if (parts.length >= 8) {
                    int id = Integer.parseInt(parts[0]);
                    int appointmentId = Integer.parseInt(parts[1]);
                    int patientId = Integer.parseInt(parts[2]);
                    int doctorId = Integer.parseInt(parts[3]);
                    LocalDateTime time = LocalDateTime.parse(parts[4]);
                    
                    String complaint = unescapeCommas(parts[5]);
                    String diagnosis = unescapeCommas(parts[6]);
                    String medication = unescapeCommas(parts[7]);
                    
                    Diagnosis diagnosisRecord = new Diagnosis(id, appointmentId, patientId, doctorId, 
                                                            time, complaint, diagnosis, medication);
                    diagnosisList.add(diagnosisRecord);
                    
                    if (id > maxId) {
                        maxId = id;
                    }
                }
            }
            
            nextId = maxId + 1;
        } catch (IOException e) {
            // File might not exist yet, which is fine
            System.out.println("Diagnosis file not found. Starting with empty list.");
        }
    }    /**
     * Save diagnosis records to file
     */
    private void saveDiagnosis() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DIAGNOSIS_FILE))) {
            Object[] objects = diagnosisList.toArray();
            
            for (Object obj : objects) {
                Diagnosis diagnosis = (Diagnosis) obj;
                writer.write(diagnosis.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving diagnosis records: " + e.getMessage());
        }
    }
    
    /**
     * Helper method to unescape commas in text fields from file storage
     */
    private String unescapeCommas(String text) {
        if (text == null) return "";
        return text.replace("\\,", ",");
    }
}
