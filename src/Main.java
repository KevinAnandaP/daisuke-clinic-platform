import java.io.File;
import service.AdminMenuController;
import service.AdminService;
import service.AppointmentService;
import service.AuthService;
import service.AuthService.Role;
import service.DiagnosisService;
import service.DoctorMenuController;
import service.DoctorService;
import service.MenuController;
import service.PatientMenuController;
import service.PatientService;
import util.InputUtil;

import model.Admin;
import model.Doctor;
import model.Patient;

/**
 * Main class for the Daisuke Clinic application
 */
public class Main {    private static AdminService adminService;
    private static DoctorService doctorService;
    private static PatientService patientService;
    private static AppointmentService appointmentService;
    private static DiagnosisService diagnosisService;
    private static AuthService authService;
    
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("     Welcome to Daisuke Clinic");
        System.out.println("======================================");
        
        // Initialize file structure
        initializeFileStructure();
          // Initialize services
        adminService = new AdminService();
        doctorService = new DoctorService();
        patientService = new PatientService();
        diagnosisService = new DiagnosisService();
        appointmentService = new AppointmentService();
        appointmentService.setDiagnosisService(diagnosisService);
        authService = new AuthService(adminService, doctorService, patientService);
        
        // Main application loop
        boolean running = true;
        while (running) {
            running = mainMenu();
        }
        
        System.out.println("Thank you for using Daisuke Clinic!");
    }
    
    private static boolean mainMenu() {
        System.out.println("\n===== Main Menu =====");
        String[] options = {
            "Login",
            "Register as Patient",
            "Exit"
        };
        
        int choice = InputUtil.getMenuChoice("Select an option:", options);
        
        switch (choice) {
            case 1:
                login();
                return true;
            case 2:
                registerPatient();
                return true;
            case 3:
                return false;
            default:
                return true;
        }
    }
    
    /**
     * Handle user login
     */
    private static void login() {
        System.out.println("\n----- Login -----");
        String username = InputUtil.getRequiredString("Enter username");
        String password = InputUtil.getRequiredString("Enter password");
        
        Object[] authResult = authService.authenticate(username, password);
        
        if (authResult != null) {
            Object user = authResult[0];
            Role role = (Role) authResult[1];
            
            MenuController menuController = null;
              switch (role) {
                case ADMIN:
                    menuController = new AdminMenuController(
                            (Admin) user, adminService, doctorService, patientService, appointmentService, authService);
                    break;
                case DOCTOR:
                    menuController = new DoctorMenuController(
                            (Doctor) user, adminService, doctorService, patientService, appointmentService, authService);
                    break;
                case PATIENT:
                    menuController = new PatientMenuController(
                            (Patient) user, adminService, doctorService, patientService, appointmentService, authService);
                    break;
                default:
                    System.out.println("Unknown role. Please try again.");
                    return;
            }
            
            // Set diagnosis service for all controllers
            menuController.setDiagnosisService(diagnosisService);
            
            boolean userActive = true;
            while (userActive) {
                userActive = menuController.displayMenu();
            }
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }
    
    /**
     * Register a new patient
     */
    private static void registerPatient() {
        System.out.println("\n----- Patient Registration -----");
        String name = InputUtil.getRequiredString("Enter your name");
        int age = InputUtil.getIntInRange("Enter your age", 0, 120);
        String address = InputUtil.getRequiredString("Enter your address");
        String phoneNumber = InputUtil.getRequiredString("Enter your phone number");
        
        // Username validation
        String username;
        do {
            username = InputUtil.getRequiredString("Enter your username");
            
            // Check if username already exists
            if (adminService.findAdminByUsername(username) != null ||
                doctorService.findDoctorByUsername(username) != null ||
                patientService.findPatientByUsername(username) != null) {
                System.out.println("Username already exists. Please choose another one.");
                username = null;
            }
        } while (username == null);
        
        String password = InputUtil.getRequiredString("Enter your password");
        
        Patient patient = patientService.addPatient(name, age, address, phoneNumber, username, password);
        System.out.println("Registration successful! Your ID is: " + patient.getId());
    }
      /**
     * Initialize file structure for data persistence
     */
    private static void initializeFileStructure() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        
        // Create empty files if they don't exist
        try {
            new File("data/Admin.txt").createNewFile();
            new File("data/Doctor.txt").createNewFile();
            new File("data/Patient.txt").createNewFile();
            new File("data/Appointment.txt").createNewFile();
            new File("data/Diagnosis.txt").createNewFile();
        } catch (Exception e) {
            System.err.println("Error creating data files: " + e.getMessage());
        }
    }
}
