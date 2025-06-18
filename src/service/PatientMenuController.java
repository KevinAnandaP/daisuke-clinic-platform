package service;

import java.time.LocalDateTime;

import model.Appointment;
import model.Doctor;
import model.Patient;
import util.InputUtil;

/**
 * Menu controller for patient users
 */
public class PatientMenuController extends MenuController {
    private Patient currentPatient;
    
    public PatientMenuController(Patient patient, AdminService adminService, DoctorService doctorService, 
                               PatientService patientService, AppointmentService appointmentService,
                               AuthService authService) {
        super(adminService, doctorService, patientService, appointmentService, authService);
        this.currentPatient = patient;
    }
      public boolean displayMenu() {
        System.out.println("\n===== Patient Menu =====");
        System.out.println("Welcome, " + currentPatient.getName() + "!");
        
        String[] options = {
            "Schedule New Appointment",
            "View My Appointments",
            "View My Medical History",
            "Update My Profile",
            "Logout"
        };
        
        int choice = InputUtil.getMenuChoice("Select an option:", options);
        
        switch (choice) {
            case 1:
                scheduleAppointment();
                break;
            case 2:
                viewMyAppointments();
                break;
            case 3:
                viewMedicalHistory();
                break;
            case 4:
                updateProfile();
                break;
            case 5:
                System.out.println("Logging out...");
                return false;
        }
        
        return true;
    }
    
    private void scheduleAppointment() {
        System.out.println("\n----- Schedule New Appointment -----");
        
        // Display available doctors
        Doctor[] doctors = doctorService.getAllDoctors();
        if (doctors.length == 0) {
            System.out.println("No doctors available in the system. Please try again later.");
            return;
        }
        
        System.out.println("Available Doctors:");
        for (Doctor doctor : doctors) {
            System.out.println(doctor.getId() + ": " + doctor.getName() + " (" + doctor.getSpecialty() + ")");
        }
        
        int doctorId = InputUtil.getInt("Enter doctor ID");
        
        // Verify doctor exists
        Doctor selectedDoctor = doctorService.findDoctorById(doctorId);
        if (selectedDoctor == null) {
            System.out.println("Invalid doctor ID. Please try again.");
            return;
        }
        
        // Get appointment time
        LocalDateTime appointmentTime = InputUtil.getDateTime("Enter appointment date", "Enter appointment time");
        
        Appointment appointment = appointmentService.scheduleAppointment(currentPatient.getId(), doctorId, appointmentTime);
        if (appointment != null) {
            System.out.println("Appointment scheduled successfully with ID: " + appointment.getAppointmentId());
        } else {
            System.out.println("Failed to schedule appointment. Please check the following:");
            System.out.println("- Appointment time must be between 7:00 and 22:00");
            System.out.println("- Appointment must be within one year from now");
            System.out.println("- You and the doctor must not have another appointment at the same time");
        }
    }
      private void viewMyAppointments() {
        System.out.println("\n----- My Appointments -----");
        Appointment[] appointments = appointmentService.getPatientAppointments(currentPatient.getId());
        
        if (appointments.length == 0) {
            System.out.println("You have no upcoming appointments.");
        } else {
            for (Appointment appointment : appointments) {
                Doctor doctor = doctorService.findDoctorById(appointment.getDoctorId());
                String doctorName = (doctor != null) ? doctor.getName() : "Unknown";
                
                System.out.println(appointment);
                System.out.println("  Doctor: " + doctorName);
                
                // Show diagnosis information if the appointment is completed
                if (appointment.isCompleted() && !appointment.getDiagnosis().isEmpty()) {
                    System.out.println("\n  ----- Medical Record -----");
                    System.out.println("  Complaint: " + appointment.getComplaint());
                    System.out.println("  Diagnosis: " + appointment.getDiagnosis());
                    System.out.println("  Prescribed Medication: " + appointment.getMedication());
                }
                
                System.out.println();
            }
            System.out.println("Total appointments: " + appointments.length);
        }
    }
    
    /**
     * View patient's medical history (diagnosis records)
     */
    private void viewMedicalHistory() {
        System.out.println("\n----- My Medical History -----");
        
        if (diagnosisService == null) {
            System.out.println("Medical history service is not available at this time.");
            return;
        }
        
        // Get all diagnosis records for this patient
        model.Diagnosis[] diagnosisRecords = diagnosisService.getPatientDiagnosis(currentPatient.getId());
        
        if (diagnosisRecords.length == 0) {
            System.out.println("You have no medical history records yet.");
            return;
        }
        
        System.out.println("Your Medical History:");
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
    
    private void updateProfile() {
        System.out.println("\n----- Update My Profile -----");
        
        String[] options = {
            "Update Phone Number",
            "Update Address",
            "Update Password",
            "Back to Main Menu"
        };
        
        int choice = InputUtil.getMenuChoice("Select an option to update:", options);
        
        switch (choice) {
            case 1:
                String newPhone = InputUtil.getRequiredString("Enter new phone number");
                currentPatient.setPhoneNumber(newPhone);
                System.out.println("Phone number updated successfully.");
                break;
            case 2:
                String newAddress = InputUtil.getRequiredString("Enter new address");
                currentPatient.setAddress(newAddress);
                System.out.println("Address updated successfully.");
                break;
            case 3:
                String newPassword = InputUtil.getRequiredString("Enter new password");
                currentPatient.setPassword(newPassword);
                System.out.println("Password updated successfully.");
                break;
            case 4:
                return;
        }
    }
}
