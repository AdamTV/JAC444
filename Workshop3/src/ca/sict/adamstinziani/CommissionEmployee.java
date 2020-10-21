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
public class CommissionEmployee extends Employee {
    private double grossSales;
    private double commissionRate;

    // constructors use setters and super
    public CommissionEmployee(String first, String last, String ssn, double grossSales, double commissionRate) {
        super(first, last, ssn);
        setGrossSales(grossSales);
        setCommissionRate(commissionRate);
    }

    // get gross sales
    public double getGrossSales() {
        return grossSales;
    }

    // set gross sales with validation
    public void setGrossSales(double grossSales) {
        if (grossSales <= 0.0) {
            throw new IllegalArgumentException("gross sales must be greater than 0.0.");
        }
        this.grossSales = grossSales;
    }

    // get commission rate
    public double getCommissionRate() {
        return commissionRate;
    }

    // set commission rate with validation
    public void setCommissionRate(double commissionRate) {
        if (commissionRate < 0.0 || commissionRate > 1.0) {
            throw new IllegalArgumentException("commission rate must be between 0.0 and 1.0.");
        }
        this.commissionRate = commissionRate;
    }

    @Override
    public double getPayableAmount() {
        return getGrossSales() * getCommissionRate();
    }

    @Override
    public String toString() {
        return String.format("%s, grossSales:%s, commissionRate:%s", super.toString(), getGrossSales(), getCommissionRate());
    }
}
