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
public class Workshop {

    // main entry point of this workshop.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("MENU OPTIONS\n" +
                    "2 - PRINT TAX TABLES FOR TAXABLE INCOMES\n" +
                    "1 - COMPUTE PERSONAL INCOME TAX\n" +
                    "0 - EXIT\n\n" +
                    "Enter your selection: ");
            int selection = -1;
            try{
                selection = sc.nextInt();
            } catch (Exception e) {
                sc.nextLine();
            }
            switch (selection) {
                case 0:
                    return;
                case 1:
                    IncomeTax incomeTax = new IncomeTax();
                    double tax = incomeTax.getIncomeTax();
                    System.out.println("Income Tax is: $" + String.format("%.2f", tax) + '\n');
                    break;
                case 2:
                    IncomeTax.printTaxTables();
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }
}
