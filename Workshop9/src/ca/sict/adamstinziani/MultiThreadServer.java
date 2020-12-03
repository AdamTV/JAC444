/*
 * Workshop # 9
 * Course: JAC444 - Fall 2020
 * Last Name: Stinziani
 * First Name: Adam
 * ID: 124521188
 * Section: NDD
 * This assignment represents my own work in accordance with Seneca Academic Policy.
 * Signature: Adam Stinziani
 * Date: 2020-12-01
 */

package ca.sict.adamstinziani;

import java.io.IOException;
import java.net.ServerSocket;
import java.time.LocalDateTime;

// multi threaded server class for accepting connections and passing them to instances of Server class
public class MultiThreadServer {
    public static int clientId = 0;

    // main entry point for this module
    public static void main(String[] args) {
        ServerMain.appendScrollPane("Server started at " + LocalDateTime.now());
        try (ServerSocket serverSocket = new ServerSocket(4000)) {
            while (true) {
                try {
                    new Server(serverSocket.accept(), clientId++).start();
                } catch (IOException e) {
                    System.out.println("Server Exception" + e.getMessage());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Server Exception" + e.getMessage());
        }

    }
}
