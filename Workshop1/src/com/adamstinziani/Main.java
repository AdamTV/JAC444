/*
 * Workshop # 1
 * Course: JAC444 - Fall 2020
 * Last Name: Stinziani
 * First Name: Adam
 * ID: 124521188
 * Section: NDD
 * This assignment represents my own work in accordance with Seneca Academic Policy.
 * Signature: Adam Stinziani
 * Date:<submission date>
 */

package com.adamstinziani;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("==================\n|| Begin Task 1 ||\n==================");
        Task1();
        System.out.println("==================\n||  End Task 1  ||\n==================\n\n");
        System.out.println("==================\n|| Begin Task 2 ||\n==================");
        Task2();
        System.out.println("==================\n||  End Task 2  ||\n==================");
    }

    private static void Task1(){
        float[][] arrayToProcess = GetTwoDimensionalArrayFromUserInput();
        MaxLocation maxLocation = GetMaxLocation(arrayToProcess);
        System.out.println("The value of the largest element is: " + maxLocation.maxValue + " located at [" + maxLocation.row + "][" + maxLocation.column + "]");
    }

    private static void Task2() {
        Craps.playCraps();
    }

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

    private static MaxLocation GetMaxLocation(float[][] arrayToProcess) {
        MaxLocation maxLocation = new MaxLocation();
        maxLocation.maxValue = 0;

        for (int column = 0; column < arrayToProcess[0].length; column++) {
            for (int row = 0; row < arrayToProcess.length; row++) {
                if (arrayToProcess[row][column] > maxLocation.maxValue) {
                    maxLocation.column = column;
                    maxLocation.row = row;
                    maxLocation.maxValue = arrayToProcess[row][column];
                }
            }
        }

        return maxLocation;
    }
}
