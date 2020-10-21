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

// contains logic to calculate and store income tax
public class IncomeTax {
    // filing statuses
    static final int SINGLE_FILER = 0, MARRIED_JOINTLY_OR_QUALIFYING_WIDOW_WIDOWER = 1, MARRIED_SEPARATELY = 2, HEAD_OF_HOUSEHOLD = 3, NUMBER_OF_TAX_BRACKETS = 4;
    // current filing status
    static int filingStatus;
    // intervals of income and corresponding tax payments per bracket
    static double[][] intervals;
    // percentages at which tax is calculated for each interval
    static double[] rates;
    // the maximum income allowed per tax bracket
    static double[] taxBrackets;
    // current taxable income
    static double taxableIncome;
    // current year of filing
    static int yearOfFiling;

    // default constructor for this class, initialize values from user input
    IncomeTax() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("0 - single filer\n" +
                    "1 - married jointly or qualifying widow(er)\n" +
                    "2 - married separately\n" +
                    "3 - head of household\n\n" +
                    "Enter your filing status: ");
            try{
                setFilingStatus(sc.nextInt());
            }catch(Exception e){
                sc.nextLine();
                setFilingStatus(-1);
            }
            if (filingStatus == -1) {
                System.out.println("Invalid filing status entered. Please try again.");
            }
        } while (getFilingStatus() == -1);
        do {
            System.out.print("Enter the taxable income (greater than 0): $");
            try{
                setTaxableIncome(sc.nextDouble());
            }catch(Exception e){
                sc.nextLine();
                setTaxableIncome(-1);
            }
            if (getTaxableIncome() < 0) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (getTaxableIncome() < 0);
    }

    // overloaded constructor using calculated values
    private IncomeTax(int filingStatus, double[][] intervals, double[] rates, double taxableIncome) {
        setFilingStatus(filingStatus);
        setIntervals(intervals);
        setRates(rates);
        setTaxableIncome(taxableIncome);
    }

    // getter for year of filing
    private static int getYearOfFiling(){
        return yearOfFiling;
    }

    // setter for year of filing
    private static void setYearOfFiling(int yearOfFilingArg){
        if(yearOfFilingArg == 2001 || yearOfFilingArg == 2009){
            yearOfFiling = yearOfFilingArg;
        }
    }

    // getter for filing status
    private static int getFilingStatus() {
        return filingStatus;
    }

    // setter for filing status
    private static void setFilingStatus(int filingStatusArg) {
        if (filingStatusArg < 0 || filingStatusArg > 3) {
            filingStatus = -1;
        } else {
            filingStatus = filingStatusArg;
        }
    }

    // getter for taxable income
    private static double getTaxableIncome() {
        return taxableIncome;
    }

    // setter for taxable income
    private static void setTaxableIncome(double taxableIncomeArg) {
        taxableIncome = taxableIncomeArg;
    }

    // getter for intervals
    private static double[][] getIntervals() {
        return intervals;
    }

    // setter for intervals
    private static void setIntervals(double[][] intervalsArg) {
        intervals = intervalsArg;
    }

    // getter for rates
    private static double[] getRates() {
        return rates;
    }

    // setter for rates
    private static void setRates(double[] ratesArg) {
        rates = ratesArg;
    }

    // getter for tax brackets
    private static double[] getTaxBrackets() {
        return taxBrackets;
    }

    // setter for tax brackets
    private static void setTaxBrackets() {
        switch(getYearOfFiling()){
            case 2001:
                switch (getFilingStatus()) {
                    case SINGLE_FILER:
                        taxBrackets = new double[]{27050, 65550, 136750, 297350, 297351};
                        break;
                    case MARRIED_JOINTLY_OR_QUALIFYING_WIDOW_WIDOWER:
                        taxBrackets = new double[]{45200, 109250, 166500, 297350, 297351};
                        break;
                    case MARRIED_SEPARATELY:
                        taxBrackets = new double[]{22600, 54625, 83250, 148675, 148676};
                        break;
                    case HEAD_OF_HOUSEHOLD:
                        taxBrackets = new double[]{36250, 93650, 151650, 297350, 297351};
                        break;
                }
                break;
            case 2009:
                switch (getFilingStatus()) {
                    case SINGLE_FILER:
                        taxBrackets = new double[]{8350, 33950, 82250, 171550, 372950, 372951};
                        break;
                    case MARRIED_JOINTLY_OR_QUALIFYING_WIDOW_WIDOWER:
                        taxBrackets = new double[]{16700, 67900, 137050, 208850, 372950, 372951};
                        break;
                    case MARRIED_SEPARATELY:
                        taxBrackets = new double[]{8350, 33950, 68525, 104425, 186475, 186476};
                        break;
                    case HEAD_OF_HOUSEHOLD:
                        taxBrackets = new double[]{11950, 45500, 117450, 190200, 372950, 372951};
                        break;
                }
                break;
        }
    }

    // gets income tax for 2009
    double getIncomeTax() {
        setYearOfFiling(2009);
        setRates(new double[]{0.10, 0.15, 0.25, 0.28, 0.33, 0.35});
        setTaxBrackets();

        double incomeTax = 0;
        for (int i = 0; i < getTaxBrackets().length; i++) {
            if (getTaxableIncome() >= getTaxBrackets()[i]) {
                if (i == 0) {
                    incomeTax += getTaxBrackets()[i] * getRates()[i];
                } else {
                    if (i == getTaxBrackets().length - 1) {
                        incomeTax += getTaxableIncome() * getRates()[i];
                    }
                    incomeTax += (getTaxBrackets()[i] - getTaxBrackets()[i - 1]) * getRates()[i];
                }
                setTaxableIncome(getTaxableIncome() - getTaxBrackets()[i]);
            } else {
                incomeTax += getTaxableIncome() * getRates()[i];
                break;
            }
        }
        return incomeTax;
    }

    // prints tax tables for 2001 and 2009 for a range of taxable incomes and filing statuses
    static void printTaxTables() {
        Scanner sc = new Scanner(System.in);
        int amountFrom = -1;
        int amountTo = -1;
        do{
            try{
                System.out.print("Enter the amount From (greater than 0): $");
                amountFrom = sc.nextInt();
                System.out.print("Enter the amount To (greater than 0): $");
                amountTo = sc.nextInt();
                if(amountFrom < 0 || amountTo < 0) throw new Exception();
            }catch(Exception e){
                sc.nextLine();
                System.out.println("Invalid input. Please try again.");
            }
        } while(amountTo < 0 || amountFrom < 0);
        System.out.println("2001 tax tables for taxable income from $" + amountFrom + " to $" + amountTo);
        System.out.println("---------------------------------------------------------------------------------");
        get2001TaxTable(amountFrom, amountTo);
        System.out.println('\n');
        System.out.println("2009 tax tables for taxable income from $" + amountFrom + " to $" + amountTo);
        System.out.println("---------------------------------------------------------------------------------");
        get2009TaxTable(amountFrom, amountTo);
        System.out.println('\n');
    }

    // prints tax table for 2001
    private static void get2001TaxTable(int amountFrom, int amountTo) {
        setYearOfFiling(2001);
        setRates(new double[]{0.15, 0.275, 0.305, 0.355, 0.391});
        getIncomeTaxTable(amountTo, amountFrom);
        IncomeTax tax = getIncomeTaxTable(amountTo, amountFrom);
        tax.printTaxTable();
    }

    // prints tax table for 2009
    private static void get2009TaxTable(int amountFrom, int amountTo) {
        setYearOfFiling(2009);
        setRates(new double[]{0.10, 0.15, 0.25, 0.28, 0.33, 0.35});
        IncomeTax tax = getIncomeTaxTable(amountTo, amountFrom);
        tax.printTaxTable();
    }

    // calculates tax brackets for a range of taxable incomes and filing statuses
    private static IncomeTax getIncomeTaxTable(int amountTo, int amountFrom) {
        int rowsOfIntervals = ((amountTo - amountFrom) / 1000) + 1;
        setIntervals(new double[rowsOfIntervals][NUMBER_OF_TAX_BRACKETS + 1]);
        // for each interval (of 1000 starting at amountFrom ending at amountTo)
        for (int i = 0; i < intervals.length; i++) {
            double taxableIncomeForThisInterval = amountTo - (rowsOfIntervals - (i + 1)) * 1000;
            getIntervals()[i][0] = taxableIncomeForThisInterval;
            for (int j = 0; j < NUMBER_OF_TAX_BRACKETS; j++) {
                setTaxableIncome(taxableIncomeForThisInterval);
                setFilingStatus(j);
                setTaxBrackets();
                double incomeTax = 0;
                int numberOfBracketsToCalculate = 1;
                for (int bracket = 0; bracket < NUMBER_OF_TAX_BRACKETS; bracket++) {
                    if (getTaxableIncome() >= getTaxBrackets()[bracket]) numberOfBracketsToCalculate++;
                }
                for (int z = 0; z < numberOfBracketsToCalculate; z++) {
                    if (z == 0) {
                        incomeTax += getTaxBrackets()[z] * getRates()[z];
                    } else {
                        if (getTaxableIncome() >= getTaxBrackets()[z]) {
                            incomeTax += (getTaxBrackets()[z] - getTaxBrackets()[z - 1]) * getRates()[z];
                        } else {
                            incomeTax += (getTaxableIncome() - getTaxBrackets()[z - 1]) * getRates()[z];
                        }
                    }
                }
                getIntervals()[i][j + 1] = incomeTax;
            }
        }
        return new IncomeTax(-1, intervals, rates, taxableIncome);
    }

    // prints current tax table
    private void printTaxTable(){
        System.out.println("|_______________|_______________|_______________|_______________|_______________|\n" +
                "|               |               | Married Joint |               |               |\n" +
                "|    Taxable    |     Single    | or Qualifying |   Married     |    Head of    |\n" +
                "|    Income     |               | Widow(er)     |   Separate    |    a House    |\n" +
                "|_______________|_______________|_______________|_______________|_______________|");
        for (double[] interval : intervals) {
            System.out.println("|     " + String.format("%.0f", interval[0]) + "     |   " + String.format("%.2f", interval[1]) +
                    (String.format("%.2f", interval[1]).length() == 7 ? "     |   " : "    |   ") + String.format("%.2f", interval[2]) +
                    (String.format("%.2f", interval[2]).length() == 7 ? "     |   " : "    |   ") + String.format("%.2f", interval[3]) +
                    (String.format("%.2f", interval[3]).length() == 7 ? "     |   " : "    |   ") + String.format("%.2f", interval[4]) +
                    (String.format("%.2f", interval[4]).length() == 7 ? "     |" : "    |"));
            System.out.println("|_______________|_______________|_______________|_______________|_______________|");
        }
    }
}
