# 🏥 Daisuke Clinic Management System

<div align="center">
  <img src="img/MainPage.png" alt="Daisuke Clinic" width="600px"/>
  <br>
  <em>A comprehensive clinic management solution built with Java</em>
</div>

## 📋 Description
Daisuke Clinic Management System is a Java-based application designed to efficiently manage clinic operations. The system facilitates the administration of patients, doctors, and appointments through an intuitive command-line interface. It employs various data structures such as Linked Lists, Queues, and Binary Search Trees to optimize data management and retrieval.

## ✨ Features

<details open>
<summary><b>🔐 Main Login Page</b></summary>
<p>The system provides a secure login interface for different user roles.</p>
<div align="center">
  <img src="img/LogInPage.png" alt="Login Page" width="600px"/>
</div>
</details>

### 👩‍💼 Admin Features

<details>
<summary><b>Admin Main Menu</b></summary>
<p>Administrative dashboard with access to all management features.</p>
<div align="center">
  <img src="img/Admin_MainMenu.png" alt="Admin Main Menu" width="600px"/>
</div>
</details>

<details>
<summary><b>Doctor Management</b></summary>
<p>Admin can add, view, and manage doctor information.</p>
<div align="center">
  <img src="img/Admin_DoctorManagement.png" alt="Doctor Management" width="600px"/>
</div>
</details>

<details>
<summary><b>Patient Management</b></summary>
<p>Admin can add, remove, and find patients in the system.</p>
<div align="center">
  <img src="img/Admin_PatientManagement.png" alt="Patient Management" width="600px"/>
</div>

<details>
<summary><i>Add Patient</i></summary>
<p>Admins can register new patients to the system.</p>
<div align="center">
  <img src="img/Admin_AddPatient.png" alt="Add Patient" width="600px"/>
</div>
</details>

<details>
<summary><i>Remove Patient</i></summary>
<p>Admins can remove patients from the system using their ID.</p>
<div align="center">
  <img src="img/Admin_RemovePatient.png" alt="Remove Patient" width="600px"/>
</div>
</details>

<details>
<summary><i>Find Patient</i></summary>
<p>Admins can search for patients by name.</p>
<div align="center">
  <img src="img/Admin_FindPAteint.png" alt="Find Patient" width="600px"/>
</div>
</details>

<details>
<summary><i>Display All Patients</i></summary>
<p>View all patients stored in the linked list.</p>
<div align="center">
  <img src="img/Admin_DisplayAllPatient.png" alt="Display All Patients" width="600px"/>
</div>
</details>
</details>

<details>
<summary><b>Appointment Management</b></summary>

<details>
<summary><i>Schedule Appointment</i></summary>
<p>Admins can schedule new appointments.</p>
<div align="center">
  <img src="img/Admin_ScheduleAppointment.png" alt="Schedule Appointment" width="600px"/>
</div>
</details>

<details>
<summary><i>Process Appointment</i></summary>
<p>Admins can process and update appointment status, including recording diagnoses and prescribing medication.</p>
<div align="center">
  <img src="img/Admin_ProsesAppointment.png" alt="Process Appointment" width="600px"/>
</div>
</details>

<details>
<summary><i>Medical Records</i></summary>
<p>The system records and manages patient medical records including complaints, diagnoses, and prescribed medications. This information is stored in a dedicated database file (Diagnosis.txt) and can be accessed by relevant parties based on their roles.</p>
</details>

<details>
<summary><i>Upcoming Appointments</i></summary>
<p>View all upcoming appointments.</p>
<div align="center">
  <img src="img/Admin_Upcomingppointment.png" alt="Upcoming Appointments" width="600px"/>
</div>
</details>
</details>

### 👨‍⚕️ Doctor Features

<details>
<summary><b>Doctor Main Menu</b></summary>
<p>Interface for doctors to manage their appointments.</p>
<div align="center">
  <img src="img/Doctor_MainMenu.png" alt="Doctor Main Menu" width="600px"/>
</div>
</details>

<details>
<summary><b>View Appointments</b></summary>
<p>Doctors can view their scheduled appointments.</p>
<div align="center">
  <img src="img/Doctor_ViewTheirAppointment.png" alt="Doctor Appointments" width="600px"/>
</div>
</details>

<details>
<summary><b>Process Appointments</b></summary>
<p>Doctors can process their appointments, record patient complaints, provide diagnoses, and prescribe medications.</p>
<div align="center">
  <img src="img/Doctor_ProcessAppointment.png" alt="Process Appointments" width="600px"/>
</div>

<details>
<summary><i>Diagnosis Entry</i></summary>
<p>Doctors can enter detailed diagnosis information and prescribe medications.</p>
<div align="center">
  <img src="img/Doctor_DiagnosisEntry.png" alt="Diagnosis Entry" width="600px"/>
</div>
</details>
</details>

<details>
<summary><b>Medical Records Management</b></summary>
<p>Doctors can create and maintain patient medical records with detailed information about each visit, including:
<ul>
  <li>Patient Complaints/Symptoms</li>
  <li>Clinical Diagnoses</li>
  <li>Prescribed Medications</li>
</ul>
</p>
<p>Doctors can also view the complete medical history of any patient, helping provide better continuity of care.</p>
<div align="center">
  <img src="img/Doctor_ViewPatientMedicalRecords.png" alt="Patient Medical Records" width="600px"/>
</div>
</details>

### 🧑‍⚕️ Patient Features

<details>
<summary><b>Patient Menu</b></summary>
<p>Interface for patients to manage their profile and appointments.</p>
<div align="center">
  <img src="img/Patient_Menu.png" alt="Patient Menu" width="600px"/>
</div>
</details>

<details>
<summary><b>Register</b></summary>
<p>New patients can register to the system.</p>
<div align="center">
  <img src="img/Patient_Register.png" alt="Patient Register" width="600px"/>
</div>
</details>

<details>
<summary><b>Schedule Appointment</b></summary>
<p>Patients can schedule appointments with doctors.</p>
<div align="center">
  <img src="img/Patient_ScheduleAppointment.png" alt="Schedule Appointment" width="600px"/>
</div>
</details>

<details>
<summary><b>View My Appointments</b></summary>
<p>Patients can view their scheduled appointments and access their medical records from completed appointments, including diagnoses and prescribed medications.</p>
<div align="center">
  <img src="img/Patient_MyAppointment.png" alt="My Appointments" width="600px"/>
</div>
</details>

<details>
<summary><b>View Medical History</b></summary>
<p>Patients can access their complete medical history, viewing all past diagnoses, treatments, and prescriptions from their previous visits.</p>
<div align="center">
  <img src="img/Patient_ViewMedicalHistory.png" alt="Medical History" width="600px"/>
</div>
</details>

<details>
<summary><b>Update Profile</b></summary>
<p>Patients can update their personal information.</p>
<div align="center">
  <img src="img/PAtient_Update.png" alt="Update Profile" width="600px"/>
</div>

<details>
<summary><i>Update Phone</i></summary>
<p>Update phone number.</p>
<div align="center">
  <img src="img/Patient_UpdatePhone.png" alt="Update Phone" width="600px"/>
</div>
</details>

<details>
<summary><i>Update Address</i></summary>
<p>Update address information.</p>
<div align="center">
  <img src="img/Patient_UpdateAddress.png" alt="Update Address" width="600px"/>
</div>
</details>

<details>
<summary><i>Update Password</i></summary>
<p>Update account password.</p>
<div align="center">
  <img src="img/Patient_UpdatePassword.png" alt="Update Password" width="600px"/>
</div>
</details>
</details>

<details>
<summary><b>Medical Records System</b></summary>
<p>Administrators can view, search, and manage all medical records in the system through a dedicated interface. They can filter records by patient or doctor, providing oversight of clinical care.</p>
<div align="center">
  <img src="img/Admin_MedicalRecordsMenu.png" alt="Medical Records Menu" width="600px"/>
</div>

<details>
<summary><i>View All Medical Records</i></summary>
<p>Administrators can view all medical records in the system.</p>
<div align="center">
  <img src="img/Admin_ViewAllMedicalRecords.png" alt="All Medical Records" width="600px"/>
</div>
</details>

<details>
<summary><i>View Patient Medical Records</i></summary>
<p>Administrators can filter medical records by patient.</p>
<div align="center">
  <img src="img/Admin_ViewPatientMedicalRecords.png" alt="Patient Medical Records" width="600px"/>
</div>
</details>

<details>
<summary><i>View Doctor Medical Records</i></summary>
<p>Administrators can filter medical records by doctor.</p>
<div align="center">
  <img src="img/Admin_ViewDoctorMedicalRecords.png" alt="Doctor Medical Records" width="600px"/>
</div>
</details>
</details>

### 📝 Diagnosis Records
<img src="img/Patient_ViewMedicalHistory.png" align="right" width="300px"/>

Implemented for managing medical records in `src/model/Diagnosis.java` and `src/service/DiagnosisService.java`.

Key features:
- **Create Diagnosis Records**: Records patient complaints, diagnoses, and medications (`DiagnosisService.java`)
- **Patient-Specific Records**: Retrieves medical history for a specific patient (`DiagnosisService.java`)
- **Doctor-Specific Records**: Filters records by attending physician (`DiagnosisService.java`)
- **Appointment Linking**: Links diagnosis records to specific appointments (`DiagnosisService.java`)
- **Persistent Storage**: Saves all medical records to a dedicated file for long-term storage

<br clear="right"/>

## 🚀 How to Run the Program

<div align="center">
  <table>
    <tr>
      <td align="center"><b>Prerequisites</b></td>
      <td align="center"><b>Installation</b></td>
      <td align="center"><b>Execution</b></td>
    </tr>
    <tr>
      <td>
        ✅ Java JDK 8+ installed<br>
        ✅ Command line terminal<br>
        ✅ Git (optional)
      </td>
      <td>
        1. Clone/download repository<br>
        2. Navigate to project directory
      </td>
      <td>
        1. Compile Java files<br>
        2. Run the Main class<br>
        3. Follow on-screen instructions
      </td>
    </tr>
  </table>
</div>

### Step-by-Step Instructions

1. Ensure you have Java installed on your system (JDK 8 or higher recommended)
   ```bash
   java -version
   ```

2. Clone or download the repository to your local machine
   ```bash
   git clone https://github.com/KevinAnandaP/daisuke-clinic-platform.git
   ```

3. Navigate to the project directory in your terminal or command prompt
   ```bash
   cd daisuke-clinic
   ```

4. Compile the Java files (if not already compiled):
   ```bash
   javac -d bin src\*.java src\model\*.java src\datastructure\*.java src\service\*.java src\util\*.java
   ```

5. Run the program:
   ```bash
   java -cp bin Main
   ```

6. Follow the on-screen instructions to navigate through the system

## 💻 Implementation Details

<div align="center">
  <table>
    <tr>
      <th>Data Structure</th>
      <th>Used For</th>
      <th>Time Complexity</th>
    </tr>
    <tr>
      <td>Linked List</td>
      <td>Patient collection, Doctor login records</td>
      <td>Insert: O(1), Search: O(n), Delete: O(1)*</td>
    </tr>
    <tr>
      <td>Queue</td>
      <td>Appointment scheduling and processing</td>
      <td>Enqueue: O(1), Dequeue: O(1)</td>
    </tr>
    <tr>
      <td>Binary Search Tree</td>
      <td>Patient lookup by ID</td>
      <td>Search: O(log n), Insert: O(log n), Delete: O(log n)</td>
    </tr>
  </table>
  <p><i>* With direct access to node</i></p>
</div>

### 🔗 Linked List
<img src="img/Admin_DisplayAllPatient.png" align="right" width="300px"/>

Used for managing collections of patients and other entities. Implemented in `src/datastructure/LinkedList.java`.

Key operations:
- **Add Patient**: Adds a new patient to the linked list (`PatientService.java`)
- **Remove Patient by ID**: Removes a patient using their ID as the key (`PatientService.java`)
- **Find Patient by Name**: Searches for patients with matching names (`PatientService.java`)
- **Display All Patients**: Shows all patients stored in the linked list (`PatientService.java`)
- **Log In Doctor**: Records when a doctor logs into the system (`DoctorService.java`)
- **Register Doctor**: Adds a new doctor to the system (`AdminService.java`)
- **Log Out Doctor**: Records when a doctor logs out (`DoctorService.java`)
- **Get All Logged In Doctors**: Retrieves a list of all doctors currently logged in (`DoctorService.java`)

<br clear="right"/>

### 🔄 Queue
<img src="img/Admin_ProsesAppointment.png" align="right" width="300px"/>

Implemented for appointment scheduling and processing in `src/datastructure/Queue.java`.

Key operations:
- **Schedule Appointment**: Adds a new appointment to the queue (`AppointmentService.java`)
- **Process Appointment**: Removes and processes the next appointment in the queue (`AppointmentService.java`)
- **Upcoming Appointment**: Views all appointments in the queue (`AppointmentService.java`)
- **Priority Queue based on Time**: Appointments are prioritized based on their scheduled time
- **Medical Records Generation**: During appointment processing, diagnosis and medication information is recorded (`DiagnosisService.java`)

<br clear="right"/>

### 🌳 Binary Search Tree (BST)
<img src="img/DisplayPatientBST.png" align="right" width="300px"/>

Used for efficient patient lookup by ID in `src/datastructure/BinarySearchTree.java`.

Key operations:
- **Insert Patient**: Adds a patient to the BST with ID as key (`PatientService.java`)
- **Search Patient by ID**: Efficiently finds a patient using binary search (`PatientService.java`)
- **In-Order Display**: Displays all patients in sorted order by ID (`PatientService.java`)

<br clear="right"/>

## 📊 Case Studies

### 🚨 Case Study 1: Emergency Patient Management
**Scenario**: A new patient arrives at the clinic with severe symptoms requiring immediate attention.

**Solution**:
1. The admin quickly registers the patient using the "Add Patient" feature, which utilizes the Linked List data structure for rapid insertion.
2. The patient's information is simultaneously added to the Binary Search Tree indexed by patient ID for efficient future lookups.
3. An urgent appointment is scheduled through the Queue system, which prioritizes based on severity and time.
4. Available doctors are identified through the "Get All Logged In Doctors" feature.
5. The appointment is processed immediately using the "Process Appointment" functionality.

This case demonstrates how the integrated data structures (Linked List for patient registration, BST for lookup, and Queue for appointment prioritization) work together to handle emergency situations efficiently.

### 🏥 Case Study 2: Managing High-Volume Patient Visits
**Scenario**: The clinic experiences a sudden influx of patients during flu season.

**Solution**:
1. Multiple patients are registered quickly using the efficient "Add Patient" operation.
2. Appointments are scheduled in bulk and automatically prioritized by the Queue implementation.
3. Doctors can view their upcoming appointments in order of priority.
4. Admin staff can monitor the overall appointment flow and redistribute if necessary.
5. Patient lookup by ID (using BST) ensures quick access to patient records even with the increased volume.

This case highlights the system's scalability through efficient data structures: O(1) insertion with Linked Lists, O(log n) lookup with BST, and prioritized processing with Queue.

### 📱 Case Study 3: Patient Record Update and Appointment Rescheduling
**Scenario**: A patient needs to update their contact information and reschedule a previously booked appointment.

**Solution**:
1. The patient logs in and uses the "Update Profile" features to modify their phone number and address.
2. The system efficiently locates the patient's record in both the Linked List and BST structures.
3. The patient views their current appointment using "View My Appointments".
4. Using the appointment scheduling system backed by the Queue implementation, the patient reschedules their appointment.
5. Both the patient and assigned doctor receive updated appointment information.

This case study demonstrates the flexibility of the system and how the different data structures maintain consistency across multiple operations.

### 🚑 Case Study 4: Comprehensive Medical Records Management
**Scenario**: A patient with a chronic condition visits multiple doctors at the clinic over time, requiring consistent medical record keeping.

**Solution**:
1. Each time the patient visits, their appointment is recorded in the Queue system.
2. When doctors process appointments, they enter detailed diagnosis and medication information.
3. These records are stored in the dedicated Diagnosis.txt file through the DiagnosisService.
4. The patient can access their complete medical history through the "View Medical History" feature.
5. Any doctor seeing the patient can review previous diagnoses and treatments, ensuring continuity of care.
6. Administrators can monitor patterns in diagnoses and treatments across all patients or specific doctors.

This case study demonstrates how the medical records system enhances patient care through comprehensive record-keeping and accessibility, while maintaining data integrity using proper file I/O operations.

## 👥 Team Members and Responsibilities

<div align="center">
  <table>
    <tr>
      <th>Team Member</th>
      <th>Responsibilities</th>
    </tr>
    <tr>
      <td>
        <b>L0124070 - Raditya Adi Pradana</b><br>
        <i></i>
      </td>
      <td>
        • Implementing Doctor.java
      </td>
    </tr>
    <tr>
      <td>
        <b>L0124072 - Resyad Agung W</b><br>
        <i></i>
      </td>
      <td>
        • Creating Queue.java<br>
        • Creating BST.java<br>
        • Implementing Appointment.java
      </td>
    </tr>
    <tr>
      <td>
        <b>L0124075 - Sanny Tazkiyah F. H</b><br>
        <i></i>
      </td>
      <td>
        • Creating LinkedList.java<br>
        • Implementing Patient.java
      </td>
    </tr>
    <tr>
      <td>
        <b>L0124103 - Kevin Ananda Putra</b><br>
        <i></i>
      </td>
      <td>
        • Implementing Main.java and folder structure<br>
        • Add diagnosis feature<br>
        • Finishing
      </td>
    </tr>
  </table>
</div>

---

<div align="center">
  <p>© 2025 Daisuke Clinic Management System</p>
</div>