package Services;

import Classes.Invoice;
import Classes.Patient;
import Classes.Treatment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InvoiveService {
  static Scanner console = new Scanner(System.in);
  public static List<Invoice> creatInvoice(List<Invoice> invoceList, List<Patient> patients) {
    int invoiceId;
    String payorName = "";
    double registrationFee;
    int medicationUnit;
    double medicationPricePerUnit;
    boolean diagnosticTest;
    double diagnosticTestFee;
    boolean found = false;
    List<Patient> assignedPatients = new ArrayList<>();
    List<Treatment> treatmentOfPatient = new ArrayList<>();
    Patient patient = new Patient();

    while (!found) {
      System.out.print("Please enter the name of the patient: ");
      payorName = console.nextLine();

      // To check whether this patient is in the record.
      for (Patient o : patients) {
          if (o.getName().equals(payorName)) {
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

    if (!treatmentOfPatient.isEmpty()){}

    return invoceList;
  }
}
