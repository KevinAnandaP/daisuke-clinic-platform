package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;

import datastructure.Queue;
import model.Appointment;

/**
 * Service class for managing appointments using a Queue
 */
public class AppointmentService {
    private Queue<Appointment> appointmentQueue;
    private DiagnosisService diagnosisService;
    private int nextId;
    private static final String APPOINTMENT_FILE = "data/Appointment.txt";

    public AppointmentService(DiagnosisService diagnosisService) {
        this.appointmentQueue = new Queue<>();
        this.diagnosisService = diagnosisService;
        this.nextId = 1;
        loadAppointmentsFromFile();
    }

    public AppointmentService() {
        this.appointmentQueue = new Queue<>();
        this.nextId = 1;
        loadAppointmentsFromFile();
    }
    
    public void setDiagnosisService(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    /**
     * Schedule a new appointment
     */
    public Appointment scheduleAppointment(int patientId, int doctorId, LocalDateTime appointmentTime) {
        // Validate appointment time
        if (!isValidAppointmentTime(appointmentTime)) {
            return null;
        }

        // Check for conflicts
        if (hasTimeConflict(doctorId, appointmentTime) || hasTimeConflict(patientId, appointmentTime, true)) {
            return null;
        }

        Appointment appointment = new Appointment(nextId++, patientId, doctorId, appointmentTime);
        appointmentQueue.enqueue(appointment);
        saveAppointments();
        return appointment;
    }    /**
     * Process the next appointment in the queue
     */
    public Appointment processNextAppointment() {
        return processNextAppointment("", "", "");
    }
    
    /**
     * Process the next appointment in the queue with diagnosis information
     */
    public Appointment processNextAppointment(String complaint, String diagnosis, String medication) {
        if (appointmentQueue.isEmpty()) {
            return null;
        }
        
        Appointment appointment = appointmentQueue.dequeue();
        appointment.setCompleted(true);
        
        // Save to Appointment
        appointment.setComplaint(complaint);
        appointment.setDiagnosis(diagnosis);
        appointment.setMedication(medication);
        
        // Save to Diagnosis.txt if diagnosis service is available
        if (diagnosisService != null && !complaint.isEmpty() && !diagnosis.isEmpty()) {
            diagnosisService.addDiagnosis(
                appointment.getAppointmentId(),
                appointment.getPatientId(),
                appointment.getDoctorId(),
                complaint,
                diagnosis,
                medication
            );
        }
        
        saveAppointments();
        return appointment;
    }/**
     * View upcoming appointments
     */
    public Appointment[] viewUpcomingAppointments() {
        Object[] objArray = appointmentQueue.toArray();
        Appointment[] result = new Appointment[objArray.length];
        for (int i = 0; i < objArray.length; i++) {
            result[i] = (Appointment) objArray[i];
        }
        return result;
    }    /**
     * Get upcoming appointments for a specific doctor
     */
    public Appointment[] getDoctorAppointments(int doctorId) {
        Object[] objArray = appointmentQueue.toArray();
        Appointment[] allAppointments = new Appointment[objArray.length];
        for (int i = 0; i < objArray.length; i++) {
            allAppointments[i] = (Appointment) objArray[i];
        }
        
        int count = 0;
        
        // Count doctor's appointments
        for (Appointment appointment : allAppointments) {
            if (appointment.getDoctorId() == doctorId) {
                count++;
            }
        }
        
        // Create and fill result array
        Appointment[] result = new Appointment[count];
        int index = 0;
        for (Appointment appointment : allAppointments) {
            if (appointment.getDoctorId() == doctorId) {
                result[index++] = appointment;
            }
        }
        
        return result;
    }    /**
     * Get upcoming appointments for a specific patient
     */
    public Appointment[] getPatientAppointments(int patientId) {
        Object[] objArray = appointmentQueue.toArray();
        Appointment[] allAppointments = new Appointment[objArray.length];
        for (int i = 0; i < objArray.length; i++) {
            allAppointments[i] = (Appointment) objArray[i];
        }
        
        int count = 0;
        
        // Count patient's appointments
        for (Appointment appointment : allAppointments) {
            if (appointment.getPatientId() == patientId) {
                count++;
            }
        }
        
        // Create and fill result array
        Appointment[] result = new Appointment[count];
        int index = 0;
        for (Appointment appointment : allAppointments) {
            if (appointment.getPatientId() == patientId) {
                result[index++] = appointment;
            }
        }
        
        return result;
    }

    /**
     * Check if the appointment time is valid
     */
    private boolean isValidAppointmentTime(LocalDateTime time) {
        // Check if time is in the future
        if (time.isBefore(LocalDateTime.now())) {
            return false;
        }
        
        // Check if time is within one year from now
        if (time.isAfter(LocalDateTime.now().plusYears(1))) {
            return false;
        }
        
        // Check if time is within clinic hours (7:00 to 22:00)
        LocalTime appointmentTime = time.toLocalTime();
        LocalTime openingTime = LocalTime.of(7, 0);
        LocalTime closingTime = LocalTime.of(22, 0);
        
        return !appointmentTime.isBefore(openingTime) && !appointmentTime.isAfter(closingTime);
    }    /**
     * Check if there's a time conflict for the doctor
     */
    private boolean hasTimeConflict(int doctorId, LocalDateTime time) {
        Object[] objArray = appointmentQueue.toArray();
        Appointment[] appointments = new Appointment[objArray.length];
        for (int i = 0; i < objArray.length; i++) {
            appointments[i] = (Appointment) objArray[i];
        }
        
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId() == doctorId && 
                appointment.getAppointmentTime().equals(time)) {
                return true;
            }
        }
        return false;
    }    /**
     * Check if there's a time conflict for the patient
     */
    private boolean hasTimeConflict(int patientId, LocalDateTime time, boolean isPatient) {
        Object[] objArray = appointmentQueue.toArray();
        Appointment[] appointments = new Appointment[objArray.length];
        for (int i = 0; i < objArray.length; i++) {
            appointments[i] = (Appointment) objArray[i];
        }
        
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId() == patientId && 
                appointment.getAppointmentTime().equals(time)) {
                return true;
            }
        }
        return false;
    }    /**
     * Load appointments from file
     */
    private void loadAppointmentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENT_FILE))) {
            String line;
            int maxId = 0;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",(?=([^\\\\]|\\\\[^,])*$)"); // Split by commas not escaped with backslash
                if (parts.length >= 5) {
                    int id = Integer.parseInt(parts[0]);
                    int patientId = Integer.parseInt(parts[1]);
                    int doctorId = Integer.parseInt(parts[2]);
                    LocalDateTime time = LocalDateTime.parse(parts[3]);
                    boolean completed = Boolean.parseBoolean(parts[4]);
                    
                    // Get diagnosis data if available
                    String complaint = parts.length > 5 ? unescapeCommas(parts[5]) : "";
                    String diagnosis = parts.length > 6 ? unescapeCommas(parts[6]) : "";
                    String medication = parts.length > 7 ? unescapeCommas(parts[7]) : "";
                    
                    Appointment appointment = new Appointment(id, patientId, doctorId, time);
                    appointment.setCompleted(completed);
                    appointment.setComplaint(complaint);
                    appointment.setDiagnosis(diagnosis);
                    appointment.setMedication(medication);
                    
                    // Only add to queue if not completed
                    if (!completed) {
                        appointmentQueue.enqueue(appointment);
                    }
                    
                    if (id > maxId) {
                        maxId = id;
                    }
                }
            }
            
            nextId = maxId + 1;
        } catch (IOException e) {
            // File might not exist yet, which is fine
            System.out.println("Appointment file not found. Starting with empty queue.");
        }
    }
    
    /**
     * Helper method to unescape commas in text fields from file storage
     */
    private String unescapeCommas(String text) {
        if (text == null) return "";
        return text.replace("\\,", ",");
    }/**
     * Save appointments to file
     */
    private void saveAppointments() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_FILE))) {
            Object[] objArray = appointmentQueue.toArray();
            for (Object obj : objArray) {
                Appointment appointment = (Appointment) obj;
                writer.write(appointment.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving appointments: " + e.getMessage());
        }
    }
}
