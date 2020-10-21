/*
 * Workshop # 3
 * Course: JAC444 - Fall 2020
 * Last Name: Stinziani
 * First Name: Adam
 * ID: 124521188
 * Section: NDD
 * This assignment represents my own work in accordance with Seneca Academic Policy.
 * Signature: Adam Stinziani
 * Date: 2020-10-07
 */

package ca.sict.adamstinziani;

// abstract employee class to represent an employee
public abstract class Employee implements PayableInterface {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;

    // constructors use setters and super
    public Employee(String firstName, String lastName, String socialSecurityNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setSocialSecurityNumber(socialSecurityNumber);
    }

    // get first name
    public String getFirstName() {
        return firstName;
    }

    // set first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // get last name
    public String getLastName() {
        return lastName;
    }

    // set last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // get SSN
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    // set SSN
    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    // abstract interface for getting payable amount
    @Override
    public abstract double getPayableAmount();

    // concrete override of toString
    @Override
    public String toString() {
        return String.format("Employee firstName:%s, lastName:%s, socialSecurityNumber:%s", getFirstName(), getLastName(), getSocialSecurityNumber());
    }
}
