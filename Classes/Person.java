/**
 * Abstract Person class
 * 
 * @Version 1.0
 * @Since 2023-12-18
 * @Authour FSD Team 3 
*/

package Classes;

import java.time.LocalDate;

public abstract class Person {
    private String firstName;
    private String lastName;
    private LocalDate dOB;

    Person(String aFirstName, String aLastName, LocalDate aDOB){
        setFirstName(aFirstName);
        setLastName(aLastName);
        setDOB(aDOB);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDOB(LocalDate dOB) {
        // Throw error if date is passes current date.

        this.dOB = dOB;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDOB() {
        return dOB;
    }

    @Override
    public String toString() {
        return "First Name: " + this.getFirstName() + ", Last Name: " + this.getLastName() + ", DOB: " + this.getDOB();
    }
} 