package Classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Patient extends Person {
  private int patientId;
  private String gender;
  private String medicalRecords;
  private String currentSymptoms;
  private String attendingDoctor;
  private String insuranceCompany;
  private List<Treatment> treatments; 

  public Patient() {
    super();
  }

  public Patient (String name, LocalDate birthDate, String address, String phone, int patientId, String gender, String medicalRecords, String currentSymptoms, String insuranceCompany) {
    super(name, birthDate, address, phone);
    this.patientId = patientId;
    this. gender =  gender;
    this.medicalRecords = medicalRecords;
    this.currentSymptoms = currentSymptoms;
    this.attendingDoctor = "";
    this.insuranceCompany = insuranceCompany;
    this.treatments = new ArrayList<>();
  }

  public int getPatientId() {
    return patientId;
  }

  public void setPatientId(int patientId) {
    this.patientId = patientId;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getMedicalRecords() {
    return medicalRecords;
  }

  public void setMedicalRecords(String medicalRecords) {
    this.medicalRecords = medicalRecords;
  }

  public String getCurrentSymptoms() {
    return currentSymptoms;
  }

  public void setCurrentSymptoms(String currentSymptoms) {
    this.currentSymptoms = currentSymptoms;
  }

  public String getAttendingDoctor() {
    return attendingDoctor;
  }

  public void setAttendingDoctor(String attendingDoctor) {
    this.attendingDoctor = attendingDoctor;
  }

  public String getInsuranceCompany() {
    return insuranceCompany;
  }

  public void setInsuranceCompany(String insuranceCompany) {
    this.insuranceCompany = insuranceCompany;
  }


  public List<Treatment> getTreatments() {
    return treatments;
  }

  public void setTreatments(List<Treatment> treatments) {
    this.treatments = treatments;
  }

  @Override
  public String toString() {
      List<Treatment> treatments = getTreatments();
  
      // Extract treatment numbers
      List<String> treatmentIds = treatments.stream()
              .map(Treatment::getTreatmentId)
              .map(Object::toString)
              .collect(Collectors.toList());
  
      return String.format("%s: %d%n%s%s: %s%n%s: %s%n%s: %s%n%s: %s%n%s: %s%n",
              "PatientId", getPatientId(),
              super.toString(),
              "Gender", getGender(),
              "MedicalRecords", getMedicalRecords(),
              "CurrentSymptoms", getCurrentSymptoms(),
              "AttendingDoctor", getAttendingDoctor(),
              "InsuranceCompany", getInsuranceCompany(),
              "treatments", String.join(", ", treatmentIds));
  }
}
