/**
 * Team 3 Clinic System Demo 
 * 
 * @version 1.0
 * @since 2023-01-05
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
import Services.PersonService;
import Services.TreatmentService;

public class MyClinic {
  static Scanner console = new Scanner(System.in);
  public static void main(String[] args) throws FileNotFoundException {
    boolean continueLoop = true;
    do{
      try{
        mainMenu();
        continueLoop = false;
      } catch (InputMismatchException e){
        console.nextLine();
        System.out.println();
        System.out.printf("------Please chose a number------%n%n");
      }

    } while (continueLoop);
  }

  public static void mainMenu() throws FileNotFoundException {
    int firstLevelOption = 0;
    int secondLevelOption;
    String temp = "";
    String MAIN_MENU_OPTION = "%n=======================%n"
                            + "Welcom to TEAM3 Clinic!%n"
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

    String INPUT_MENU_OPTIONS = "Enter 1 to input data from file, enter 2 to input data manually: ";   
    String OUTPUT_MENU_OPTIONS = "Enter request%n"
                                + "1 - Print patients list.%n"
                                + "2 - Print Doctors list.%n"
                                + "3 - Print Nurses list.%n"
                                + "4 - Print appointments list.%n"
                                + "5 - Print treatment list.%n"
                                + "6 - Print invoice.%n"
                                + "Please enter number 1-6 to choose your option or press any key to go back to previous menu: ";                 

    List<Patient> patients = new ArrayList<>();
    List<Doctor> doctors = new ArrayList<>();
    List<Nurse> nurses = new ArrayList<>();
    List<Treatment> treatments = new ArrayList<>();
    List<Invoice> invoice = new ArrayList<>();
    List<Appointment> appointments = new ArrayList<>();


    while(firstLevelOption != 8){
      System.out.printf(MAIN_MENU_OPTION);
      firstLevelOption = console.nextInt();
      System.out.println();

      switch(firstLevelOption)
      {
      case 1:
        // Select patient information: either input from a file or enter manually.
        System.out.print(INPUT_MENU_OPTIONS);
        secondLevelOption = console.nextInt();
        System.out.println();
        switch(secondLevelOption)
        {
          case 1:
            PersonService.creatPatientsFromFile(patients);
            break;
          case 2:
            PersonService.creatPatientManually(patients);
            break;
        }
        break;
        
      case 2:
        // Select doctor information: either input from a file or enter manually.
        System.out.print(INPUT_MENU_OPTIONS);
        secondLevelOption = console.nextInt();
        System.out.println();
        switch(secondLevelOption)
        {
          case 1:
            PersonService.creatDoctorsFromFile(doctors);
            break;
          case 2:
            PersonService.creatDoctorManually(doctors);
            break;
        }
        break;

      case 3:
        // Select nurse information: either input from a file or enter manually.
        System.out.print(INPUT_MENU_OPTIONS);
        secondLevelOption = console.nextInt();
        System.out.println();
        switch(secondLevelOption)
        {
          case 1:
            PersonService.creatNursesFromFile(nurses);
            break;
          case 2:
            PersonService.creatNurseManually(nurses);
            break;
        }
        break;

      case 4:
        // Create appointment
        if (patients.isEmpty() || doctors.isEmpty()) {
          temp = patients.isEmpty() ? "patient data, " : "";
          temp += doctors.isEmpty() ? "doctor data, " : "";
          System.out.println("There is no " + temp + " Please input data first.");
          break;
        }
        AppointmentService.creatAppointments(appointments, patients, doctors);
        break;

      case 5:
        // Create treatment
        System.out.println("Patients size: " + patients.size());
        System.out.println("Doctors size: " + doctors.size());
        System.out.println("Nurses size: " + nurses.size());
        if (patients.isEmpty() || doctors.isEmpty() || nurses.isEmpty()) {
          temp = patients.isEmpty() ? "patient data, " : "";
          temp += doctors.isEmpty() ? "doctor data, " : "";
          temp += nurses.isEmpty() ? "nurse data, " : "";
          System.out.println("There is no " + temp + " Please input data first.");
          System.out.println();
          break;
        } else {
            TreatmentService.creatTreatment(treatments, patients, doctors, nurses);
        }
        break;

      case 6:
        // Create invoice
        if (treatments.isEmpty()) {
          System.out.println("There is no treatment data in the system. Please input treatment first. ");
          System.out.println();
          break;
        }
        InvoiveService.creatInvoice(invoice, patients);
        break;
      case 7:
        // Export data
        System.out.printf(OUTPUT_MENU_OPTIONS);
        secondLevelOption = console.nextInt();
        System.out.println();
          switch(secondLevelOption)
          {
            case 1:
              PersonService.printOutPatients(patients);
              break;
            case 2:
              PersonService.printOutDoctors(doctors);
              break;
            case 3:
              PersonService.printOutNurses(nurses);
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
      }
    }
    console.close();
    System.out.println("Thank you for using TEAM 3 Clinic system!");
    System.out.println();
  }
  
}
