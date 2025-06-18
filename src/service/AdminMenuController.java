package service;

import java.time.LocalDateTime;

import model.Admin;
import model.Appointment;
import model.Doctor;
import model.Patient;
import util.InputUtil;

/**
 * Menu controller for admin users
 */
public class AdminMenuController extends MenuController {
    private Admin currentAdmin;
    
    public AdminMenuController(Admin admin, AdminService adminService, DoctorService doctorService, 
                               PatientService patientService, AppointmentService appointmentService,
                               AuthService authService) {
        super(adminService, doctorService, patientService, appointmentService, authService);
        this.currentAdmin = admin;
    }
    
    public boolean displayMenu() {
        System.out.println("\n===== Admin Menu =====");
        System.out.println("Welcome, " + currentAdmin.getName() + "!");
          String[] options = {
            "Manage Patients",
            "Manage Doctors",
            "Manage Appointments",
            "View Medical Records",
            "View Logged In Doctors",
            "Logout"
        };
        
        int choice = InputUtil.getMenuChoice("Select an option:", options);
          switch (choice) {
            case 1:
                managePatients();
                break;
            case 2:
                manageDoctors();
                break;
            case 3:
                manageAppointments();
                break;
            case 4:
                viewMedicalRecords();
                break;
            case 5:
                viewLoggedInDoctors();
                break;
            case 6:
                System.out.println("Logging out...");
                return false;
        }
        
        return true;
    }
    
    private void managePatients() {
        System.out.println("\n----- Patient Management -----");
        String[] options = {
            "Add New Patient",
            "Remove Patient",
            "Find Patient by Name",
            "Display All Patients",
            "Display Patients in Order (BST)",
            "Back to Main Menu"
        };
        
        int choice = InputUtil.getMenuChoice("Select an option:", options);
        
        switch (choice) {
            case 1:
                addPatient();
                break;
            case 2:
                removePatient();
                break;
            case 3:
                findPatientByName();
                break;
            case 4:
                displayAllPatients();
                break;
            case 5:
                patientService.displayPatientsInOrder();
                break;
            case 6:
                return;
        }
    }
    
    private void manageDoctors() {
        System.out.println("\n----- Doctor Management -----");
        String[] options = {
            "Add New Doctor",
            "Display All Doctors",
            "Back to Main Menu"
        };
        
        int choice = InputUtil.getMenuChoice("Select an option:", options);
        
        switch (choice) {
            case 1:
                addDoctor();
                break;
            case 2:
                displayAllDoctors();
                break;
            case 3:
                return;
        }
    }
    
    private void manageAppointments() {
        System.out.println("\n----- Appointment Management -----");
        String[] options = {
            "Schedule New Appointment",
            "Process Next Appointment",
            "View All Upcoming Appointments",
            "Back to Main Menu"
        };
        
        int choice = InputUtil.getMenuChoice("Select an option:", options);
        
        switch (choice) {
            case 1:
                scheduleAppointment();
                break;
            case 2:
                processNextAppointment();
                break;
            case 3:
                viewUpcomingAppointments();
                break;
            case 4:
                return;
        }
    }
    
    private void addPatient() {
        System.out.println("\n--- Add New Patient ---");
        String name = InputUtil.getRequiredString("Enter patient name");
        int age = InputUtil.getIntInRange("Enter patient age", 0, 120);
        String address = InputUtil.getRequiredString("Enter patient address");
        String phoneNumber = InputUtil.getRequiredString("Enter patient phone number");
        String username = InputUtil.getRequiredString("Enter patient username");
        String password = InputUtil.getRequiredString("Enter patient password");
        
        Patient patient = patientService.addPatient(name, age, address, phoneNumber, username, password);
        System.out.println("Patient added successfully with ID: " + patient.getId());
    }
    
    private void removePatient() {
        System.out.println("\n--- Remove Patient ---");
        int id = InputUtil.getInt("Enter patient ID to remove");
        
        boolean removed = patientService.removePatientById(id);
        if (removed) {
            System.out.println("Patient removed successfully.");
        } else {
            System.out.println("Patient not found with ID: " + id);
        }
    }
    
    private void findPatientByName() {
        System.out.println("\n--- Find Patient by Name ---");
        String name = InputUtil.getRequiredString("Enter patient name (or part of name)");
        
        Patient patient = patientService.findPatientByName(name);
        if (patient != null) {
            System.out.println("Found patient: " + patient);
        } else {
            System.out.println("No patient found with name containing: " + name);
        }
    }
    
    private void displayAllPatients() {
        System.out.println("\n--- All Patients ---");
        Patient[] patients = patientService.getAllPatients();
        
        if (patients.length == 0) {
            System.out.println("No patients in the system.");
        } else {
            for (Patient patient : patients) {
                System.out.println(patient);
            }
            System.out.println("Total patients: " + patients.length);
        }
    }
    
    private void addDoctor() {
        System.out.println("\n--- Add New Doctor ---");
        String name = InputUtil.getRequiredString("Enter doctor name");
        String specialty = InputUtil.getRequiredString("Enter doctor specialty");
        String username = InputUtil.getRequiredString("Enter doctor username");
        String password = InputUtil.getRequiredString("Enter doctor password");
        
        Doctor doctor = doctorService.addDoctor(name, specialty, username, password);
        System.out.println("Doctor added successfully with ID: " + doctor.getId());
    }
    
    private void displayAllDoctors() {
        System.out.println("\n--- All Doctors ---");
        Doctor[] doctors = doctorService.getAllDoctors();
        
        if (doctors.length == 0) {
            System.out.println("No doctors in the system.");
        } else {
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }
            System.out.println("Total doctors: " + doctors.length);
        }
    }
    
    private void viewLoggedInDoctors() {
        System.out.println("\n--- Currently Logged In Doctors ---");
        Doctor[] loggedInDoctors = doctorService.getLoggedInDoctors();
        
        if (loggedInDoctors.length == 0) {
            System.out.println("No doctors currently logged in.");
        } else {
            for (Doctor doctor : loggedInDoctors) {
                System.out.println(doctor);
            }
            System.out.println("Total logged in doctors: " + loggedInDoctors.length);
        }
    }
    
    private void scheduleAppointment() {
        System.out.println("\n--- Schedule New Appointment ---");
        
        // Display available patients
        Patient[] patients = patientService.getAllPatients();
        if (patients.length == 0) {
            System.out.println("No patients in the system. Please add a patient first.");
            return;
        }
        
        System.out.println("Available Patients:");
        for (Patient patient : patients) {
            System.out.println(patient.getId() + ": " + patient.getName());
        }
        int patientId = InputUtil.getInt("Enter patient ID");
        
        // Display available doctors
        Doctor[] doctors = doctorService.getAllDoctors();
        if (doctors.length == 0) {
            System.out.println("No doctors in the system. Please add a doctor first.");
            return;
        }
        
        System.out.println("Available Doctors:");
        for (Doctor doctor : doctors) {
            System.out.println(doctor.getId() + ": " + doctor.getName() + " (" + doctor.getSpecialty() + ")");
        }
        int doctorId = InputUtil.getInt("Enter doctor ID");
        
        // Get appointment time
        LocalDateTime appointmentTime = InputUtil.getDateTime("Enter appointment date", "Enter appointment time");
        
        Appointment appointment = appointmentService.scheduleAppointment(patientId, doctorId, appointmentTime);
        if (appointment != null) {
            System.out.println("Appointment scheduled successfully with ID: " + appointment.getAppointmentId());
        } else {
            System.out.println("Failed to schedule appointment. Please check the following:");
            System.out.println("- Appointment time must be between 7:00 and 22:00");
            System.out.println("- Appointment must be within one year from now");
            System.out.println("- Doctor and patient must not have another appointment at the same time");
        }
    }
      private void processNextAppointment() {
        System.out.println("\n--- Process Next Appointment ---");
        
        // Check if there are appointments to process
        Appointment[] appointments = appointmentService.viewUpcomingAppointments();
        if (appointments.length == 0) {
            System.out.println("No appointments in the queue.");
            return;
        }
        
        // Show the next appointment to be processed
        Appointment nextAppointment = appointments[0];
        Patient patient = patientService.findPatientById(nextAppointment.getPatientId());
        Doctor doctor = doctorService.findDoctorById(nextAppointment.getDoctorId());
        
        String patientName = (patient != null) ? patient.getName() : "Unknown";
        String doctorName = (doctor != null) ? doctor.getName() : "Unknown";
        
        System.out.println("Next appointment to process:");
        System.out.println(nextAppointment);
        System.out.println("  Patient: " + patientName);
        System.out.println("  Doctor: " + doctorName);
        
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
        } else {
            System.out.println("Appointment processing cancelled.");
        }
    }
    
    private void viewUpcomingAppointments() {
        System.out.println("\n--- Upcoming Appointments ---");
        Appointment[] appointments = appointmentService.viewUpcomingAppointments();
        
        if (appointments.length == 0) {
            System.out.println("No upcoming appointments.");
        } else {
            for (Appointment appointment : appointments) {
                Patient patient = patientService.findPatientById(appointment.getPatientId());
                Doctor doctor = doctorService.findDoctorById(appointment.getDoctorId());
                
                String patientName = (patient != null) ? patient.getName() : "Unknown";
                String doctorName = (doctor != null) ? doctor.getName() : "Unknown";
                
                System.out.println(appointment);
                System.out.println("  Patient: " + patientName);
                System.out.println("  Doctor: " + doctorName);
                System.out.println();
            }
            System.out.println("Total upcoming appointments: " + appointments.length);
        }
    }
    
    /**
     * View all medical records or filter by patient/doctor
     */
    private void viewMedicalRecords() {
        System.out.println("\n----- Medical Records Management -----");
        
        if (diagnosisService == null) {
            System.out.println("Medical records service is not available at this time.");
            return;
        }
        
        String[] options = {
            "View All Medical Records",
            "View Medical Records by Patient",
            "View Medical Records by Doctor",
            "Back to Main Menu"
        };
        
        int choice = InputUtil.getMenuChoice("Select an option:", options);
        
        switch (choice) {
            case 1:
                viewAllMedicalRecords();
                break;
            case 2:
                viewMedicalRecordsByPatient();
                break;
            case 3:
                viewMedicalRecordsByDoctor();
                break;
            case 4:
                return;
        }
    }
    
    /**
     * View all medical records in the system
     */
    private void viewAllMedicalRecords() {
        System.out.println("\n--- All Medical Records ---");
        
        model.Diagnosis[] records = diagnosisService.getAllDiagnosis();
        
        if (records.length == 0) {
            System.out.println("No medical records found in the system.");
            return;
        }
        
        displayMedicalRecords(records);
    }
    
    /**
     * View medical records for a specific patient
     */
    private void viewMedicalRecordsByPatient() {
        System.out.println("\n--- Medical Records by Patient ---");
        
        // Display all patients for selection
        Patient[] patients = patientService.getAllPatients();
        if (patients.length == 0) {
            System.out.println("No patients in the system.");
            return;
        }
        
        System.out.println("Available Patients:");
        for (Patient patient : patients) {
            System.out.println(patient.getId() + ": " + patient.getName());
        }
        
        int patientId = InputUtil.getInt("Enter patient ID");
        
        // Verify patient exists
        Patient patient = patientService.findPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient not found with ID: " + patientId);
            return;
        }
        
        // Get and display records
        model.Diagnosis[] records = diagnosisService.getPatientDiagnosis(patientId);
        
        if (records.length == 0) {
            System.out.println("No medical records found for patient: " + patient.getName());
            return;
        }
        
        System.out.println("\nMedical Records for " + patient.getName() + " (ID: " + patient.getId() + "):");
        displayMedicalRecords(records);
    }
    
    /**
     * View medical records created by a specific doctor
     */
    private void viewMedicalRecordsByDoctor() {
        System.out.println("\n--- Medical Records by Doctor ---");
        
        // Display all doctors for selection
        Doctor[] doctors = doctorService.getAllDoctors();
        if (doctors.length == 0) {
            System.out.println("No doctors in the system.");
            return;
        }
        
        System.out.println("Available Doctors:");
        for (Doctor doctor : doctors) {
            System.out.println(doctor.getId() + ": " + doctor.getName());
        }
        
        int doctorId = InputUtil.getInt("Enter doctor ID");
        
        // Verify doctor exists
        Doctor doctor = doctorService.findDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("Doctor not found with ID: " + doctorId);
            return;
        }
        
        // Get and display records
        model.Diagnosis[] records = diagnosisService.getDoctorDiagnosis(doctorId);
        
        if (records.length == 0) {
            System.out.println("No medical records found for doctor: " + doctor.getName());
            return;
        }
        
        System.out.println("\nMedical Records by Dr. " + doctor.getName() + " (ID: " + doctor.getId() + "):");
        displayMedicalRecords(records);
    }
    
    /**
     * Helper method to display medical records
     */
    private void displayMedicalRecords(model.Diagnosis[] records) {
        System.out.println("---------------------------------------------");
        
        for (model.Diagnosis record : records) {
            // Get patient and doctor names
            Patient patient = patientService.findPatientById(record.getPatientId());
            Doctor doctor = doctorService.findDoctorById(record.getDoctorId());
            
            String patientName = (patient != null) ? patient.getName() : "Unknown Patient";
            String doctorName = (doctor != null) ? "Dr. " + doctor.getName() : "Unknown Doctor";
            
            // Format date nicely
            String dateTime = record.getDiagnosisTime().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            
            System.out.println("Diagnosis ID: " + record.getDiagnosisId());
            System.out.println("Date: " + dateTime);
            System.out.println("Patient: " + patientName + " (ID: " + record.getPatientId() + ")");
            System.out.println("Doctor: " + doctorName + " (ID: " + record.getDoctorId() + ")");
            System.out.println("Complaint: " + record.getComplaint());
            System.out.println("Diagnosis: " + record.getDiagnosis());
            System.out.println("Prescribed Medication: " + record.getMedication());
            System.out.println("---------------------------------------------");
        }
        
        System.out.println("Total records: " + records.length);
    }
}
