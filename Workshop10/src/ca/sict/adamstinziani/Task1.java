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

import java.util.Scanner;

// Contains task one for this workshop.
public class Task1 {
    // Main entry point of this module.
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter time1 (hour minute second): ");
        var answers = sc.nextLine().split(" ");
        if(answers.length != 3) throw new Exception("Incorrect amount of values entered");
        Time time1 = new Time(Integer.parseInt(answers[0]), Integer.parseInt(answers[1]), Integer.parseInt(answers[2]));
        System.out.println(time1.toString());
        System.out.printf("Elapsed time in time1: %s%n", time1.getSeconds());
        System.out.print("Enter time2 (elapsed time in seconds): ");
        var answer = sc.nextLong();
        Time time2 = new Time(answer);
        System.out.println(time2.toString());
        System.out.printf("Elapsed time in time2: %s%n", time2.getSeconds());
        System.out.printf("time1.compareTo(time2) = %s%n", time1.compareTo(time2));
        Time time3 = (Time) time1.clone();
        System.out.println("time3 is created as a clone of time1");
        System.out.printf("time1.compareTo(time3) = %s%n", time1.compareTo(time3));
    }
}
