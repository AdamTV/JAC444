/*
 * Workshop # 1
 * Course: JAC444 - Fall 2020
 * Last Name: Stinziani
 * First Name: Adam
 * ID: 124521188
 * Section: NDD
 * This assignment represents my own work in accordance with Seneca Academic Policy.
 * Signature: Adam Stinziani
 * Date: 2020-09-26
 */

package com.adamstinziani;

import java.util.Scanner;

public class Main {

    // main entry point of workshop.
    public static void main(String[] args) {
        System.out.println("==================\n|| Begin Task 1 ||\n==================");
        Task1();
        System.out.println("==================\n||  End Task 1  ||\n==================\n");
        System.out.println("==================\n|| Begin Task 2 ||\n==================");
        Task2();
        System.out.println("==================\n||  End Task 2  ||\n==================");
    }

    // contains logic to complete task 1.
    private static void Task1() {
        float[][] arrayToProcess = GetTwoDimensionalArrayFromUserInput();
        if (arrayToProcess.length == 0 || arrayToProcess[0].length == 0) {
            System.out.println("Entered 0 for array dimension(s), cannot create 2-dimensional array. Skipping Task 1.");
            return;
        }
        MaxLocation maxLocation = MaxLocation.GetMaxLocation(arrayToProcess);
        System.out.println("The value of the largest element is: " + maxLocation.maxValue + " located at [" + maxLocation.row + "][" + maxLocation.column + "]");
    }

    // contains logic to complete task 2.
    private static void Task2() {
        Craps.playCraps();
    }

    // gets a two-dimensional array of floating-point numbers from user input.
    private static float[][] GetTwoDimensionalArrayFromUserInput() {
        System.out.print("Enter the number of rows in the array: ");
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        System.out.print("Enter the number of columns in the array: ");
        int columns = sc.nextInt();
        System.out.println();
        float[][] arrayCapturedFromUserInput = new float[rows][columns];
        System.out.println("Enter one row of the array at a time, separating column values with commas. Press enter to go to next row: ");
        for (int i = 0; i < rows; i++) {
            String line = sc.next();
            String[] columnValuesForRow = line.split(",");
            for (int j = 0; j < columnValuesForRow.length; j++) {
                arrayCapturedFromUserInput[i][j] = Float.parseFloat(columnValuesForRow[j].replace(",", ""));
            }
        }
        return arrayCapturedFromUserInput;
    }
}
