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

public class IncomeTax {
    static final int SINGLE_FILER = 0, MARRIED_JOINTLY_OR_QUALIFYING_WIDOW_WIDOWER = 1, MARRIED_SEPARATELY = 2, HEAD_OF_HOUSEHOLD = 3, NUMBER_OF_TAX_BRACKETS = 4;
    static int filingStatus;
    static double[][] intervals;
    static double[] rates;
    static double taxableIncome;

    IncomeTax() {
        filingStatus = -1;
    }

    IncomeTax(int filingStatusArg, double[][] intervalsArg, double[] ratesArg, double taxableIncomeArg) {
        filingStatus = filingStatusArg;
        intervals = intervalsArg;
        rates = ratesArg;
        taxableIncome = taxableIncomeArg;
    }

    static int getFilingStatus() {
        return filingStatus;
    }

    static void setFilingStatus(int filingStatusArg) {
        if (filingStatusArg < 0 || filingStatusArg > 3) {
            filingStatus = -1;
        } else {
            filingStatus = filingStatusArg;
        }
    }

    static double getTaxableIncome() {
        return taxableIncome;
    }

    static void setTaxableIncome(double taxableIncomeArg) {
        taxableIncome = taxableIncomeArg;
    }

    static double[][] getIntervals() {
        return intervals;
    }

    static void setIntervals(double[][] intervalsArg) {
        intervals = intervalsArg;
    }

    static double[] getRates() {
        return rates;
    }

    static void setRates(double[] ratesArg) {
        rates = ratesArg;
    }

    static double getIncomeTax() {
        setTaxVariables();
        double[] taxBrackets = new double[]{};
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
        double incomeTax = 0;
        for(int i = 0; i < taxBrackets.length; i++){
            if(getTaxableIncome() >= taxBrackets[i]){
                if(i == 0){
                    incomeTax += taxBrackets[i] * getRates()[i];
                }else{
                    if(i == taxBrackets.length - 1) {
                        incomeTax += getTaxableIncome() * getRates()[i];
                    }
                    incomeTax += (taxBrackets[i] - taxBrackets[i - 1]) * getRates()[i];
                }
                setTaxableIncome(getTaxableIncome() - taxBrackets[i]);
            }else{
                incomeTax += getTaxableIncome() * getRates()[i];
                break;
            }
        }

        return incomeTax;
    }

    private static void setTaxVariables() {
        Scanner sc = new Scanner(System.in);
        System.out.print("0 - single filer\n" +
                "1 - married jointly or qualifying widow(er)\n" +
                "2 - married separately\n" +
                "3 - head of household\n\n" +
                "Enter your filing status: ");
        setFilingStatus(sc.nextInt());
        if (filingStatus == -1) {
            System.out.println("Invalid filing status entered. Please try again.");
            setTaxVariables();
        }
        do {
            System.out.print("Enter the taxable income: $");
            setTaxableIncome(sc.nextDouble());
            if (getTaxableIncome() < 0) {
                System.out.println("Cannot calculate tax on negative number. Please try again.");
            }
        } while (getTaxableIncome() < 0);
        setRates(new double[]{0.10, 0.15, 0.25, 0.28, 0.33, 0.35});
    }

    static void printTaxTables() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the amount From: $");
        int amountFrom = sc.nextInt();
        System.out.print("Enter the amount To: $");
        int amountTo = sc.nextInt();
        System.out.println("2001 tax tables for taxable income from $" + amountFrom + " to $" + amountTo);
        System.out.println("---------------------------------------------------------------------------------");
        get2001TaxTable(amountFrom, amountTo);
        System.out.println("2009 tax tables for taxable income from $" + amountFrom + " to $" + amountTo);
        System.out.println("---------------------------------------------------------------------------------");
        get2009TaxTable(amountFrom, amountTo);
    }

    private static void get2001TaxTable(int amountFrom, int amountTo) {
        setRates(new double[]{0.15, 0.275, 0.305, 0.355, 0.391});
        int rowsOfIntervals = ((amountTo - amountFrom) / 1000) + 1;
        setIntervals(new double[rowsOfIntervals][NUMBER_OF_TAX_BRACKETS + 1]);
        // for each interval (of 1000 starting at amountFrom ending at amountTo)
        for(int i = 0; i < intervals.length; i++){
            double taxableIncomeForThisInterval = amountTo - (rowsOfIntervals - (i + 1)) * 1000;
            getIntervals()[i][0] = taxableIncomeForThisInterval;
            for(int j = 0; j < NUMBER_OF_TAX_BRACKETS; j++){
                setTaxableIncome(taxableIncomeForThisInterval);
                setFilingStatus(j);
                double[] taxBrackets = new double[]{};
                double incomeTax = 0;
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
                int numberOfBracketsToCalculate = 1;
                for(int bracket = 0; bracket < NUMBER_OF_TAX_BRACKETS; bracket++){
                    if(getTaxableIncome() >= taxBrackets[bracket]) numberOfBracketsToCalculate++;
                }
                for(int z = 0; z < numberOfBracketsToCalculate; z++){
                    if(z == 0){
                        incomeTax += taxBrackets[z] * getRates()[z];
                    }else{
                        if(getTaxableIncome() >= taxBrackets[z]){
                            incomeTax += (taxBrackets[z] - taxBrackets[z - 1]) * getRates()[z];
                        }else{
                            incomeTax += (getTaxableIncome() - taxBrackets[z - 1]) * getRates()[z];
                        }
                    }
                }
                getIntervals()[i][j + 1] = incomeTax;
            }
        }
        System.out.println("|_______________|_______________|_______________|_______________|_______________|\n" +
                "|               |               | Married Joint |               |               |\n" +
                "|    Taxable    |     Single    | or Qualifying |   Married     |    Head of    |\n" +
                "|    Income     |               | Widow(er)     |   Separate    |    a House    |\n" +
                "|_______________|_______________|_______________|_______________|_______________|\n");
        for (double[] interval : intervals) {
            System.out.println("|     " + String.format("%.0f", interval[0]) + "     |   " +
                    String.format("%.2f", interval[1]) + "    |   " + String.format("%.2f", interval[2]) +
                    (String.format("%.2f", interval[2]).length() == 7 ? "     |   " : "    |   ") +
                    String.format("%.2f", interval[3]) + "    |   " + String.format("%.2f", interval[4]) +
                    (String.format("%.2f", interval[4]).length() == 7 ? "     |" : "    |"));
            System.out.println("---------------------------------------------------------------------------------");
        }
        System.out.println('\n');
    }

    private static void get2009TaxTable(int amountFrom, int amountTo) {
        setRates(new double[]{0.10, 0.15, 0.25, 0.28, 0.33, 0.35});
        int rowsOfIntervals = ((amountTo - amountFrom) / 1000) + 1;
        intervals = new double[rowsOfIntervals][NUMBER_OF_TAX_BRACKETS + 1];
        // for each interval (of 1000 starting at amountFrom ending at amountTo)
        for(int i = 0; i < intervals.length; i++){
            double taxableIncomeForThisInterval = amountTo - (rowsOfIntervals - (i + 1)) * 1000;
            getIntervals()[i][0] = taxableIncomeForThisInterval;
            for(int j = 0; j < NUMBER_OF_TAX_BRACKETS; j++){
                setTaxableIncome(taxableIncomeForThisInterval);
                setFilingStatus(j);
                double[] taxBrackets = new double[]{};
                double incomeTax = 0;
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
                int numberOfBracketsToCalculate = 1;
                for(int bracket = 0; bracket < NUMBER_OF_TAX_BRACKETS; bracket++){
                    if(getTaxableIncome() >= taxBrackets[bracket]) numberOfBracketsToCalculate++;
                }

                for(int z = 0; z < numberOfBracketsToCalculate; z++){
                    if(z == 0){
                        incomeTax += taxBrackets[z] * getRates()[z];
                    }else{
                        if(getTaxableIncome() >= taxBrackets[z]){
                            incomeTax += (taxBrackets[z] - taxBrackets[z - 1]) * getRates()[z];
                        }else{
                            incomeTax += (getTaxableIncome() - taxBrackets[z - 1]) * getRates()[z];
                        }
                    }
                }
                getIntervals()[i][j + 1] = incomeTax;
            }
        }
        System.out.println("|_______________|_______________|_______________|_______________|_______________|\n" +
                "|               |               | Married Joint |               |               |\n" +
                "|    Taxable    |     Single    | or Qualifying |   Married     |    Head of    |\n" +
                "|    Income     |               | Widow(er)     |   Separate    |    a House    |\n" +
                "|_______________|_______________|_______________|_______________|_______________|\n");
        for (double[] interval : intervals) {
            System.out.println("|     " + String.format("%.0f", interval[0]) + "     |   " + String.format("%.2f", interval[1]) +
                    (String.format("%.2f", interval[1]).length() == 7 ? "     |   " : "    |   ") + String.format("%.2f", interval[2]) +
                    (String.format("%.2f", interval[2]).length() == 7 ? "     |   " : "    |   ") + String.format("%.2f", interval[3]) +
                    (String.format("%.2f", interval[3]).length() == 7 ? "     |   " : "    |   ") + String.format("%.2f", interval[4]) +
                    (String.format("%.2f", interval[4]).length() == 7 ? "     |" : "    |"));
            System.out.println("---------------------------------------------------------------------------------");
        }
    }
}
