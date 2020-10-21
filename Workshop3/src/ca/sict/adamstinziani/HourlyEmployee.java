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

// concrete class to implement employee
public class HourlyEmployee extends Employee {
    private double hourlyWage;
    private double hoursWorked;

    // constructors use setters and super
    public HourlyEmployee(String first, String last, String ssn, double hourlyWage, double hoursWorked) {
        super(first, last, ssn);
        setHourlyWage(hourlyWage);
        setHoursWorked(hoursWorked);
    }

    // get hourly wage
    public double getHourlyWage() {
        return hourlyWage;
    }

    // set hourly wage with validation
    public void setHourlyWage(double hourlyWage) {
        if (hourlyWage <= 0.0) {
            throw new IllegalArgumentException("hourly wage must be greater than 0.0.");
        }
        this.hourlyWage = hourlyWage;
    }

    // get hours worked
    public double getHoursWorked() {
        return hoursWorked;
    }

    // set hours worked with validation
    public void setHoursWorked(double hoursWorked) {
        if (hoursWorked < 0.0 || hoursWorked > 168.0) {
            throw new IllegalArgumentException("hours worked must be in between 0.0 and 168.0.");
        }
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double getPayableAmount() {
        return getHourlyWage() * getHoursWorked();
    }

    @Override
    public String toString() {
        return String.format("%s, hourlyWage:%s, hoursWorked:%s", super.toString(), getHourlyWage(), getHoursWorked());
    }
}
