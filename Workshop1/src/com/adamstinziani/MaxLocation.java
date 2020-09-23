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

// contains logic to calculate maximum value of two-dimensional array and store its info.
class MaxLocation {
    // row index of maxValue in a a two-dimensional array.
    int row;
    // column index of maxValue in a two-dimensional array.
    int column;
    // maximum value in the two-dimensional array.
    float maxValue;

    // initialize this class with default values.
    MaxLocation(){
        maxValue = 0;
    }

    // gets maximum location from a two-dimensional array of floating-point numbers.
    public static MaxLocation GetMaxLocation(float[][] arrayToProcess) {
        MaxLocation maxLocation = new MaxLocation();
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
