package Classes;

import java.time.LocalDateTime;

public class Appointment {
  private int appointmentId;
  private String patientName;
  private String doctorName;
  private String phone;
  private LocalDateTime date;
  private LocalDateTime creationTimestamp;

  public Appointment() {}

  public Appointment(int appointmentId, String patientName, String doctorName, String phone, LocalDateTime date) {
    this.appointmentId = appointmentId;
    this.patientName = patientName;
    this.doctorName = doctorName;
    this.phone = phone;
    this.date = date;
    this.creationTimestamp = LocalDateTime.now();
  }

  public int getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(int appointmentId) {
    this.appointmentId = appointmentId;
  }
  
  public String getPatientName() {
    return patientName;
  }

  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  public String getDoctorName() {
    return doctorName;
  }
  public void setDoctorName(String doctorName) {
    this.doctorName = doctorName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public LocalDateTime getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp() {
    this.creationTimestamp = LocalDateTime.now();
  }
}
