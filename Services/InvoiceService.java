package Services;

import Classes.Invoice;
import Classes.Patient;
import Classes.Treatment;
import static Type.InsuranceCompany.COMPANY_A;
import static Type.InsuranceCompany.COMPANY_B;
import static Type.InsuranceCompany.COMPANY_C;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InvoiveService {
  static Scanner console = new Scanner(System.in);
  public static void creatInvoice(List<Invoice> invoceList, List<Patient> patients) throws FileNotFoundException {
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
          if (o.getName().equalsIgnoreCase(payorName)) {
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

    // Check whether there is treatment record under this patient
    if (treatmentOfPatient.isEmpty()){
      
      System.out.println("No treatment of this patient.");
      System.out.println();

    } else {

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
        printInvoiceOfPerson(invoceList, payorName);
      }
    }
  }

  // Method to print the invoice out both on screen and export as txt file
  public static void printInvoice(List<Invoice> invoiceList) throws FileNotFoundException {
    PrintWriter outFile;
    String fileName = "Invoice.txt";
    outFile = new PrintWriter(fileName);
    for (Invoice invoice : invoiceList) {
      outFile.println(invoice);
      outFile.println();
      System.out.println(invoice);
      System.out.println();
    }
    System.out.println("Invoice is exported as " + fileName);
    outFile.close();
  }

  public static void printInvoiceOfPerson(List<Invoice> invoiceList, String payorName) throws FileNotFoundException {
    PrintWriter outFile;
    String fileName = "Invoice_" + payorName + ".txt";
    outFile = new PrintWriter(fileName);
    for (Invoice invoice : invoiceList) {
      if(payorName.equalsIgnoreCase(invoice.getPayorName())){
        outFile.println(invoice);
        outFile.println();
        System.out.println(invoice);
        System.out.println();
      }
    }
    System.out.println("Invoice is exported as " + fileName);
    outFile.close();
  }
}
