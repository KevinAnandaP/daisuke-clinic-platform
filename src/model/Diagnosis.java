package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a medical diagnosis record in the clinic
 */
public class Diagnosis {
    private int diagnosisId;
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private LocalDateTime diagnosisTime;
    private String complaint;
    private String diagnosis;
    private String medication;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Diagnosis(int diagnosisId, int appointmentId, int patientId, int doctorId, LocalDateTime diagnosisTime, 
                    String complaint, String diagnosis, String medication) {
        this.diagnosisId = diagnosisId;
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.diagnosisTime = diagnosisTime;
        this.complaint = complaint;
        this.diagnosis = diagnosis;
        this.medication = medication;
    }

    public int getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisId = diagnosisId;
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

    public LocalDateTime getDiagnosisTime() {
        return diagnosisTime;
    }

    public void setDiagnosisTime(LocalDateTime diagnosisTime) {
        this.diagnosisTime = diagnosisTime;
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
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Diagnosis ID: ").append(diagnosisId)
          .append(", Appointment ID: ").append(appointmentId)
          .append(", Date: ").append(diagnosisTime.format(FORMATTER))
          .append("\nComplaint: ").append(complaint)
          .append("\nDiagnosis: ").append(diagnosis)
          .append("\nMedication: ").append(medication);
        
        return sb.toString();
    }

    /**
     * Convert diagnosis to a string format for saving to file
     */
    public String toFileString() {
        return diagnosisId + "," + appointmentId + "," + patientId + "," + doctorId + "," + 
               diagnosisTime.toString() + "," + escapeCommas(complaint) + "," + 
               escapeCommas(diagnosis) + "," + escapeCommas(medication);
    }
    
    /**
     * Helper method to escape commas in text fields for file storage
     */
    private String escapeCommas(String text) {
        if (text == null) return "";
        return text.replace(",", "\\,");
    }
}
