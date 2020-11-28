/*
 * Workshop # 6
 * Course: JAC444 - Fall 2020
 * Last Name: Stinziani
 * First Name: Adam
 * ID: 124521188
 * Section: NDD
 * This assignment represents my own work in accordance with Seneca Academic Policy.
 * Signature: Adam Stinziani
 * Date: 2020-11-10
 */

package ca.sict.adamstinziani;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.DoubleStream;

// contains logic to complete task 2 of this workshop
public class ArrayCalculator {
    public static final ArrayProcessor max = (s) -> Arrays.stream(s).max().isPresent() ? Arrays.stream(s).max().getAsDouble() : -1;
    public static final ArrayProcessor min = (s) -> Arrays.stream(s).min().isPresent() ? Arrays.stream(s).min().getAsDouble() : -1;
    public static final ArrayProcessor sum = (s) -> DoubleStream.of(s).sum();
    public static final ArrayProcessor avg = (s) -> Arrays.stream(s).average().isPresent() ? Arrays.stream(s).average().getAsDouble() : -1;
    static double[] test = new double[]{1.0, 2.0, 3.0, 4.0};
    static double value = 0;
    public static final ArrayProcessor cnt = (s) -> {
        double count = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == value) count++;
        }
        return count;
    };

    // counts occurance of a value in the loaded array
    public static ArrayProcessor counter(double valueArg) {
        value = valueArg;
        return cnt;
    }

    // main entry point for task 2 of this workshop
    public static void main(String[] args) {
        System.out.print("Enter an array of doubles, with each value separated by a comma: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] results = new String[0];
        double val = 0;
        try {
            results = br.readLine().split(",");
            System.out.print("Enter a value to count in the array: ");
            val = Double.parseDouble(br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
        test = new double[results.length];
        for (int i = 0; i < results.length; i++) {
            test[i] = Double.parseDouble(results[i]);
        }
        System.out.println("Max: " + max.apply(test));
        System.out.println("Min: " + min.apply(test));
        System.out.println("Sum: " + sum.apply(test));
        System.out.println("Avg: " + avg.apply(test));
        System.out.println(val + " occurs in the array " + counter(val).apply(test) + " times.");

    }
}
