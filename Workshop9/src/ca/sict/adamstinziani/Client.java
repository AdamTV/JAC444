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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// client class that communicates with server class over a socket
public class Client {

    public static String name = null;
    public static String echoString;
    static Socket socket;
    static BufferedReader reader;
    static PrintWriter writer;

    // main entry point for this module
    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 4000);
            ClientMain.appendScrollPane("Enter your name");
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            new Thread(Client::acceptMessagesFromServer).start();
        } catch (IOException e) {
            ClientMain.appendScrollPane("Client Error: " + e.getMessage());
        }
    }

    // Checks for messages from server - can be received as soon as connection established.
    private static void acceptMessagesFromServer() {
        try {
            String response;
            while (true) {
                response = reader.readLine();
                if (response != null) {
                    ClientMain.appendScrollPane(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Sends message to server
    public static void sendMessageToServer() {
        try {
            writer.println(name + ": " + echoString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

