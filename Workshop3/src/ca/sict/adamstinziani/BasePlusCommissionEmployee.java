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

// concrete class to extend commission employee
public class BasePlusCommissionEmployee extends CommissionEmployee {
    double baseSalary;

    // constructors use setters and super
    public BasePlusCommissionEmployee(String first, String last, String ssn, double grossSales, double commissionRate, double baseSalary) {
        super(first, last, ssn, grossSales, commissionRate);
        setBaseSalary(baseSalary);
    }

    // get base salary
    public double getBaseSalary() {
        return baseSalary;
    }

    // set base salary with validation
    public void setBaseSalary(double baseSalary) {
        if (baseSalary <= 0.0) {
            throw new IllegalArgumentException("base salary must be greater than 0.");
        }
        this.baseSalary = baseSalary;
    }

    @Override
    public String toString() {
        return String.format("%s, baseSalary:%s", super.toString(), getBaseSalary());
    }
}
