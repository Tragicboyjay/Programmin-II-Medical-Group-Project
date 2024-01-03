package Classes;

import java.time.LocalDate;

public class Nurse extends Person {
  private int nurseId;
  private int yearsInPractice;
  private String skill;

  public Nurse() {
    super();
  }

  public Nurse(String name, LocalDate birthDate, String address, String phone, int nurseId, int yearsInPractice, String skill) {
    super(name, birthDate, address, phone);
    this.nurseId = nurseId;
    this.yearsInPractice = yearsInPractice;
    this.skill = skill;
  }

  public int getNurseId() {
    return nurseId;
  }

  public void setNurseId(int nurseId) {
    this.nurseId = nurseId;
  }

  public int getYearsInPractice() {
    return yearsInPractice;
  }

  public void setYearsInPractice(int yearsInPractice) {
    this.yearsInPractice = yearsInPractice;
  }

  public String getSkill() {
    return skill;
  }

  public void setSkill(String skill) {
    this.skill = skill;
  }

  @Override
  public String toString() {
      return String.format("%s: %d %n%s%s: %d %n%s: %s %n", 
            "NurseId", getNurseId(),
            super.toString(),
            "YearsInPractice", getYearsInPractice(),
            "Skill", getSkill());
  }
}
