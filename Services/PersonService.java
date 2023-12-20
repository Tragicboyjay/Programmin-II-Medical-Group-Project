package Services;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import Classes.Doctor;
import Classes.Nurse;
import Classes.Patient;

public class PersonService {
  static Scanner console = new Scanner(System.in);
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
        System.out.println("This patient is already exist.");
        System.out.println();
        continue;
      }

      id++;

      Patient patient = new Patient(name, birthDate, address, phone, id, gender, medicalRecords, currentSymptoms, insuranceCompany);

      patients.add(patient);
      System.out.println("A new patient added!");
      System.out.println();
    }
    inFile.close();
    return patients;
  }

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
        System.out.println("This doctor is already exist.");
        System.out.println();
        continue;
      }

      id++;

      Doctor doctor = new Doctor(name, birthDate, address, phone, id, specialty, yearsInPractice);
      doctors.add(doctor);
      System.out.println("A new doctor added!");
      System.out.println();
    }
    inFile.close();
    return doctors;
  }

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
      System.out.println("A new nurse added!");
      System.out.println();
    }
    inFile.close();
    return nurses;
  }
  // Use to check the duplication
  public static boolean patientComparator(List<Patient> patients, String name, LocalDate birthDate) {
    for (Patient patient : patients) {
      if (patient.getName().toUpperCase().equals(name.toUpperCase()) && patient.getBirthDate().equals(birthDate)){
        return true;
      }
    }
    return false;
  }

  public static boolean doctorComparator(List<Doctor> doctors, String name, LocalDate birthDate) {
    for (Doctor doctor : doctors) {
      if (doctor.getName().toUpperCase().equals(name.toUpperCase()) && doctor.getBirthDate().equals(birthDate)){
        return true;
      }
    }
    return false;
  }

  public static boolean nurseComparator(List<Nurse> nurses, String name, LocalDate birthDate) {
    for (Nurse nurse : nurses) {
      if (nurse.getName().toUpperCase().equals(name.toUpperCase()) && nurse.getBirthDate().equals(birthDate)){
        return true;
      }
    }
    return false;
  }

  // Print the data out and export to txt file
  public static void printOutPatients(List<Patient> patients) throws FileNotFoundException {
    PrintWriter outFile;
    outFile = new PrintWriter("updatedPatients.txt");
    for (Patient patient : patients) {
      outFile.println(patient);
      System.out.println(patient);
    }
    System.out.println("Please find the updated data in updatedPatients.txt");
    outFile.close();
  }

  public static void printOutDoctors(List<Doctor> doctors) throws FileNotFoundException {
    PrintWriter outFile;
    outFile = new PrintWriter("updatedDoctors.txt");
    for (Doctor doctor : doctors) {
      outFile.println(doctor);
      System.out.println(doctor);
    }
    System.out.println("Please find the updated data in updatedDoctors.txt");
    outFile.close();
  }

  public static void printOutNurses(List<Nurse> nurses) throws FileNotFoundException {
    PrintWriter outFile;
    outFile = new PrintWriter("updatedNurses.txt");
    for (Nurse nurse : nurses) {
      outFile.println(nurse);
      System.out.println(nurse);
    }
    System.out.println("Please find the updated data in updatedNursers.txt");
    outFile.close();
  }
}
