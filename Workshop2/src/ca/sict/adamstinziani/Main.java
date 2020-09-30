/*
 * Workshop # 2
 * Course: JAC444 - Fall 2020
 * Last Name: Stinziani
 * First Name: Adam
 * ID: 124521188
 * Section: NDD
 * This assignment represents my own work in accordance with Seneca Academic Policy.
 * Signature: Adam Stinziani
 * Date: 2020-09-29
 */

package ca.sict.adamstinziani;

import java.util.Scanner;

// contains logic to complete this workshop.
public class Main {

    // main entry point of this workshop.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("MENU OPTIONS\n" +
                    "2 - PRINT TAX TABLES FOR TAXABLE INCOMES\n" +
                    "1 - COMPUTE PERSONAL INCOME TAX\n" +
                    "0 - EXIT\n\n" +
                    "Enter your selection: ");
            int selection = sc.nextInt();
            switch (selection) {
                case 0:
                    return;
                case 1:
                    double incomeTax = IncomeTax.getIncomeTax();
                    System.out.println("Income Tax is: $" + String.format("%.2f", incomeTax) + '\n');
                    break;
                case 2:
                    IncomeTax.printTaxTables();
                    break;
                default:
                    System.out.println("Invalid selection");
            }
        }
    }
}
