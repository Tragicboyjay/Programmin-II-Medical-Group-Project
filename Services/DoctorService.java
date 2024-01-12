package Services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Classes.Doctor;

public class DoctorService {
  static Scanner console = new Scanner(System.in);

  /**
   * Creates doctors by reading data from a file and adds them to the list of doctors.
   *
   * @param doctors The list of existing doctors.
   * @return The updated list of doctors after adding doctors from the file.
   * @throws FileNotFoundException If the file is not found or there is an issue with file operations.
   */
  public static void creatDoctorsFromFile(List<Doctor> doctors) throws FileNotFoundException {
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

      if(doctorComparator(doctors, name, birthDate) != null){
        System.out.printf("%n------This doctor is already registered in the system------%n%n");
        continue;
      }

      id++;

      Doctor doctor = new Doctor(name, birthDate, address, phone, id, specialty, yearsInPractice);
      doctors.add(doctor);
      System.out.println("A new doctor added!");
      System.out.println();
    }
    updateDoctors(doctors);
    inFile.close();
  }

  /**
   * Collects information about a doctor and either adds a new doctor or updates existing
   * doctor information based on user input. The method prompts the user to enter doctor
   * details such as name, birth date, address, phone number, specialty, and years in practice.
   * If the doctor is already registered, the user has the option to update the information.
   * The updated or new doctor is then added to the list of doctors, and the doctor data is
   * saved to a file.
   *
   * @param doctors The list of doctors to which the new doctor or updated information will
   *                be added.
   * @throws FileNotFoundException If an error occurs while attempting to update the doctor
   *                               data file.
   */
  public static void insertUpdateDoctor(List<Doctor> doctors) throws FileNotFoundException {
    String name;
    int id = doctors.size();
    LocalDate birthDate;
    String address;
    String phone;
    String specialty;
    int yearsInPractice;
    String dateString;
    String input = "";
    boolean update = false;
    Doctor doctor;

    System.out.print("Enter doctor's name: ");
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
    if(doctorComparator(doctors, name, birthDate) != null){
      System.out.printf("%n------This doctor is already registered in the system------%n%n");
      System.out.println("Do you want to update the information of this doctor?(yes to confirm, other to cancel)");
      input = console.nextLine();
      update = input.equalsIgnoreCase("yes");

      if(!update) {
        return;
      }
    }


    while (true) {
      try {
        System.out.print("Enter doctor's address: ");
        address = console.nextLine();
        
        if (address.matches("[A-Za-z0-9]+")) {
          break;
        }
        else {
          throw new Exception("The address input was invalid please try again.");
        }

      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }



    while (true) {

      try{
        System.out.print("Enter doctor's phone number (XXX-XXX-XXXX): ");
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
    
    System.out.print("Enter doctor's specialty: " );
    specialty = console.nextLine();

    while (true) {
      
      try {
        System.out.print("Enter yearsInPractice: ");
        yearsInPractice = console.nextInt();
        break;
      } catch (InputMismatchException e){
        console.nextLine();
        System.out.printf("%n------Please enter an integer------%n%");
      }
    }

    id++;

    if (update) {
      doctor = doctorComparator(doctors, name, birthDate);
      doctor.setAddress(address);
      doctor.setPhone(phone);
      doctor.setSpecialty(specialty);
      doctor.setYearsInPractice(yearsInPractice);
      System.out.println("Information of " + name + " is updated!");
    } else {
      doctor = new Doctor(name, birthDate, address, phone, id, specialty, yearsInPractice);
      doctors.add(doctor);
      System.out.println("A new doctor added!");
    }

    updateDoctors(doctors);
    System.out.println();
  }

  /**
   * Compares a doctor's name and birth date with existing doctors in the list.
   *
   * @param doctors The list of existing doctors.
   * @param name     The name of the doctor to compare.
   * @param birthDate The birth date of the doctor to compare.
   * @return True if a doctor with the same name and birth date already exists in the list, false otherwise.
   */
  public static Doctor doctorComparator(List<Doctor> doctors, String name, LocalDate birthDate) {
    for (Doctor doctor : doctors) {
      if (doctor.getName().toUpperCase().equals(name.toUpperCase()) && doctor.getBirthDate().equals(birthDate)){
        return doctor;
      }
    }
    return null;
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
}
