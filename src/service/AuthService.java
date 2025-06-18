package service;

import model.Admin;
import model.Doctor;
import model.Patient;
import model.User;

/**
 * Service class for authentication and role detection
 */
public class AuthService {
    private AdminService adminService;
    private DoctorService doctorService;
    private PatientService patientService;
    
    public enum Role {
        ADMIN,
        DOCTOR,
        PATIENT,
        UNKNOWN
    }
    
    public AuthService(AdminService adminService, DoctorService doctorService, PatientService patientService) {
        this.adminService = adminService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }
    
    /**
     * Authenticate a user by username and password, determining their role automatically
     */
    public Object[] authenticate(String username, String password) {
        // Try admin login
        Admin admin = adminService.login(username, password);
        if (admin != null) {
            return new Object[]{admin, Role.ADMIN};
        }
        
        // Try doctor login
        Doctor doctor = doctorService.loginDoctorByUsername(username, password);
        if (doctor != null) {
            return new Object[]{doctor, Role.DOCTOR};
        }
        
        // Try patient login
        Patient patient = patientService.findPatientByUsername(username);
        if (patient != null && patient.getPassword().equals(password)) {
            return new Object[]{patient, Role.PATIENT};
        }
        
        return null;
    }
    
    public Role getUserRole(User user) {
        if (user instanceof Admin) {
            return Role.ADMIN;
        } else if (user instanceof Doctor) {
            return Role.DOCTOR;
        } else if (user instanceof Patient) {
            return Role.PATIENT;
        }
        return Role.UNKNOWN;
    }
}
