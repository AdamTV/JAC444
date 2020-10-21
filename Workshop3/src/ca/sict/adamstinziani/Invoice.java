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

// concrete class to represent an invoice
public class Invoice implements PayableInterface {
    String part;
    String description;
    int count;
    double price;

    // constructors use getters
    public Invoice(String part, String description, int count, double price) {
        setPart(part);
        setDescription(description);
        setCount(count);
        setPrice(price);
    }

    // get part
    public String getPart() {
        return part;
    }

    // set part
    public void setPart(String part) {
        this.part = part;
    }

    // get description
    public String getDescription() {
        return description;
    }

    // set description
    public void setDescription(String description) {
        this.description = description;
    }

    // get count
    public int getCount() {
        return count;
    }

    // set count
    public void setCount(int count) {
        this.count = count;
    }

    // get price
    public double getPrice() {
        return price;
    }

    // set price
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getPayableAmount() {
        return 0;
    }

    public String toString() {
        return "";
    }
}
