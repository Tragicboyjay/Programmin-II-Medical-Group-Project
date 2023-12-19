/**
 * Doctor class extends Person class
 * 
 * @Version 1.0
 * @Since 2023-12-18
 * @Authour FSD Team 3 
*/

package Classes;

import java.time.LocalDate;

public class Doctor extends Person {
    private String speciality;
    private LocalDate dateOfEmployment;

    Doctor(String aFirstName, String aLastName, LocalDate aDOB, String aSpeciality, LocalDate aDateOfEmployment) {
        super(aFirstName, aLastName, aDOB);
        setDateOfEmployment(aDateOfEmployment);
        setSpeciality(aSpeciality);

    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public String getSpeciality() {
        return speciality;
    }

    @Override
    public String toString() {
        // not sure how y'all want to format this -JM
        return " ";
    }
}
