package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing an appointment in the clinic
 */
public class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private LocalDateTime appointmentTime;
    private boolean completed;
    private String complaint;      // Patient's complaint/symptoms
    private String diagnosis;      // Doctor's diagnosis
    private String medication;     // Prescribed medication

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Appointment(int appointmentId, int patientId, int doctorId, LocalDateTime appointmentTime) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentTime = appointmentTime;
        this.completed = false;
        this.complaint = "";
        this.diagnosis = "";
        this.medication = "";
    }

    public Appointment(int appointmentId, int patientId, int doctorId, LocalDateTime appointmentTime, 
                      String complaint, String diagnosis, String medication) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentTime = appointmentTime;
        this.completed = false;
        this.complaint = complaint;
        this.diagnosis = diagnosis;
        this.medication = medication;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public boolean isCompleted() {
        return completed;
    }    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    public String getComplaint() {
        return complaint;
    }
    
    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
    
    public String getDiagnosis() {
        return diagnosis;
    }
    
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    public String getMedication() {
        return medication;
    }
    
    public void setMedication(String medication) {
        this.medication = medication;
    }    public String toString() {
        String status = completed ? "Completed" : "Scheduled";
        StringBuilder sb = new StringBuilder();
        sb.append("Appointment ID: ").append(appointmentId)
          .append(", Patient ID: ").append(patientId)
          .append(", Doctor ID: ").append(doctorId)
          .append(", Time: ").append(appointmentTime.format(FORMATTER))
          .append(", Status: ").append(status);
        
        if (completed && !diagnosis.isEmpty()) {
            sb.append("\n  Complaint: ").append(complaint)
              .append("\n  Diagnosis: ").append(diagnosis)
              .append("\n  Medication: ").append(medication);
        }
        
        return sb.toString();
    }

    /**
     * Convert appointment to a string format for saving to file
     */
    public String toFileString() {
        return appointmentId + "," + patientId + "," + doctorId + "," + 
               appointmentTime.toString() + "," + completed + "," + 
               escapeCommas(complaint) + "," + escapeCommas(diagnosis) + "," + escapeCommas(medication);
    }
    
    /**
     * Helper method to escape commas in text fields for file storage
     */
    private String escapeCommas(String text) {
        if (text == null) return "";
        return text.replace(",", "\\,");
    }
}
