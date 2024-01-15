package Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import static Type.InsuranceCompany.COMPANY_A;
import static Type.InsuranceCompany.COMPANY_B;
import static Type.InsuranceCompany.COMPANY_C;

import Classes.Patient;

public class PatientService {
  static Scanner console = new Scanner(System.in);

  /**
   * Creates patients by reading data from a file and adds them to the list of patients.
   *
   * @param patients The list of existing patients.
   * @return The updated list of patients after adding patients from the file.
   * @throws FileNotFoundException If the file is not found or there is an issue with file operations.
   */
  public static void creatPatientsFromFile(List<Patient> patients) throws FileNotFoundException {
    String name;
    int id = 0;
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
      if(patientComparator(patients, name, birthDate) != null){
        System.out.printf("%n------This patient is already registered in the system------%n%n");
        continue;
      }

      if(patients.isEmpty()) {
        id = 1;
      } else {
        id = patients.get(patients.size() - 1).getPatientId() + 1;
      }

      Patient patient = new Patient(name, birthDate, address, phone, id, gender, medicalRecords, currentSymptoms, insuranceCompany);

      patients.add(patient);
      System.out.println("A new patient added!");
      System.out.println();
    }
    updatePatients(patients);
    inFile.close();
  }

  /**
   * Collects information about a patient and either adds a new patient or updates existing
   * patient information based on user input. The method prompts the user to enter patient
   * details such as name, birth date, address, phone number, gender, medical records,
   * current symptoms, and insurance company. If the patient is already registered, the user
   * has the option to update the information. The updated or new patient is then added to
   * the list of patients, and the patient data is saved to a file.
   *
   * @param patients The list of patients to which the new patient or updated information
   *                 will be added.
   * @throws FileNotFoundException If an error occurs while attempting to update the patient
   *                               data file.
   */
  public static void insertUpdatePatient(List<Patient> patients) throws FileNotFoundException {
    String name;
    int id = patients.size();
    LocalDate birthDate;
    String address;
    String phone;
    int genderChoice = 0;
    String gender = "";
    String medicalRecords;
    String currentSymptoms;
    String insuranceCompany;
    int choice = 0;
    String NO_INSURANCE_COMPANY = "No insurance company";
    String dateString;
    String input = "";
    boolean update = false;
    Patient patient;


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
    if(patientComparator(patients, name, birthDate) != null){
      System.out.printf("%n------This patient is already registered in the system------%n%n");
      System.out.println("Do you want to update the information of this patient?(yes to confirm, other to cancel)");
      input = console.nextLine();
      update = input.equalsIgnoreCase("yes");

      if(!update) {
        return;
      }
    }
    
    while (true) {
      try {
          System.out.print("Enter patient's address: ");
          address = console.nextLine();
  
          // Allow letters, numbers, and spaces in the address
          if (address.matches("[A-Za-z0-9\\s]+")) {
              break;
          } else {
              throw new Exception("The address input was invalid. Please try again.");
          }
  
      } catch (Exception e) {
          System.out.println(e.getMessage());
      }
  }

    
    while (true) {

      try{
        System.out.print("Enter patient's phone number (XXX-XXX-XXXX): ");
        phone = console.nextLine();

        if (phone.matches("^[2-9][0-9]{2}-[0-9]{3}-[0-9]{4}")) {
          break;
        } 
        else {
          throw new Exception("Invalid phone number input please try again.");
        }
         
      }
      catch (Exception e){
        System.out.println(e.getMessage());
      }
    }


    while (genderChoice != 1 & genderChoice != 2 & genderChoice != 3) {
      
      System.out.print("Enter patient's gender (1: Male, 2: Female, 3: other(s)): ");
      genderChoice = console.nextInt();

      switch (genderChoice) {
        case (1):
          gender = "Male";
          console.nextLine();
          break;

        case (2):
          gender = "Female";
          console.nextLine();
          break;

        case (3):
          gender = "Other";
          break;
      
        default:
          System.out.print("Your input is invalid please try again.");
          console.nextLine();
          break;
      }
      
    }


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

    if (update) {
      patient = patientComparator(patients, name, birthDate);
      patient.setAddress(address);
      patient.setPhone(phone);
      patient.setGender(gender);
      patient.setMedicalRecords(medicalRecords);
      patient.setCurrentSymptoms(currentSymptoms);
      patient.setInsuranceCompany(insuranceCompany);
      System.out.println("Information of " + name + " is updated!");
    } else {
      
      if(patients.isEmpty()) {
        id = 1;
      } else {
        id = patients.get(patients.size() - 1).getPatientId() + 1;
      }

      patient = new Patient(name, birthDate, address, phone, id, gender, medicalRecords, currentSymptoms, insuranceCompany);
      patients.add(patient);
      System.out.println("A new patient added!");
    }

    updatePatients(patients);
    System.out.println();
  }

  /**
   * Compares a patient's name and birth date with existing patients in the list.
   *
   * @param patients The list of existing patients.
   * @param name     The name of the patient to compare.
   * @param birthDate The birth date of the patient to compare.
   * @return True if a patient with the same name and birth date already exists in the list, false otherwise.
   */
  public static Patient patientComparator(List<Patient> patients, String name, LocalDate birthDate) {
    for (Patient patient : patients) {
      if (patient.getName().toUpperCase().equals(name.toUpperCase()) && patient.getBirthDate().equals(birthDate)){
        return patient;
      }
    }
    return null;
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
    Formatter output;
        try {
            // Provide the file pat
            File file = new File("patients.txt");
            output = new Formatter(file); 
        } catch (SecurityException securityException) {
            System.err.println("Write permission denied. Terminating.");
            System.exit(1); 
            return; 
        }

    for (Patient patient : patients) {
      output.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n", patient.getName(), patient.getBirthDate(), patient.getAddress(), patient.getPhone(), patient.getGender(), patient.getMedicalRecords(),patient.getCurrentSymptoms(), patient.getInsuranceCompany());
    }
    output.close();
    // PrintWriter outFile;
    // outFile = new PrintWriter("patients.txt");
    // for (Patient patient : patients) {
    //   outFile.println(patient.getName());
    //   outFile.println(patient.getBirthDate());
    //   outFile.println(patient.getAddress());
    //   outFile.println(patient.getPhone());
    //   outFile.println(patient.getGender());
    //   outFile.println(patient.getMedicalRecords());
    //   outFile.println(patient.getCurrentSymptoms());
    //   outFile.println(patient.getInsuranceCompany());
    // }
    // outFile.close();
  }
}
