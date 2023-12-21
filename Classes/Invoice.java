package Classes;

import java.time.LocalDateTime;

public class Invoice {
  private int invoiceId;
  private String payorName;
  private double registrationFee;
  private int medicationUnit;
  private double medicationPricePerUnit;
  private boolean diagnosticTest;
  private double diagnosticTestFee;
  private double insurancCoverage;
  private double totalAmount;
  private LocalDateTime creationTimestamp;

  public Invoice() {}
  
  public Invoice(int invoiceId, String payorName, double registrationFee, int medicationUnit, double medicationPricePerUnit, boolean diagnosticTest,
      double diagnosticTestFee, double insurancCoverage) {
    setInvoiceId(invoiceId);
    this.payorName = payorName;
    setRegistrationFee(registrationFee);
    setMedicationUnit(medicationUnit);
    setMedicationPricePerUnit(medicationPricePerUnit);
    this.diagnosticTest = diagnosticTest;
    setDiagnosticTestFee(diagnosticTestFee);
    this.insurancCoverage = insurancCoverage;
    setCreationTimestamp();
    setTotalAmount();
  }

  public int getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(int invoiceId) {
    if (invoiceId < 0) {
      throw new IllegalArgumentException("Invoice Id must be greater than zero");
    }
    this.invoiceId = invoiceId;
  }

  public String getPayorName() {
    return payorName;
  }

  public void setPayorName(String payorName) {
    this.payorName = payorName;
  }

  public double getRegistrationFee() {
    return registrationFee;
  }
  public void setRegistrationFee(double registrationFee) {
    if (registrationFee < 0) {
      throw new IllegalArgumentException("RegistrationFee must be greater than zero");
    }
    this.registrationFee = registrationFee;
  }

  public int getMedicationUnit() {
    return medicationUnit;
  }

  public void setMedicationUnit(int medicationUnit) {
    if (medicationUnit < 0) {
      throw new IllegalArgumentException("MedicationUnit must be greater than zero");
    }
    this.medicationUnit = medicationUnit;
  }

  public double getMedicationPricePerUnit() {
    return medicationPricePerUnit;
  }

  public void setMedicationPricePerUnit(double medicationPricePerUnit) {
    if (medicationPricePerUnit < 0) {
      throw new IllegalArgumentException("MedicationPricePerUnit must be greater than zero");
    }
    this.medicationPricePerUnit = medicationPricePerUnit;
  }

  public double getDiagnosticTestFee() {
    return diagnosticTestFee;
  }

  public void setDiagnosticTestFee(double diagnosticTestFee) {
    if (!isDiagnosticTest()) {
      this.diagnosticTestFee = 0;
    }
    this.diagnosticTestFee = diagnosticTestFee;
  }

  public boolean isDiagnosticTest() {
    return diagnosticTest;
  }

  public void setDiagnosticTest(boolean diagnosticTest) {
    this.diagnosticTest = diagnosticTest;
  }

  public double getInsurancCoverage() {
    return insurancCoverage;
  }

  public void setInsurancCoverage(double insurancCoverage) {
    this.insurancCoverage = insurancCoverage;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount() {
    this.totalAmount = (getRegistrationFee() + getMedicationPricePerUnit() * getMedicationUnit() + getDiagnosticTestFee()) * (1 - getInsurancCoverage()/100);
  }

  public LocalDateTime getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp() {
    this.creationTimestamp = LocalDateTime.now();
  }

  @Override
  public String toString() {
      return String.format("%s%d%n %s%s%n %s%,.2f%n %s%d%n %s%,.2f%n %s%,.2f%n %s%,.2f%%%n %s%,.2f%n %s%s%n",
              "InvoiceId: ", getInvoiceId(),
              "Payor: ", getPayorName(),
              "Registration fee: $", getRegistrationFee(),
              "Medication unit: ", getMedicationUnit(),
              "Medication fee: $", getMedicationUnit() * getMedicationPricePerUnit(),
              "Diagnostic test fee: $", getDiagnosticTestFee(),
              "Insuranc Coverage: ", getInsurancCoverage(),
              "Total amount: $", getTotalAmount(),
              "Invoice created on ", getCreationTimestamp());
  }
}
