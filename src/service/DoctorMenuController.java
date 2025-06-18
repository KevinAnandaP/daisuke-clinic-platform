package service;

import model.Appointment;
import model.Doctor;
import model.Patient;
import util.InputUtil;

/**
 * Menu controller for doctor users
 */
public class DoctorMenuController extends MenuController {
    private Doctor currentDoctor;
    
    public DoctorMenuController(Doctor doctor, AdminService adminService, DoctorService doctorService, 
                               PatientService patientService, AppointmentService appointmentService,
                               AuthService authService) {
        super(adminService, doctorService, patientService, appointmentService, authService);
        this.currentDoctor = doctor;
    }
    
    public boolean displayMenu() {
        System.out.println("\n===== Doctor Menu =====");
        System.out.println("Welcome, Dr. " + currentDoctor.getName() + "!");
          String[] options = {
            "View My Upcoming Appointments",
            "Process Next Appointment",
            "View Patient Medical Records",
            "Logout"
        };
        
        int choice = InputUtil.getMenuChoice("Select an option:", options);
          switch (choice) {
            case 1:
                viewMyAppointments();
                break;
            case 2:
                processNextAppointment();
                break;
            case 3:
                viewPatientMedicalRecords();
                break;
            case 4:
                System.out.println("Logging out...");
                doctorService.logoutDoctor(currentDoctor.getId());
                return false;
        }
        
        return true;
    }
    
    private void viewMyAppointments() {
        System.out.println("\n----- My Upcoming Appointments -----");
        Appointment[] appointments = appointmentService.getDoctorAppointments(currentDoctor.getId());
        
        if (appointments.length == 0) {
            System.out.println("You have no upcoming appointments.");
        } else {
            for (Appointment appointment : appointments) {
                Patient patient = patientService.findPatientById(appointment.getPatientId());
                String patientName = (patient != null) ? patient.getName() : "Unknown";
                
                System.out.println(appointment);
                System.out.println("  Patient: " + patientName);
                System.out.println();
            }
            System.out.println("Total upcoming appointments: " + appointments.length);
        }
    }
      private void processNextAppointment() {
        System.out.println("\n----- Process Next Appointment -----");
        
        // Get doctor's appointments
        Appointment[] appointments = appointmentService.getDoctorAppointments(currentDoctor.getId());
        
        if (appointments.length == 0) {
            System.out.println("You have no upcoming appointments to process.");
            return;
        }
        
        // Show the next appointment
        Appointment nextAppointment = appointments[0];
        Patient patient = patientService.findPatientById(nextAppointment.getPatientId());
        String patientName = (patient != null) ? patient.getName() : "Unknown";
        
        System.out.println("Next appointment:");
        System.out.println(nextAppointment);
        System.out.println("  Patient: " + patientName);
        
        // Confirm processing
        boolean process = InputUtil.getYesNo("Process this appointment?");
        if (process) {
            // Collect diagnosis information
            System.out.println("\n----- Diagnosis and Medication Information -----");
            System.out.println("Patient: " + patientName + " (ID: " + patient.getId() + ")");
            
            // Get patient complaint
            String complaint = InputUtil.getString("Patient Complaint/Symptoms: ");
            
            // Get diagnosis
            String diagnosis = InputUtil.getString("Doctor's Diagnosis: ");
            
            // Get medication
            String medication = InputUtil.getString("Prescribed Medication: ");
              // Process with diagnosis information
            appointmentService.processNextAppointment(complaint, diagnosis, medication);
            
            System.out.println("\nAppointment processed successfully.");
            System.out.println("Diagnosis and medication information has been saved.");
            
            // Notify if diagnosis service is not available
            if (diagnosisService == null) {
                System.out.println("Note: Medical records service is not available. Diagnosis information was saved only with the appointment.");
            }
        } else {
            System.out.println("Appointment processing cancelled.");
        }
    }
    
    /**
     * View medical records of patients
     */
    private void viewPatientMedicalRecords() {
        System.out.println("\n----- Patient Medical Records -----");
        
        if (diagnosisService == null) {
            System.out.println("Medical records service is not available at this time.");
            return;
        }
        
        // Get patient ID to look up
        int patientId = InputUtil.getInt("Enter patient ID to view medical records");
        
        // Verify patient exists
        Patient patient = patientService.findPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient not found with ID: " + patientId);
            return;
        }
        
        // Get all diagnosis records for this patient
        model.Diagnosis[] diagnosisRecords = diagnosisService.getPatientDiagnosis(patientId);
        
        if (diagnosisRecords.length == 0) {
            System.out.println("No medical records found for patient: " + patient.getName());
            return;
        }
        
        System.out.println("Medical Records for " + patient.getName() + " (ID: " + patient.getId() + "):");
        System.out.println("---------------------------------------------");
        
        for (model.Diagnosis record : diagnosisRecords) {
            // Get doctor name
            Doctor doctor = doctorService.findDoctorById(record.getDoctorId());
            String doctorName = (doctor != null) ? "Dr. " + doctor.getName() : "Unknown Doctor";
            
            // Format date nicely
            String dateTime = record.getDiagnosisTime().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            
            System.out.println("Date: " + dateTime);
            System.out.println("Doctor: " + doctorName);
            System.out.println("Complaint: " + record.getComplaint());
            System.out.println("Diagnosis: " + record.getDiagnosis());
            System.out.println("Prescribed Medication: " + record.getMedication());
            System.out.println("---------------------------------------------");
        }
        
        System.out.println("Total records: " + diagnosisRecords.length);
    }
}
