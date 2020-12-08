/*
 * Workshop # 10
 * Course: JAC444 - Fall 2020
 * Last Name: Stinziani
 * First Name: Adam
 * ID: 124521188
 * Section: NDD
 * This assignment represents my own work in accordance with Seneca Academic Policy.
 * Signature: Adam Stinziani
 * Date: 2020-12-07
 */

package ca.sict.adamstinziani;

import java.util.*;

// Contains task two for this workshop.
public class Task2 {
    public static List<Bank> banks;
    public static int limit;
    public static Map<Integer, Integer> loansPaid = new HashMap<>();

    // Main entry point of this module.
    public static void main(String[] args) {
        banks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of banks: ");
        int numOfBanks = sc.nextInt();
        System.out.print("Minimum asset limit: ");
        limit = sc.nextInt();
        for (int i = 0; i < numOfBanks; i++) {
            System.out.printf("For Bank # %s%n", i);
            System.out.print("Balance: ");
            double balance = sc.nextDouble();
            System.out.print("Number of banks loaned: ");
            int numOfLoans = sc.nextInt();
            Map<Integer, Double> loans = new HashMap<>();
            for (int j = 0; j < numOfLoans; j++) {
                System.out.print("Bank ID who gets the loan: ");
                int id = sc.nextInt();
                System.out.print("Loaned Amount: ");
                double amt = sc.nextDouble();
                loans.put(id, amt);
            }
            banks.add(new Bank(i, balance, loans));
        }
        calculateUnsafeBanks();
        Iterator<Map.Entry<Integer, Integer>> it = loansPaid.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> pair = it.next();
            System.out.printf("Bank %s was bankrupted and a loan was paid from bank %s to bank %s. Bank %s is unsafe.%n", pair.getValue(), pair.getKey(), pair.getValue(), pair.getValue());
            it.remove();
        }
    }

    // Recursively calculates which banks are safe.
    static void calculateUnsafeBanks() {
        boolean recall = false;
        for (Object bankObj :
                banks) {
            Bank bank = (Bank) bankObj;
            bank.setSafe();
            if (!bank.safe) {
                for (Object loanerBankObj :
                        banks) {
                    Bank loanerBank = (Bank) loanerBankObj;
                    if (loanerBank.loans.containsKey(bank.id)) {
                        var loanAmount = loanerBank.loans.remove(bank.id);
                        // loansPaid put in (from, to) order
                        loansPaid.put(loanerBank.id, bank.id);
                        bank.balance += loanAmount;
                        loanerBank.totalAssetsInLoans -= loanAmount;
                        loanerBank.setSafe();
                        if (!loanerBank.safe) {
                            recall = true;
                            break;
                        }
                        if (bank.totalAssetsInLoans + bank.balance >= limit) {
                            break;
                        }
                    }
                }
            }
            if (recall) calculateUnsafeBanks();
        }
    }

    // Inner class to represent a bank.
    static class Bank {
        public int id;
        public Double totalAssetsInLoans;
        public boolean safe;
        double balance;
        Map<Integer, Double> loans;

        // Construct object of this class according to parameters.
        public Bank(int id, double balance, Map<Integer, Double> loans) {
            this.id = id;
            this.balance = balance;
            this.loans = loans;
        }

        // Sets the safe value of this object.
        public void setSafe() {
            setTotalAssetsInLoans();
            safe = balance + totalAssetsInLoans >= limit;
        }

        // Sets the total assets in loans value of this object.
        public void setTotalAssetsInLoans() {
            totalAssetsInLoans = loans.values().stream().mapToDouble(Double::doubleValue).sum();
        }
    }
}
