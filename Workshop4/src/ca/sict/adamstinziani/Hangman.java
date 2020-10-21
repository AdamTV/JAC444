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

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// contains logic to play hangman
public class Hangman {

    private static final String path = "C:\\Users\\adams\\source\\repos\\GitHub\\JAC444\\Workshop4\\src\\ca\\sict\\adamstinziani\\hangman.txt";
    private static boolean gameOver;
    private static ArrayList<Character> triedLetters;
    private static int timesMissed;
    private static String hangmanWord;
    private static ArrayList<String> hangmanWords;
    private static StringBuilder sb;

    // high level logic to play hangman
    public static void playHangman() throws IOException {
        initializeGameVariables();
        do {
            System.out.printf("Guess a letter from the following word: %s. Your Guess: ", sb);
            String c = "";
            try {
                c = new Scanner(System.in).next();
            } catch (Exception e) {
                System.out.printf("Error: %s%n", e);
            }
            if (c.length() != 1) {
                System.out.println("Must print one letter.");
                continue;
            }
            if (triedLetters.contains(c.charAt(0))) {
                System.out.printf("You've already tried the letter %c.%n", c.charAt(0));
                continue;
            }
            triedLetters.add(c.charAt(0));
            CharSequence cs = c.subSequence(0, 1);
            if (!hangmanWord.contains(cs)) {
                System.out.printf("%s is not in the word.%n", cs.toString());
                timesMissed++;
                continue;
            }
            for (int i = 0; i < hangmanWord.length(); i++) {
                if (hangmanWord.charAt(i) == cs.charAt(0)) sb.setCharAt(i, cs.charAt(0));
            }
            if (!sb.toString().contains("*")) gameOver = true;
        } while (!gameOver);
        endGame();
    }

    // initializes the class variables at beginning of game
    private static void initializeGameVariables() {
        gameOver = false;
        timesMissed = 0;
        triedLetters = new ArrayList<>();
        hangmanWords = new ArrayList<>();

        try (BufferedReader inputStream = new BufferedReader(new FileReader(path))) {
            String l;
            while ((l = inputStream.readLine()) != null) {
                hangmanWords.add(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int index = new Random().nextInt(hangmanWords.size());

        hangmanWord = hangmanWords.get(index);

        sb = new StringBuilder();

        sb.append("*".repeat(hangmanWord.length()));
    }

    // ends the game by adding a new word to the game and asking to play again
    private static void endGame() throws IOException {
        System.out.printf("You won! The word is %s. You missed %s time%s.%n", hangmanWord, timesMissed, timesMissed == 1 ? "" : "s");
        System.out.print("Enter a new word to be added to the game: ");
        String word = "";
        try {
            word = new Scanner(System.in).next();
        } catch (Exception e) {
            System.out.printf("Error: %s%n", e);
        }
        if (hangmanWords.contains(word)) {
            System.out.printf("The word %s is already in the game.%n", word);
        } else {
            hangmanWords.add(word);
            try (PrintWriter outputStream = new PrintWriter(new FileWriter(path))) {
                for (String line : hangmanWords) {
                    outputStream.println(line);
                }
                System.out.printf("The word %s has been added to the game.%n", word);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.print("Do you want to play again? Enter y/n: ");
        String answer = "";
        try {
            answer = new Scanner(System.in).next();
        } catch (Exception e) {
            System.out.printf("Error: %s%n", e);
        }
        if (answer.length() != 1) {
            System.out.println("Must print one letter. Exiting game.");
            return;
        }
        if (answer.charAt(0) == 'y') playHangman();
    }
}

