package Services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Classes.Nurse;

public class NurseService {
  static Scanner console = new Scanner(System.in);

  /**
   * Creates nurses by reading data from a file and adds them to the list of nurses.
   *
   * @param nurses The list of existing nurses.
   * @return The updated list of nurses after adding nurses from the file.
   * @throws FileNotFoundException If the file is not found or there is an issue with file operations.
   */
  public static void creatNursesFromFile(List<Nurse> nurses) throws FileNotFoundException {
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

      if(nurseComparator(nurses, name, birthDate) != null){
        System.out.printf("%n------This nurse is already registered in the system------%n%n");
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
  }

  /**
   * Collects information about a nurse and either adds a new nurse or updates existing
   * nurse information based on user input. The method prompts the user to enter nurse
   * details such as name, birth date, address, phone number, years in practice, and skill.
   * If the nurse is already registered, the user has the option to update the information.
   * The updated or new nurse is then added to the list of nurses, and the nurse data is
   * saved to a file.
   *
   * @param nurses The list of nurses to which the new nurse or updated information will
   *               be added.
   * @throws FileNotFoundException If an error occurs while attempting to update the nurse
   *                               data file.
   */
  public static void insertUpdateNurse(List<Nurse> nurses) throws FileNotFoundException {
    String name;
    int id = nurses.size();
    LocalDate birthDate;
    String address;
    String phone;
    int yearsInPractice;
    String skill;
    String dateString;
    String input = "";
    boolean update = false;
    Nurse nurse;
    
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
    if(nurseComparator(nurses, name, birthDate) != null){
      System.out.printf("%n------This nurse is already registered in the system------%n%n");
      System.out.println("Do you want to update the information of this nurse?(yes to confirm, other to cancel)");
      input = console.nextLine();
  
      if(!input.equalsIgnoreCase("yes")){
        return;
      }  
    }
    
    // address input validation 
    while (true) {
      try {
        System.out.print("Enter nurse's address: ");
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

    // phone number input validation 
    while (true) {

      try{
        System.out.print("Enter nurse's phone number (XXX-XXX-XXXX): ");
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

    System.out.print("Enter nurse's skill: " );
    skill = console.nextLine();

    id++;

    if (update) {
      nurse = nurseComparator(nurses, name, birthDate);
      nurse.setAddress(address);
      nurse.setPhone(phone);
      nurse.setYearsInPractice(yearsInPractice);
      nurse.setSkill(skill);
      System.out.println("Information of " + name + " is updated!");
    } else {
      nurse = new Nurse(name, birthDate, address, phone, id, yearsInPractice, skill);
      nurses.add(nurse);
      System.out.println("A new nurse added!");
    }

    updateNurses(nurses);
    System.out.println();
  }


  /**
   * Compares a nurse's name and birth date with existing nurses in the list.
   *
   * @param doctors The list of existing nurses.
   * @param name     The name of the nurse to compare.
   * @param birthDate The birth date of the nurse to compare.
   * @return True if a nurse with the same name and birth date already exists in the list, false otherwise.
   */
  public static Nurse nurseComparator(List<Nurse> nurses, String name, LocalDate birthDate) {
    for (Nurse nurse : nurses) {
      if (nurse.getName().toUpperCase().equals(name.toUpperCase()) && nurse.getBirthDate().equals(birthDate)){
        return nurse;
      }
    }
    return null;
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
}
