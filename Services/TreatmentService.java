package Services;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

  /**
   * Creates a new treatment and associates it with a patient, doctor, and nurse.
   * Collects information about the patient, doctor, and nurse involved in the
   * treatment, as well as details such as treatment description, start and end dates,
   * and the need for a diagnostic test. Updates patient information by adding the
   * attending doctor and treatment. Also updates the doctor information by adding the
   * assigned patient. The created treatment is added to the list of treatments.
   *
   * @param treatments   The list of treatments where the new treatment will be added.
   * @param patients     The list of patients to associate with the treatment.
   * @param doctors      The list of doctors to associate with the treatment.
   * @param nurses       The list of nurses to associate with the treatment.
   */
  public static void creatTreatment(List<Treatment> treatments, List<Patient> patients, List<Doctor> doctors, List<Nurse> nurses) {
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

    System.out.printf("%nA new treatment No.%d has been registered!%n",  treatmentId);
  }

  /**
   * Prints out the details of each treatment in the provided list of treatments.
   * Writes the treatment information to a text file named "Treatments_of_Clinic.txt"
   * and also displays the information on the console. This method facilitates the
   * documentation and record-keeping of treatments within the clinic.
   *
   * @param treatments   The list of treatments to be printed and recorded.
   * @throws FileNotFoundException If the specified file "Treatments_of_Clinic.txt" is not found.
   */
  public static void printOutTreatments(List<Treatment> treatments) throws FileNotFoundException {
    PrintWriter outFile;
    outFile = new PrintWriter("Treatments_of_Clinic.txt");
    for (Treatment treatment : treatments) {
      outFile.println(treatment);
      System.out.println(treatment);
    }
    System.out.printf("%nPlease find the updated data in Treatments_of_Clinic.txt%n");
    outFile.close();
  }
}
