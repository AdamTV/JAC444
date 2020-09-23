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

// contains required logic to play craps
public class Craps {

    // point that is established during first round
    private static int establishedPoint;
    // flag to determine if round one has passed (and point has been established)
    private static boolean roundOnePassed = false;

    // plays craps until the game is either won or lost
    public static void playCraps() {

        int[] dice = new int[]{(int) (Math.random() * 6 + 1), (int) (Math.random() * 6 + 1)};

        int sumOfDice = dice[0] + dice[1];

        System.out.println("You rolled " + dice[0] + " + " + dice[1] + " = " + sumOfDice);

        if (!roundOnePassed) {
            roundOnePassed = true;
            switch (sumOfDice) {
                case 2:
                case 3:
                case 12:
                    System.out.println("Craps, Better Luck Next Time, You Lose.");
                    return;
                case 7:
                case 11:
                    System.out.println("Congratulations, You Win!");
                    return;
                default:
                    establishedPoint = sumOfDice;
                    System.out.println("Point is (established) set to: " + establishedPoint);
                    playCraps();
            }
        } else {
            if (sumOfDice == 7) {
                System.out.println("Craps, Better Luck Next Time, You Lose.");
                return;
            } else if (sumOfDice == establishedPoint) {
                System.out.println("Congratulations, You Win!");
                return;
            }
            playCraps();
        }
    }
}