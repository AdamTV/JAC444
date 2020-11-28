/*
 * Workshop # 8
 * Course: JAC444 - Fall 2020
 * Last Name: Stinziani
 * First Name: Adam
 * ID: 124521188
 * Section: NDD
 * This assignment represents my own work in accordance with Seneca Academic Policy.
 * Signature: Adam Stinziani
 * Date: 2020-11-21
 */

package ca.sict.adamstinziani;

import java.time.Duration;
import java.time.Instant;

// contains logic to complete task 1 of workshop 8.
public class Task1 {

    // instance variables for use with lambdas.
    static double[][] sum1;
    static double[][] sum2;
    static double[][] sum3;
    static double[][] sum4;

    // main entry point of this task.
    public static void main(String[] args) {

        // instantiate arrays to work with.
        double[][] a = new double[2000][2000];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] = i * j * i * j;
            }
        }
        double[][] b = new double[2000][2000];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                b[i][j] = (double) i / (double) j / (double) i / (double) j;
            }
        }

        // split each array into 4 arrays for parallel calculation.
        double[][] m1 = new double[500][500];
        double[][] m2 = new double[500][500];
        double[][] m3 = new double[500][500];
        double[][] m4 = new double[500][500];
        double[][] m5 = new double[500][500];
        double[][] m6 = new double[500][500];
        double[][] m7 = new double[500][500];
        double[][] m8 = new double[500][500];
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                m1[i][j] = a[i][j];
                m5[i][j] = b[i][j];

                m2[i][j] = a[i + 500][j + 500];
                m6[i][j] = b[i + 500][j + 500];

                m3[i][j] = a[i + 1000][j + 1000];
                m7[i][j] = b[i + 1000][j + 1000];

                m4[i][j] = a[i + 1500][j + 1500];
                m8[i][j] = b[i + 1500][j + 1500];

            }
        }

        // create threads to be executed in parallel.
        Thread t1 = new Thread(() -> sum1 = MatrixCalculator.parallelAddMatrix(m1, m5));
        Thread t2 = new Thread(() -> sum2 = MatrixCalculator.parallelAddMatrix(m2, m6));
        Thread t3 = new Thread(() -> sum3 = MatrixCalculator.parallelAddMatrix(m3, m7));
        Thread t4 = new Thread(() -> sum4 = MatrixCalculator.parallelAddMatrix(m4, m8));

        // PARALLEL EXECUTION.
        Instant start1 = Instant.now();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant end1 = Instant.now();

        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                a[i][j] = m1[i][j];
                b[i][j] = m5[i][j];

                a[i + 500][j + 500] = m2[i][j];
                b[i + 500][j + 500] = m6[i][j];

                a[i + 1000][j + 1000] = m3[i][j];
                b[i + 1000][j + 1000] = m7[i][j];

                a[i + 1500][j + 1500] = m4[i][j];
                b[i + 1500][j + 1500] = m8[i][j];
            }
        }

        // SEQUENTIAL EXECUTION.
        Instant start2 = Instant.now();
        MatrixCalculator.sequentialAddMatrix(a, a);
        Instant end2 = Instant.now();

        // results.
        Duration timeElapsed1 = Duration.between(start1, end1);
        Duration timeElapsed2 = Duration.between(start2, end2);
        System.out.printf("Duration in nanoseconds for parallel calculation:   %s\nDuration in nanoseconds for sequential calculation: %s\nParallel execution was %s times faster than sequential.", timeElapsed1.getNano(), timeElapsed2.getNano(), (double) timeElapsed2.getNano() / (double) timeElapsed1.getNano());
    }
}
