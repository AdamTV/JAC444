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

// contains logic to calculate sum of matrices sequentially and in parallel.
public class MatrixCalculator {

    static Object lock = new Object();

    // calculate sum of matrices sequentially.
    public static double[][] sequentialAddMatrix(double[][] c, double[][] d) {
        double[][] sum = new double[c.length][c[0].length];
        for (int i = 0; i < sum.length; i++) {
            for (int j = 0; j < sum[0].length; j++) {
                sum[i][j] = c[i][j] + d[i][j];
            }
        }
        return sum;
    }

    // calculate sum of matrices in parallel.
    public static double[][] parallelAddMatrix(double[][] a, double[][] b) {
        synchronized (lock) {
            return sequentialAddMatrix(a, b);
        }
    }
}
