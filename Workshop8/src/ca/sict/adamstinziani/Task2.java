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

// contains logic to complete task 2 of workshop 8.
public class Task2 {

    public static int maxNumberThreads = 500;

    // main entry point of this task.
    public static void main(String[] args) {
        new ReverseThread(0).start();
    }

    // custom thread class
    public static class ReverseThread extends Thread {

        final static Object lock = new Object();
        int i;

        public ReverseThread(int i) {
            this.i = i;
            setName(String.valueOf(i + 1));
        }

        // overridden run method to print greeting from each thread in reverse order
        @Override
        public void run() {
            synchronized (lock) {
                if (i++ < maxNumberThreads) {
                    new ReverseThread(i).start();
                    if (i == maxNumberThreads) {
                        lock.notifyAll();
                    }
                    try {
                        if (i < maxNumberThreads) {
                            lock.wait();
                            if (i == 1) lock.wait();
                            if (i == 2) lock.notify();
                        }
                        System.out.printf("Hello from thread %s\n", getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
