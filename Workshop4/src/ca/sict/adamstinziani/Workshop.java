/*
 * Workshop # 4
 * Course: JAC444 - Fall 2020
 * Last Name: Stinziani
 * First Name: Adam
 * ID: 124521188
 * Section: NDD
 * This assignment represents my own work in accordance with Seneca Academic Policy.
 * Signature: Adam Stinziani
 * Date: 2020-10-15
 */

package ca.sict.adamstinziani;

import java.io.IOException;

// contains logic to complete this workshop.
public class Workshop {

    // main entry point of this workshop.
    public static void main(String[] args) {
        try {
            System.out.println("Welcome to hangman!");
            Hangman.playHangman();
            System.out.println("Thank you for playing hangman!");
            System.out.println("Welcome to letter counter!");
            LetterCounter.countLetters();
            System.out.println("Thank you for counting letters!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
