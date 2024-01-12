package Services;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import Classes.Appointment;
import Classes.Doctor;
import Classes.Patient;

public class AppointmentService {
  static Scanner console = new Scanner(System.in);

  /**
   * Creates a new appointment by collecting information such as patient name, doctor name,
   * phone number, and appointment date from the user. It validates whether the provided
   * patient and doctor names exist in the respective records. If not, the user is prompted
   * to either create a new patient or enter a valid name. The appointment date is entered
   * with the format "yyyy-MM-dd HH:mm:ss". The new appointment is then added to the list of
   * appointments, and relevant details are displayed.
   *
   * @param appointments The list of appointments to which the new appointment will be added.
   * @param patients     The list of patients to check and retrieve patient information.
   * @param doctors      The list of doctors to check and retrieve doctor information.
   */
  public static void creatAppointments(List<Appointment> appointments, List<Patient> patients, List<Doctor> doctors) throws FileNotFoundException {
    int appointmentId = appointments.size();
    String patientName = "";
    String doctorName = "";
    String phone = "";
    LocalDateTime date;
    boolean found = false;
    String print = "";
    String dateString;

    // To check whether this patient is in the record.
    while (!found) {
      System.out.print("Please enter the name of the patient: ");
      patientName = console.nextLine();

      for (Patient o : patients) {
          if (o.getName().equalsIgnoreCase(patientName)) {
              found = true;
              patientName = o.getName();
              phone = o.getPhone();
              break;
          }
      }

      if (!found) {
        System.out.println("Patient not found. Do you want to create a patient(Y) or re-enter a valid name(R)or go back to main menu(N)?");
        print = console.nextLine();

        if(print.equalsIgnoreCase("Y")){
          PatientService.insertUpdatePatient(patients);
        } else if(print.equalsIgnoreCase("R")){
        } else if(print.equalsIgnoreCase("N")){
          return;
        }
      }
    }

    found = false;
    // To check whether this doctor is in the record.
    while (!found) {
      System.out.print("Please enter the name of the doctor: ");
      doctorName = console.nextLine();

      for (Doctor o : doctors) {
          if (o.getName().equalsIgnoreCase(doctorName)) {
              found = true;
              doctorName = o.getName();
              break;
          }
      }

      if (!found) {
          System.out.println("Doctor not found. Please enter a valid name.");
      }
    }

    // Input start date with correct format
    while (true) {

      try {
        System.out.print("Please enter appointment Date (yyyy-MM-dd HH:mm:ss): ");
        dateString = console.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        date = LocalDateTime.parse(dateString, formatter);
        break;

      } catch (Exception e) {
          System.out.println("Invalid date and time format.");
      }
    }

    appointmentId++;

    Appointment appointment = new Appointment(appointmentId, patientName, doctorName, phone, date);

    appointments.add(appointment);

    System.out.println("A new appointment created.");
    System.out.println("==========================");
    System.out.println(appointment);
  }

  /**
   * Prints the details of each appointment in the provided list of appointments
   * to a file named "Appointments_of_Clinic.txt". The information is also displayed
   * on the console. After printing, a message is shown indicating where the updated
   * data can be found.
   *
   * @param appointments The list of appointments whose details will be printed.
   * @throws FileNotFoundException If the specified file "Appointments_of_Clinic.txt"
   *                               cannot be created or opened for writing.
   */
  public static void printOutAppointments(List<Appointment> appointments) throws FileNotFoundException {
    PrintWriter outFile;
    outFile = new PrintWriter("Appointments_of_Clinic.txt");
    for (Appointment appointment : appointments) {
      outFile.println(appointment);
      System.out.println(appointment);
    }
    System.out.println("Please find the updated data in Appointments_of_Clinic.txt");
    outFile.close();
  }
  
}
