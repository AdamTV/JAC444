package com.adamstinziani;

public class Craps {

    private static int establishedPoint;
    private static boolean roundOnePassed = false;

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