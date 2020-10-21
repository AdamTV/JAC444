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

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// contains logic to count all capital and lower case letters from a user-specified file
public class LetterCounter {

    // counts letters from user-specified file
    public static void countLetters() {
        var alphabet = new Character[]{'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z'};
        ArrayList<Integer> counts = new ArrayList<>();
        BufferedReader inputStream;
        StringBuilder sb = new StringBuilder();
        String textFromFile = "";
        System.out.print("Enter a file name: ");
        String fileName;
        try {
            fileName = new Scanner(System.in).next();
            inputStream = new BufferedReader(new FileReader(fileName));
            String l;
            while ((l = inputStream.readLine()) != null) {
                sb.append(l);
            }
            textFromFile = sb.toString();
        } catch (Exception e) {
            System.out.printf("Error: %s%n", e);
        }

        for (Character letter : alphabet) {
            Pattern pattern = Pattern.compile("[^" + letter + "]*" + letter);
            Matcher matcher = pattern.matcher(textFromFile);
            int count = 0;
            while (matcher.find()) {
                count++;
            }
            counts.add(count);
        }

        for (Character letter : alphabet) {
            System.out.printf("Number of %s's: %s%n", letter, counts.get(0));
            counts.remove(0);
        }
    }

}
