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
public class SalariedEmployee extends Employee {
    double weeklySalary;

    // constructors use getters and super
    public SalariedEmployee(String first, String last, String ssn, double weeklySalary) {
        super(first, last, ssn);
        setWeeklySalary(weeklySalary);
    }

    // get weekly salary
    public double getWeeklySalary() {
        return weeklySalary;
    }

    // set weekly salary with validation
    public void setWeeklySalary(double weeklySalary) {
        if (weeklySalary <= 0) {
            throw new IllegalArgumentException("weekly salary must be greater than 0.");
        }
        this.weeklySalary = weeklySalary;
    }

    @Override
    public double getPayableAmount() {
        return getWeeklySalary();
    }

    @Override
    public String toString() {
        return String.format("%s, weeklySalary:%s", super.toString(), getWeeklySalary());
    }
}
