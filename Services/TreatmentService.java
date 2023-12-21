package Services;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Classes.Doctor;
import Classes.Nurse;
import Classes.Patient;
import Classes.Treatment;

public class TreatmentService {
  static Scanner console = new Scanner(System.in);
  public static List<Treatment> creatTreatment(List<Treatment> treatments, List<Patient> patients, List<Doctor> doctors, List<Nurse> nurses) {
    int treatmentId;
    String patientName = "";
    String doctorName = "";
    String nurseName = "";
    String treatmentDescription;
    LocalDate startDate;
    LocalDate endDate;
    boolean diagnosticTest;
    int i = treatments.size();
    boolean found = false;
    String dateString;
    List<Patient> assignedPatients = new ArrayList<>();
    List<Treatment> treatmentOfPatient = new ArrayList<>();

    Patient patient = new Patient();
    Doctor doctor = new Doctor();

    // Chech whether the patient exist in the record.
    while (!found) {
      System.out.print("Please enter the name of the patient: ");
      patientName = console.nextLine();

      for (Patient o : patients) {
          if (o.getName().equals(patientName)) {
              found = true;
              patient = o;
              treatmentOfPatient = o.getTreatments();
              break;
          }
      }

      if (!found) {
          System.out.println("Patient not found. Please enter a valid name.");
      }
    }

    found = false;

    while (!found) {
      System.out.print("Please enter the name of the doctor: ");
      doctorName = console.nextLine();

      for (Doctor o : doctors) {
          if (o.getName().equals(doctorName)) {
              found = true;
              doctor = o;
              assignedPatients = doctor.getAssignedPatients();
              break;
          }
      }

      if (!found) {
          System.out.println("Doctor not found. Please enter a valid name.");
      }
    }

    found = false;

    while (!found) {
      System.out.print("Please enter the name of the nurse: ");
      nurseName = console.nextLine();

      for (Nurse o : nurses) {
          if (o.getName().equals(nurseName)) {
              found = true;
              break;
          }
      }

      if (!found) {
          System.out.println("Nurse not found. Please enter a valid name.");
      }
    }

    System.out.print("Please enter treatment description: ");
    treatmentDescription = console.nextLine();

    // Input start date with correct format
    while (true) {
      System.out.print("Please enter treatment start Date (yyyy-MM-dd): ");
      dateString = console.nextLine();

      try {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

          startDate = LocalDate.parse(dateString, formatter);
          break;

      } catch (Exception e) {
          System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
      }
    }

    // Input end date with correct format
    while (true) {
      System.out.print("Please enter treatment end Date (yyyy-MM-dd): ");
      dateString = console.nextLine();

      try {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

          endDate = LocalDate.parse(dateString, formatter);
          break;

      } catch (Exception e) {
          System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
      }
    }

    System.out.print("Does the patient need diagnostic test?(true or false) ");
    diagnosticTest = console.nextBoolean();
    System.out.println();

    treatmentId = i++;

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
