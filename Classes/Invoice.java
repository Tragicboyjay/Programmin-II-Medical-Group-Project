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
  private double totalAmount;
  private LocalDateTime creationTimestamp;
  
  public Invoice(int invoiceId, String payorName, double registrationFee, int medicationUnit, double medicationPricePerUnit,
      double diagnosticTestFee, LocalDateTime creationTimestamp) {
    setInvoiceId(invoiceId);
    this.payorName = payorName;
    setRegistrationFee(registrationFee);
    setMedicationUnit(medicationUnit);
    setMedicationFee(medicationPricePerUnit);
    setDiagnosticTestFee(diagnosticTestFee);
    setCreationTimestamp();
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

  public void setMedicationFee(double medicationPricePerUnit) {
    if (medicationPricePerUnit < 0) {
      throw new IllegalArgumentException("MedicationPricePerUnit must be greater than zero");
    }
    this.medicationPricePerUnit = medicationPricePerUnit;
  }

  public double getDiagnosticTestFee() {
    return diagnosticTestFee;
  }

  public void setDiagnosticTestFee(double diagnosticTestFee) {
    if (diagnosticTestFee < 0) {
      throw new IllegalArgumentException("DiagnosticTestFee must be greater than zero");
    }
    this.diagnosticTestFee = diagnosticTestFee;
  }

  public boolean isDiagnosticTest() {
    return diagnosticTest;
  }

  public void setDiagnosticTest(boolean diagnosticTest) {
    this.diagnosticTest = diagnosticTest;
  }

  public double getTotalAmout() {
    if (!isDiagnosticTest()) {
      return this.totalAmount = getRegistrationFee() + getMedicationPricePerUnit() * getMedicationUnit();
    }
    return this.totalAmount = getRegistrationFee() + getMedicationPricePerUnit() * getMedicationUnit() + getDiagnosticTestFee();
  }

  public LocalDateTime getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp() {
    this.creationTimestamp = LocalDateTime.now();
  }
}
