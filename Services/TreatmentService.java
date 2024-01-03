package Services;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Classes.Doctor;
import Classes.Nurse;
import Classes.Patient;
import Classes.Treatment;

public class TreatmentService {
  static Scanner console = new Scanner(System.in);
  public static List<Treatment> creatTreatment(List<Treatment> treatments, List<Patient> patients, List<Doctor> doctors, List<Nurse> nurses) {
    int treatmentId = treatments.size();
    String patientName = "";
    String doctorName = "";
    String nurseName = "";
    String treatmentDescription;
    LocalDate startDate;
    LocalDate endDate;
    boolean diagnosticTest;
    boolean found = false;
    String dateString;
    List<Patient> assignedPatients = new ArrayList<>();
    List<Treatment> treatmentOfPatient = new ArrayList<>();

    Patient patient = new Patient();
    Doctor doctor = new Doctor();

    // Chech whether the patient exists in the record.
    while (!found) {
      System.out.print("Please enter the name of the patient: ");
      patientName = console.nextLine();

      for (Patient o : patients) {
          if (o.getName().equalsIgnoreCase(patientName)) {
              found = true;
              patient = o;
              patientName = o.getName();
              treatmentOfPatient = o.getTreatments();
              break;
          }
      }

      if (!found) {
          System.out.println("Patient not found. Please enter a valid name.");
      }
    }

    found = false;
    // Chech whether the doctor exists in the record.
    while (!found) {
      System.out.print("Please enter the name of the doctor: ");
      doctorName = console.nextLine();

      for (Doctor o : doctors) {
          if (o.getName().equalsIgnoreCase(doctorName)) {
              found = true;
              doctor = o;
              doctorName = o.getName();
              assignedPatients = doctor.getAssignedPatients();
              break;
          }
      }

      if (!found) {
          System.out.println("Doctor not found. Please enter a valid name.");
      }
    }

    found = false;
    // Chech whether the nurse exists in the record.
    while (!found) {
      System.out.print("Please enter the name of the nurse: ");
      nurseName = console.nextLine();

      for (Nurse o : nurses) {
          if (o.getName().equalsIgnoreCase(nurseName)) {
              found = true;
              nurseName = o.getName();
              break;
          }
      }

      if (!found) {
          System.out.println("Nurse not found. Please enter a valid name.");
      }
    }
    // Input treatment description
    System.out.print("Please enter treatment description: ");
    treatmentDescription = console.nextLine();

    // Input start date with correct format
    while (true) {

      try {
        System.out.print("Please enter treatment start Date (yyyy-MM-dd): ");
        dateString = console.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        startDate = LocalDate.parse(dateString, formatter);
        break;

      } catch (Exception e) {
          System.out.println("Invalid date format. ");
      }
    }

    // Input end date with correct format
    while (true) {
      
      try {
        System.out.print("Please enter treatment end Date (yyyy-MM-dd): ");
        dateString = console.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        endDate = LocalDate.parse(dateString, formatter);
        break;

      } catch (Exception e) {
          System.out.println("Invalid date format. ");
      }
    }

    // Input diagnosticTest with correct format
    while (true) {
      
      try {
        System.out.print("Does the patient need diagnostic test?(true or false) ");
        diagnosticTest = console.nextBoolean();
        System.out.println();
        break;
      } catch (InputMismatchException e) {
        System.out.printf("You must enter true or false. Please try again.%n%n");
      }
    }

    treatmentId ++;

    Treatment treatment = new Treatment(treatmentId, patientName, doctorName, nurseName, treatmentDescription, startDate, endDate, diagnosticTest);
    // Update patient information by adding attending doctor and treatment
    patient.setAttendingDoctor(doctorName);
    treatmentOfPatient.add(treatment);
    patient.setTreatments(treatmentOfPatient);

    // Update doctor information by adding assigned patient
    assignedPatients.add(patient);
    doctor.setAssignedPatients(assignedPatients);

    treatments.add(treatment);

    System.out.println("A new treatment No." + treatmentId + "has been registered!");

    return treatments;
  }
}