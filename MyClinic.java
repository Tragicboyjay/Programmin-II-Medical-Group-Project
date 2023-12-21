import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Classes.Doctor;
import Classes.Invoice;
import Classes.Nurse;
import Classes.Patient;
import Classes.Treatment;
import Services.InvoiveService;
import Services.PersonService;
import Services.TreatmentService;

public class MyClinic {
  static Scanner console = new Scanner(System.in);
  public static void main(String[] args) throws FileNotFoundException {
    int input;
    int inputCase;
    String temp = "";
    String WELCOME_MESSAGE = "Welcom to XXX Clinic!\n"
                           + "1: Create patients.\n"
                           + "2: Create Doctors.\n"
                           + "3: Create Nurses.\n"
                           + "4: Create an appointment.\n"
                           + "5: Create a treatment.\n"
                           + "6: Generate invoice.\n"
                           + "7: Print out.\n"
                           + "8: Quit.\n"
                           + "Please enter number 1-8 to choose your option: ";

    String INPUT_OPTION = "Enter 1 to input data from file, enter 2 to input data manually: ";   
    String OUTPUT_OPTION = "Enter 1 for patients, 2 for doctors, 3 for nurses to output the updated data. Enter 4 to print invoice.";                     


    System.out.print(WELCOME_MESSAGE);
    input = console.nextInt();
    System.out.println();

    List<Patient> patients = new ArrayList<>();
    List<Doctor> doctors = new ArrayList<>();
    List<Nurse> nurses = new ArrayList<>();
    List<Treatment> treatments = new ArrayList<>();
    List<Invoice> invoice = new ArrayList<>();


    while(input != 8){
      switch(input)
      {
      case 1:
        // Choose input from file or manually
        System.out.print(INPUT_OPTION);
        inputCase = console.nextInt();
        System.out.println();
          switch(inputCase)
          {
            case 1:
              patients = PersonService.creatPatientsFromFile(patients);
              break;
            case 2:
              // to be added: function of input doctors patients manually
              break;
          }
        break;
        
      case 2:
        System.out.print(INPUT_OPTION);
        inputCase = console.nextInt();
        System.out.println();
          switch(inputCase)
          {
            case 1:
              doctors = PersonService.creatDoctorsFromFile(doctors);
              break;
            case 2:
              // to be added: function of input doctors data manually
              break;
          }
        break;

      case 3:
        System.out.print(INPUT_OPTION);
        inputCase = console.nextInt();
        System.out.println();
          switch(inputCase)
          {
            case 1:
              nurses = PersonService.creatNursesFromFile(nurses);
              break;
            case 2:
              // to be added: function of input nurse data manually
              break;
          }
        break;

      case 4:
        if (patients.isEmpty() || doctors.isEmpty()) {
          temp = patients.isEmpty() ? "patient data, " : "";
          temp += doctors.isEmpty() ? "doctor data, " : "";
          System.out.println("There is no " + temp + " Please input data first.");
          break;
        }
        // to be added: function of appointmentService
        break;

      case 5:
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
            treatments = TreatmentService.creatTreatment(treatments, patients, doctors, nurses);
        }
        break;

      case 6:
        if (treatments.isEmpty()) {
          System.out.println("There is no treatment data in the system. Please input treatment first. ");
          System.out.println();
          break;
        }
        InvoiveService.creatInvoice(invoice, patients);
        break;
      case 7:
        System.out.print(OUTPUT_OPTION);
        inputCase = console.nextInt();
        System.out.println();
          switch(inputCase)
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
          }
        break;
      }
      System.out.print(WELCOME_MESSAGE);
      input = console.nextInt();
      System.out.println();
    }
    console.close();
    System.out.println("Presentation finished. Thank you!");
    System.out.println();
  }
  
}
