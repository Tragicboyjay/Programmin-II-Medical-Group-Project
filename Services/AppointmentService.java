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

  public static List<Appointment> creatAppointments(List<Appointment> appointments, List<Patient> patients, List<Doctor> doctors) {
    int appointmentId = appointments.size();
    String patientName = "";
    String doctorName = "";
    String phone = "";
    LocalDateTime date;
    boolean found = false;
    String print = "";
    String dateString;

    System.out.print("Please enter the name of the patient: ");

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
          System.out.println("Patient not found. Do you want to create a patient(Y) or enter a valid name?");
          print = console.next();

          if(print.equalsIgnoreCase("Y")){
            // to be added: function of input doctors patients manually
            found = true;
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

    return appointments;
  }

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
