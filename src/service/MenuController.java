package service;

/**
 * Base class for all menu controllers
 */
public abstract class MenuController {
    protected AdminService adminService;
    protected DoctorService doctorService;
    protected PatientService patientService;
    protected AppointmentService appointmentService;
    protected DiagnosisService diagnosisService;
    protected AuthService authService;
    
    public MenuController(AdminService adminService, DoctorService doctorService, 
                          PatientService patientService, AppointmentService appointmentService,
                          AuthService authService) {
        this.adminService = adminService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.appointmentService = appointmentService;
        this.authService = authService;
    }
    
    public void setDiagnosisService(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }
    
    /**
     * Display the menu and handle user actions
     */
    public abstract boolean displayMenu();
}
