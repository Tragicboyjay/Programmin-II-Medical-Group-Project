package Services;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static Type.InsuranceCompany.COMPANY_A;
import static Type.InsuranceCompany.COMPANY_B;
import static Type.InsuranceCompany.COMPANY_C;

import Classes.Doctor;
import Classes.Nurse;
import Classes.Patient;

public class PersonService {
  static Scanner console = new Scanner(System.in);

  /**
   * Creates patients by reading data from a file and adds them to the list of patients.
   *
   * @param patients The list of existing patients.
   * @return The updated list of patients after adding patients from the file.
   * @throws FileNotFoundException If the file is not found or there is an issue with file operations.
   */
  public static List<Patient> creatPatientsFromFile(List<Patient> patients) throws FileNotFoundException {
    String name;
    int id = patients.size();
    LocalDate birthDate;
    String address;
    String phone;
    String gender;
    String medicalRecords;
    String currentSymptoms;
    String insuranceCompany;

    Scanner inFile;
    // System.out.println("Please enter the name of file you want to use as input: ");
    // String inputFileName = console.nextLine();
    inFile = new Scanner(new FileReader("patients.txt"));

    while (inFile.hasNextLine()){
    
      name = inFile.nextLine();
      System.out.println("Read name: " + name);

      birthDate = LocalDate.parse(inFile.nextLine());
      System.out.println("Read birthDate: " + birthDate);

      address = inFile.nextLine();
      System.out.println("Read address: " + address);

      phone = inFile.nextLine();
      System.out.println("Read phone: " + phone);

      gender = inFile.nextLine();
      System.out.println("Read gender: " + gender);

      medicalRecords = inFile.nextLine();
      System.out.println("Read medicalRecords: " + medicalRecords);

      currentSymptoms = inFile.nextLine();
      System.out.println("Read currentSymptoms: " + currentSymptoms);

      insuranceCompany = inFile.nextLine();
      System.out.println("Read insuranceCompany: " + insuranceCompany);

      // Compare with existing patients to avoid duplication
      if(patientComparator(patients, name, birthDate)){
        System.out.println("------This patient is already registered in the system------");
        System.out.println();
        continue;
      }

      id++;

      Patient patient = new Patient(name, birthDate, address, phone, id, gender, medicalRecords, currentSymptoms, insuranceCompany);

      patients.add(patient);
      updatePatients(patients);
      System.out.println("A new patient added!");
      System.out.println();
    }
    inFile.close();
    return patients;
  }


  /**
   * Creates doctors by reading data from a file and adds them to the list of doctors.
   *
   * @param doctors The list of existing doctors.
   * @return The updated list of doctors after adding doctors from the file.
   * @throws FileNotFoundException If the file is not found or there is an issue with file operations.
   */
  public static List<Doctor> creatDoctorsFromFile(List<Doctor> doctors) throws FileNotFoundException {
    String name;
    int id = doctors.size();
    LocalDate birthDate;
    String address;
    String phone;
    String specialty;
    int yearsInPractice;

    Scanner inFile;
    // System.out.println("Please enter the name of file you want to use as input: ");
    // String inputFileName = console.nextLine();
    inFile = new Scanner(new FileReader("doctors.txt"));

    while (inFile.hasNextLine()){
    
      name = inFile.nextLine();
      System.out.println("Read name: " + name);

      birthDate = LocalDate.parse(inFile.nextLine());
      System.out.println("Read birthDate: " + birthDate);

      address = inFile.nextLine();
      System.out.println("Read address: " + address);

      phone = inFile.nextLine();
      System.out.println("Read phone: " + phone);

      specialty = inFile.nextLine();
      System.out.println("Read specialty: " + specialty);

      yearsInPractice = Integer.parseInt(inFile.nextLine());
      System.out.println("Read yearsInPractice: " + yearsInPractice);

      if(doctorComparator(doctors, name, birthDate)){
        System.out.println("------This doctor is already registered in the system------");
        System.out.println();
        continue;
      }

      id++;

      Doctor doctor = new Doctor(name, birthDate, address, phone, id, specialty, yearsInPractice);
      doctors.add(doctor);
      updateDoctors(doctors);
      System.out.println("A new doctor added!");
      System.out.println();
    }
    inFile.close();
    return doctors;
  }

  /**
   * Creates nurses by reading data from a file and adds them to the list of nurses.
   *
   * @param nurses The list of existing nurses.
   * @return The updated list of nurses after adding nurses from the file.
   * @throws FileNotFoundException If the file is not found or there is an issue with file operations.
   */
  public static List<Nurse> creatNursesFromFile(List<Nurse> nurses) throws FileNotFoundException {
    String name;
    int id = nurses.size();
    LocalDate birthDate;
    String address;
    String phone;
    int yearsInPractice;
    String skill;

    Scanner inFile;
    System.out.println("Please enter the name of file you want to use as input: ");
    // String inputFileName = console.nextLine();
    // inFile = new Scanner(new FileReader(inputFileName));
    inFile = new Scanner(new FileReader("nurses.txt"));

    while (inFile.hasNextLine()){
    
      name = inFile.nextLine();
      System.out.println("Read name: " + name);

      birthDate = LocalDate.parse(inFile.nextLine());
      System.out.println("Read birthDate: " + birthDate);

      address = inFile.nextLine();
      System.out.println("Read address: " + address);

      phone = inFile.nextLine();
      System.out.println("Read phone: " + phone);

      yearsInPractice = Integer.parseInt(inFile.nextLine());
      System.out.println("Read yearsInPractices: " + yearsInPractice);

      skill = inFile.nextLine();
      System.out.println("Read skill: " + skill);

      if(nurseComparator(nurses, name, birthDate)){
        System.out.println("This nurse is already exist.");
        System.out.println();
        continue;
      }

      id++;

      Nurse nurse = new Nurse(name, birthDate, address, phone, id, yearsInPractice, skill);

      nurses.add(nurse);
      updateNurses(nurses);
      System.out.println("A new nurse added!");
      System.out.println();
    }
    inFile.close();
    return nurses;
  }
  
  /**
   * Compares a patient's name and birth date with existing patients in the list.
   *
   * @param patients The list of existing patients.
   * @param name     The name of the patient to compare.
   * @param birthDate The birth date of the patient to compare.
   * @return True if a patient with the same name and birth date already exists in the list, false otherwise.
   */
  public static boolean patientComparator(List<Patient> patients, String name, LocalDate birthDate) {
    for (Patient patient : patients) {
      if (patient.getName().toUpperCase().equals(name.toUpperCase()) && patient.getBirthDate().equals(birthDate)){
        return true;
      }
    }
    return false;
  }

  /**
   * Compares a doctor's name and birth date with existing doctors in the list.
   *
   * @param doctors The list of existing doctors.
   * @param name     The name of the doctor to compare.
   * @param birthDate The birth date of the doctor to compare.
   * @return True if a doctor with the same name and birth date already exists in the list, false otherwise.
   */
  public static boolean doctorComparator(List<Doctor> doctors, String name, LocalDate birthDate) {
    for (Doctor doctor : doctors) {
      if (doctor.getName().toUpperCase().equals(name.toUpperCase()) && doctor.getBirthDate().equals(birthDate)){
        return true;
      }
    }
    return false;
  }

  /**
   * Compares a nurse's name and birth date with existing nurses in the list.
   *
   * @param doctors The list of existing nurses.
   * @param name     The name of the nurse to compare.
   * @param birthDate The birth date of the nurse to compare.
   * @return True if a nurse with the same name and birth date already exists in the list, false otherwise.
   */
  public static boolean nurseComparator(List<Nurse> nurses, String name, LocalDate birthDate) {
    for (Nurse nurse : nurses) {
      if (nurse.getName().toUpperCase().equals(name.toUpperCase()) && nurse.getBirthDate().equals(birthDate)){
        return true;
      }
    }
    return false;
  }

  /**
   * Prints out information about patients to a file and the console.
   *
   * @param patients The list of patients to be printed.
   * @throws FileNotFoundException If there is an issue with file operations.
   */
  public static void printOutPatients(List<Patient> patients) throws FileNotFoundException {
    PrintWriter outFile;
    outFile = new PrintWriter("Patients_of_Clinic.txt");
    for (Patient patient : patients) {
      outFile.println(patient);
      System.out.println(patient);
    }
    System.out.println("Please find the updated data in Patients_of_Clinic.txt");
    outFile.close();
  }

  /**
   * Updates the patient information in a file with the latest data.
   *
   * @param patients The list of patients whose information will be updated in the file.
   * @throws FileNotFoundException If there is an issue with file operations.
   */
  public static void updatePatients(List<Patient> patients) throws FileNotFoundException {
    PrintWriter outFile;
    outFile = new PrintWriter("patients.txt");
    for (Patient patient : patients) {
      outFile.println(patient.getName());
      outFile.println(patient.getBirthDate());
      outFile.println(patient.getAddress());
      outFile.println(patient.getPhone());
      outFile.println(patient.getGender());
      outFile.println(patient.getMedicalRecords());
      outFile.println(patient.getCurrentSymptoms());
      outFile.println(patient.getInsuranceCompany());
    }
    outFile.close();
  }

  /**
   * Prints out information about doctors to a file and the console.
   *
   * @param doctors The list of doctors to be printed.
   * @throws FileNotFoundException If there is an issue with file operations.
   */
  public static void printOutDoctors(List<Doctor> doctors) throws FileNotFoundException {
    PrintWriter outFile;
    outFile = new PrintWriter("Doctors_of_Clinic.txt");
    for (Doctor doctor : doctors) {
      outFile.println(doctor);
      System.out.println(doctor);
    }
    System.out.println("Please find the updated data in Doctors_of_Clinic.txt");
    outFile.close();
  }

  /**
   * Updates the doctor information in a file with the latest data.
   *
   * @param doctors The list of doctors whose information will be updated in the file.
   * @throws FileNotFoundException If there is an issue with file operations.
   */
  public static void updateDoctors(List<Doctor> doctors) throws FileNotFoundException {
    PrintWriter outFile;
    outFile = new PrintWriter("doctors.txt");
    for (Doctor doctor : doctors) {
      outFile.println(doctor.getName());
      outFile.println(doctor.getBirthDate());
      outFile.println(doctor.getAddress());
      outFile.println(doctor.getPhone());
      outFile.println(doctor.getSpecialty());
      outFile.println(doctor.getYearsInPractice());
    }
    outFile.close();
  }

  /**
   * Prints out information about nurses to a file and the console.
   *
   * @param nurses The list of nurses to be printed.
   * @throws FileNotFoundException If there is an issue with file operations.
   */
  public static void printOutNurses(List<Nurse> nurses) throws FileNotFoundException {
    PrintWriter outFile;
    outFile = new PrintWriter("Nurses_of_Clinic.txt");
    for (Nurse nurse : nurses) {
      outFile.println(nurse);
      System.out.println(nurse);
    }
    System.out.println("Please find the updated data in Nurses_of_Clinic.txt");
    outFile.close();
  }

  /**
   * Updates the nurse information in a file with the latest data.
   *
   * @param nurses The list of nurses whose information will be updated in the file.
   * @throws FileNotFoundException If there is an issue with file operations.
   */
  public static void updateNurses(List<Nurse> nurses) throws FileNotFoundException {
    PrintWriter outFile;
    outFile = new PrintWriter("nurses.txt");
    for (Nurse nurse : nurses) {
      outFile.println(nurse.getName());
      outFile.println(nurse.getBirthDate());
      outFile.println(nurse.getAddress());
      outFile.println(nurse.getPhone());
      outFile.println(nurse.getYearsInPractice());
      outFile.println(nurse.getSkill());
    }
    outFile.close();
  }

  /**
   * Manually creates a new patient and add it to the list of patients.
   *
   * @param patients The list of existing patients.
   * @return The updated list of patients after adding the new patient.
   * @throws FileNotFoundException If there is an issue with file operations.
   */
  public static List<Patient> creatPatientManually(List<Patient> patients) throws FileNotFoundException {
    String name;
    int id = patients.size();
    LocalDate birthDate;
    String address;
    String phone;
    String gender;
    String medicalRecords;
    String currentSymptoms;
    String insuranceCompany;
    int choice = 0;
    String NO_INSURANCE_COMPANY = "No insurance company";
    String dateString;

    System.out.print("Enter patient name: ");
    name = console.nextLine();
    
    while (true) {
      
      try {
        System.out.print("Enter patient's birthDate (yyyy-MM-dd): ");
        dateString = console.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        birthDate = LocalDate.parse(dateString, formatter);
        break;

      } catch (Exception e) {
          System.out.println("Invalid date format. ");
      }
    }

    // Compare with existing patients to avoid duplication
    if(patientComparator(patients, name, birthDate)){
      System.out.println("------This patient is already registered in the system------");
      System.out.println();
      return patients;
    }
    
    System.out.print("Enter patient's address: ");
    address = console.nextLine();
    
    System.out.print("Enter patient's phone number: ");
    phone = console.nextLine();

    System.out.print("Enter patient's gender: ");
    gender = console.nextLine();

    System.out.print("Enter patient's medicalRecords: ");
    medicalRecords = console.nextLine();

    System.out.print("Enter patient's currentSymptoms: ");
    currentSymptoms = console.nextLine();

    System.out.printf("Chose patient's insuranceCompany: %n1 - %s%n2 - %s%n3 - %s%n4 - %s%n",
                      COMPANY_A.getCompanyName(), COMPANY_B.getCompanyName(), COMPANY_C.getCompanyName(), NO_INSURANCE_COMPANY);
    choice = console.nextInt();
    switch (choice) {
      case (1):
        insuranceCompany = COMPANY_A.getCompanyName();
        break;
      case (2):
        insuranceCompany = COMPANY_B.getCompanyName();
        break;
      case (3):
        insuranceCompany = COMPANY_C.getCompanyName();
        break;
      default:
        insuranceCompany = NO_INSURANCE_COMPANY;
        break;
    }

    id++;

    Patient patient = new Patient(name, birthDate, address, phone, id, gender, medicalRecords, currentSymptoms, insuranceCompany);

    patients.add(patient);
    updatePatients(patients);
    System.out.println("A new patient added!");
    System.out.println();

    return patients;
  }

  /**
   * Manually creates a new doctor and add it to the list of doctors.
   *
   * @param doctors The list of existing doctors.
   * @return The updated list of doctors after adding the new doctor.
   * @throws FileNotFoundException If there is an issue with file operations.
   */
  public static List<Doctor> creatDoctorManually(List<Doctor> doctors) throws FileNotFoundException {
    String name;
    int id = doctors.size();
    LocalDate birthDate;
    String address;
    String phone;
    String specialty;
    int yearsInPractice;
    String dateString;

    System.out.print("Enter doctor name: ");
    name = console.nextLine();

    while (true) {
      
      try {
        System.out.print("Enter doctor's birthDate (yyyy-MM-dd): ");
        dateString = console.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        birthDate = LocalDate.parse(dateString, formatter);
        break;

      } catch (Exception e) {
          System.out.println("Invalid date format. ");
      }
    }

    // Compare with existing doctors to avoid duplication
    if(doctorComparator(doctors, name, birthDate)){
      System.out.println("------This doctor is already registered in the system------");
      System.out.println();
      return doctors;
    }

    System.out.print("Enter doctor's address: ");
    address = console.nextLine();

    System.out.print("Enter doctor's phone number: ");
    phone = console.nextLine();

    System.out.print("Enter doctor's specialty: " );
    specialty = console.nextLine();

    while (true) {
      
      try {
        System.out.print("Enter yearsInPractice: ");
        yearsInPractice = console.nextInt();
        break;
      } catch (InputMismatchException e){
        console.nextLine();
        System.out.println();
        System.out.printf("------Please enter an integer------%n%");
      }
    }

    id++;

    Doctor doctor = new Doctor(name, birthDate, address, phone, id, specialty, yearsInPractice);
    doctors.add(doctor);
    updateDoctors(doctors);
    System.out.println("A new doctor added!");
    System.out.println();

    return doctors;
  }

  /**
   * Manually creates a new nurse and add it to the list of nurses.
   *
   * @param doctors The list of existing nurses.
   * @return The updated list of nurses after adding the new nurse.
   * @throws FileNotFoundException If there is an issue with file operations.
   */
  public static List<Nurse> creatNurseManually(List<Nurse> nurses) throws FileNotFoundException {
    String name;
    int id = nurses.size();
    LocalDate birthDate;
    String address;
    String phone;
    int yearsInPractice;
    String skill;
    String dateString;


    
    System.out.print("Enter nurse name: ");
    name = console.nextLine();

    while (true) {
      
      try {
        System.out.print("Enter nurse's birthDate (yyyy-MM-dd): ");
        dateString = console.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        birthDate = LocalDate.parse(dateString, formatter);
        break;

      } catch (Exception e) {
          System.out.println("Invalid date format. ");
      }
    }

    // Compare with existing nurses to avoid duplication
    if(nurseComparator(nurses, name, birthDate)){
      System.out.println("------This nurse is already registered in the system------");
      System.out.println();
      return nurses;
    }

    System.out.print("Enter nurse's address: ");
    address = console.nextLine();

    System.out.print("Enter nurse's phone number: ");
    phone = console.nextLine();

    while (true) {
      
      try {
        System.out.print("Enter yearsInPractice: ");
        yearsInPractice = console.nextInt();
        break;
      } catch (InputMismatchException e){
        console.nextLine();
        System.out.println();
        System.out.printf("------Please enter an integer------%n%");
      }
    }

    System.out.print("Enter nurse's skill: " );
    skill = console.nextLine();

    id++;

    Nurse nurse = new Nurse(name, birthDate, address, phone, id, yearsInPractice, skill);

    nurses.add(nurse);
    updateNurses(nurses);
    System.out.println("A new nurse added!");
    System.out.println();

    return nurses;
  }
}
