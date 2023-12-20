/**
 * Abstract Person class
 * 
 * @Version 1.0
 * @Since 2023-12-18
 * @Authour FSD Team 3 
*/

package Classes;

import java.time.LocalDate;
import java.time.Period;

public abstract class Person {
  private String name;
  private LocalDate birthDate;
  private int age;
  private String address;
  private String phone;

  public Person() {}

  public Person(String name, LocalDate birthDate, String address, String phone) {
      this.name = name;
      setBirthDate(birthDate);
      this.address = address;
      this.phone = phone;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
    Period period = Period.between(this.birthDate, LocalDate.now());
    this.age = period.getYears();
  }

  public int getAge() {
    return age;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override 
  public String toString() {
      return String.format("%s %nage: %s%nphone number: %s%naddress: %s%n"
      , getName(), getAge(), getPhone(), getAddress());
  }
}