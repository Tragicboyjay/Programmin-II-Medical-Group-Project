package Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Treatment {
  private int treatmentId;
  private String patient;
  private String doctor;
  private String nurse;
  private String treatmentDescription;
  private LocalDate startDate;
  private LocalDate endDate;
  private int medicationUnit;
  private boolean diagnosticTest;
  private LocalDateTime creationTimestamp;

  public Treatment(int treatmentId, String patient, String doctor, String nurse, String treatmentDescription,
      LocalDate startDate, LocalDate endDate, boolean diagnosticTest) {
    setTreatmentId(treatmentId);
    this.patient = patient;
    this.doctor = doctor;
    this.nurse = nurse;
    this.treatmentDescription = treatmentDescription;
    this.startDate = startDate;
    this.endDate = endDate;
    setMedicationUnit();
    this.diagnosticTest = diagnosticTest;
    setCreationTimestamp();
  }

  public int getTreatmentId() {
    return treatmentId;
  }

  public void setTreatmentId(int treatmentId) {
    if (treatmentId < 0) {
      throw new IllegalArgumentException("Invoice Id must be greater than zero");
    }
    this.treatmentId = treatmentId;
  }

  public String getPatient() {
    return patient;
  }

  public void setPatient(String patient) {
    this.patient = patient;
  }

  public String getDoctor() {
    return doctor;
  }

  public void setDoctor(String doctor) {
    this.doctor = doctor;
  }

  public String getNurse() {
    return nurse;
  }

  public void setNurse(String nurse) {
    this.nurse = nurse;
  }

  public String getTreatmentDescription() {
    return treatmentDescription;
  }

  public void setTreatmentDescription(String treatmentDescription) {
    this.treatmentDescription = treatmentDescription;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public int getMedicationUnit() {
    return medicationUnit;
  }

  public void setMedicationUnit() {
    Period period = Period.between(getStartDate(), getEndDate());
    this.medicationUnit = period.getDays();
  }

  public boolean isDiagnosticTest() {
    return diagnosticTest;
  }

  public void setDiagnosticTest(boolean diagnosticTest) {
    this.diagnosticTest = diagnosticTest;
  }

  public LocalDateTime getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp() {
    this.creationTimestamp = LocalDateTime.now();
  }
}
