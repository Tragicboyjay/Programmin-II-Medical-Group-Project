package Services;

import Classes.Invoice;
import Classes.Patient;
import Classes.Treatment;
import static Type.InsuranceCompany.COMPANY_A;
import static Type.InsuranceCompany.COMPANY_B;
import static Type.InsuranceCompany.COMPANY_C;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InvoiveService {
  static Scanner console = new Scanner(System.in);
  public static List<Invoice> creatInvoice(List<Invoice> invoceList, List<Patient> patients) {
    int invoiceId = invoceList.size();
    String payorName = "";
    double registrationFee;
    int medicationUnit;
    double medicationPricePerUnit;
    double insurancCoverage;
    boolean diagnosticTest;
    double diagnosticTestFee;
    boolean found = false;
    List<Treatment> treatmentOfPatient = new ArrayList<>();
    Patient patient = new Patient();
    String print = "";

    // To check whether this patient is in the record.
    while (!found) {
      System.out.print("Please enter the name of the patient: ");
      payorName = console.nextLine();

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

    // Check whethe there is treatment record under this patient
    if (!treatmentOfPatient.isEmpty()){

      System.out.println("The number of treatment needs to be paid: " + treatmentOfPatient.size());
      System.out.print("Generating invoice, ");

      System.out.print("please enter registration fee: ");
      registrationFee = console.nextDouble();

      System.out.print("Please enter medication price per unit: ");
      medicationPricePerUnit = console.nextDouble();

      System.out.print("Please enter diagnostic test fee: ");
      diagnosticTestFee = console.nextDouble();

      // Get insurance coverage rate for this patient
      if(patient.getInsuranceCompany().equalsIgnoreCase(COMPANY_A.getCompanyName())) {
        insurancCoverage = COMPANY_A.getCoverRate();
      } else if (patient.getInsuranceCompany().equalsIgnoreCase(COMPANY_B.getCompanyName())) {
        insurancCoverage = COMPANY_B.getCoverRate();
      } else if (patient.getInsuranceCompany().equalsIgnoreCase(COMPANY_C.getCompanyName())) {
        insurancCoverage = COMPANY_C.getCoverRate();
      } else {
        insurancCoverage = 0;
      }

      for (Treatment treatment : treatmentOfPatient){

        medicationUnit = treatment.getMedicationUnit();
        diagnosticTest = treatment.isDiagnosticTest();
        invoiceId++;

        Invoice invoice = new Invoice(invoiceId, payorName, registrationFee, medicationUnit, medicationPricePerUnit, diagnosticTest, diagnosticTestFee, insurancCoverage);

        invoceList.add(invoice);
        System.out.println("A new invoice No." + invoiceId + " for " + payorName + " is registered.");
      }
      System.out.println("Do you want to print this invoice out? (Y for yes, other cancel)");
      print = console.next();
      if(print.equalsIgnoreCase("Y")){
        printInvoice(invoceList);
      }
    }
    System.out.println("No treatment of this patient.");
    System.out.println();
    return invoceList;
  }

  public static void printInvoice(List<Invoice> invoiceList){
    for (Invoice invoice : invoiceList) {
      System.out.println(invoice);
      System.out.println();
    }
  }
}
