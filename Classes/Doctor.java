/**
 * Doctor class extends Person class
 * 
 * @Version 1.0
 * @Since 2023-12-18
 * @Authour FSD Team 3 
*/

package Classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Doctor extends Person {
  private int doctorId;
  private String specialty;
  private int yearsInPractice;
  private List<Patient> assignedPatients;

  public Doctor() {
    super();
  }

  public Doctor(String name, LocalDate birthDate, String address, String phone, int doctorId, String specialty, int yearsInPractice) {
    super(name, birthDate, address, phone);
    this.specialty = specialty;
    this.doctorId = doctorId;
    this.yearsInPractice = yearsInPractice;
    this.assignedPatients = new ArrayList<>();
  }

  public String getSpecialty() {
    return specialty;
  }

  public void setSpecialty(String specialty) {
    this.specialty = specialty;
  }

  public int getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(int doctorId) {
    this.doctorId = doctorId;
  }

  public int getYearsInPractice() {
    return yearsInPractice;
  }

  public void setYearsInPractice(int yearsInPractice) {
    this.yearsInPractice = yearsInPractice;
  }

  public List<Patient> getAssignedPatients() {
    return assignedPatients;
  }

  public void setAssignedPatients(List<Patient> assignedPatients) {
    this.assignedPatients = assignedPatients;
  }

  @Override
  public String toString() {
      List<Patient> assignedPatients = getAssignedPatients();
      
      // Extract patient names
      List<String> patientNames = assignedPatients.stream()
              .map(Patient::getName)
              .collect(Collectors.toList());
              
      return String.format("%s: %d %n%s%s: %s %n%s: %d %n%s: %s %n", 
            "DoctorId", getDoctorId(),
            super.toString(),
            "Specialty", getSpecialty(),
            "YearsInPractice", getYearsInPractice(),
            "AssignedPatients", String.join(", ", patientNames));
  }
}
