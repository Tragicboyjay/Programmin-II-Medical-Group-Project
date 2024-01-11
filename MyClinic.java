/**
 * Team 3 Clinic System Demo 
 * 
 * @version 2.0
 * @since 2023-12-18
 * @author Team3 (Fei Li, Timothy Lazurka, Justice Manly, Jie Li)
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Classes.Appointment;
import Classes.Doctor;
import Classes.Invoice;
import Classes.Nurse;
import Classes.Patient;
import Classes.Treatment;
import Services.AppointmentService;
import Services.InvoiveService;
import Services.PatientService;
import Services.DoctorService;
import Services.NurseService;
import Services.TreatmentService;

public class MyClinic {
  static Scanner console = new Scanner(System.in);
  public static void main(String[] args) throws FileNotFoundException {
    boolean continueLoop = true;
    do{
      try{
        // Display the main menu and handle potential InputMismatchException
        mainMenu();
        continueLoop = false;
      } catch (InputMismatchException e){
         // Catch exception if user enters invalid input
        console.nextLine();
        System.out.println();
        System.out.printf("------Please chose a number------%n%n");
      }

    } while (continueLoop);

    // Close the Scanner to prevent resource leak
    console.close();
    System.out.println("Thank you for using TEAM 3 Clinic system!");
    System.out.println();
  }

  /**
   * Displays the main menu and handles user input.
   *
   * @throws FileNotFoundException If a file required for operation is not found.
   */
  public static void mainMenu() throws FileNotFoundException {
    // Declarations and initialization of variables and lists
    int firstLevelOption = 0;
    int secondLevelOption;
    String temp = "";

    // Main menu options
    String MAIN_MENU_OPTION = "%n=======================%n"
                            + "Welcome to TEAM 3 Clinic!%n"           
                            + "=======================%n"
                            + "Enter request%n"
                            + "1 - Create patients.%n"
                            + "2 - Create Doctors.%n"
                            + "3 - Create Nurses.%n"
                            + "4 - Create an appointment.%n"
                            + "5 - Create a treatment.%n"
                            + "6 - Generate invoice.%n"
                            + "7 - Export data.%n"
                            + "8 - Quit.%n"
                            + "Please enter number 1-8 to choose your option: ";

    // Input menu options
    String INPUT_MENU_OPTIONS = "Enter 1 to input data from file, enter 2 to insert or update data manually: ";  
    
    // Output menu options
    String OUTPUT_MENU_OPTIONS = "Enter request%n"
                                + "1 - Print patients list.%n"
                                + "2 - Print Doctors list.%n"
                                + "3 - Print Nurses list.%n"
                                + "4 - Print appointments list.%n"
                                + "5 - Print treatment list.%n"
                                + "6 - Print invoice.%n"
                                + "Please enter number 1-6 to choose your option or press any key to go back to previous menu: ";                 

    // Lists to store data
    List<Patient> patients = new ArrayList<>();
    List<Doctor> doctors = new ArrayList<>();
    List<Nurse> nurses = new ArrayList<>();
    List<Treatment> treatments = new ArrayList<>();
    List<Invoice> invoice = new ArrayList<>();
    List<Appointment> appointments = new ArrayList<>();

    // Main loop to display and handle user input based on main menu options
    while(firstLevelOption != 8){
      // Display the main menu and read the user's choice
      System.out.printf(MAIN_MENU_OPTION);
      firstLevelOption = console.nextInt();
      System.out.println();

      // Switch based on the main menu option chosen by the user
      switch(firstLevelOption) {

        case 1:
          // Sub-menu for creating or updating Patients
          System.out.print(INPUT_MENU_OPTIONS);
          secondLevelOption = console.nextInt();
          System.out.println();
          switch(secondLevelOption) {
            case 1:
              PatientService.creatPatientsFromFile(patients);
              break;
            case 2:
              PatientService.insertUpdatePatient(patients);
              break;
          }
          break;
          
        case 2:
          // Sub-menu for creating or updating Doctors
          System.out.print(INPUT_MENU_OPTIONS);
          secondLevelOption = console.nextInt();
          System.out.println();
          switch(secondLevelOption)
          {
            case 1:
              DoctorService.creatDoctorsFromFile(doctors);
              break;
            case 2:
              DoctorService.insertUpdateDoctor(doctors);
              break;
          }
          break;

        case 3:
          // Sub-menu for creating or updating Nurses
          System.out.print(INPUT_MENU_OPTIONS);
          secondLevelOption = console.nextInt();
          System.out.println();

          // Inner switch for the second-level options
          switch(secondLevelOption)
          {
            // If the user chose option 1, call the creatNursesFromFile method from NurseService
            case 1:
              NurseService.creatNursesFromFile(nurses);
              break;
            // If the user chose option 2, call the insertUpdateNurse method from NurseService
            case 2:
              NurseService.insertUpdateNurse(nurses);
              break;
          }
          break;

        case 4:
          // Case for creating appointments
          if (patients.isEmpty() || doctors.isEmpty()) {
            temp = patients.isEmpty() ? "patient data, " : "";
            temp += doctors.isEmpty() ? "doctor data, " : "";
            System.out.println("There is no " + temp + " Please input data first.");
            break;
          }
          // If there are patients and doctors, create appointments
          AppointmentService.creatAppointments(appointments, patients, doctors);
          break;

        case 5:
          // Case for creating treatments
          System.out.println("Number of Patients: " + patients.size());
          System.out.println("Number of Doctors: " + doctors.size());
          System.out.println("Number of Nurses: " + nurses.size());
          if (patients.isEmpty() || doctors.isEmpty() || nurses.isEmpty()) {
            temp = patients.isEmpty() ? "patient data, " : "";
            temp += doctors.isEmpty() ? "doctor data, " : "";
            temp += nurses.isEmpty() ? "nurse data, " : "";
            System.out.println("There is no " + temp + " Please input data first.");
            System.out.println();
            break;
          } else {
            // If there are patients, doctors, and nurses, create treatments
            TreatmentService.creatTreatment(treatments, patients, doctors, nurses);
          }
          break;

        case 6:
          // Case for generating invoices
          if (treatments.isEmpty()) {
            System.out.println("There is no treatment data in the system. Please input treatment first. ");
            System.out.println();
            break;
          }
          // If there are treatments, generate invoices
          InvoiveService.creatInvoice(invoice, patients);
          break;

        case 7:
          // Sub-menu for printing data
          System.out.printf(OUTPUT_MENU_OPTIONS);
          secondLevelOption = console.nextInt();
          System.out.println();
            switch(secondLevelOption) {
              case 1:
                PatientService.printOutPatients(patients);
                break;
              case 2:
                DoctorService.printOutDoctors(doctors);
                break;
              case 3:
                NurseService.printOutNurses(nurses);
                break;
              case 4:
                AppointmentService.printOutAppointments(appointments);
                break;
              case 5:
                TreatmentService.printOutTreatments(treatments);
                break;
              case 6:
                InvoiveService.printInvoice(invoice);
                break;
            }
          break;
          
        // Testing-specific hidden option, can import all person data at once
        case 999:
          PatientService.creatPatientsFromFile(patients);
          DoctorService.creatDoctorsFromFile(doctors);
          NurseService.creatNursesFromFile(nurses);
          break;
      }
    }
  }
}
