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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// server class that communicates with client over a socket passed in on class instantiation.
public class Server extends Thread {

    private static final List<Socket> sockets = new ArrayList<>();
    private final int clientId;

    // instantiate this class with given Socket argument.
    public Server(Socket socket, int clientId) {
        this.clientId = clientId;
        sockets.add(socket);
    }

    // override Thread.run()
    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(sockets.get(clientId).getInputStream()));
            ServerMain.appendScrollPane("Connection from " + sockets.get(clientId) + " at " + LocalDateTime.now());
            while (true) {
                String echoString = input.readLine();
                ServerMain.appendScrollPane("Received Client Input: " + echoString);
                String test = echoString.substring(echoString.indexOf(':') + 2);
                if (test.equals("exit")) {
                    ServerMain.appendScrollPane("Disconnecting from " + sockets.get(clientId) + " at " + LocalDateTime.now());
                    break;
                }
                // Send message to other clients.
                for (int i = 0; i < sockets.toArray().length; i++) {
                    if (i != clientId && !sockets.get(i).isClosed()) {
                        new PrintWriter(sockets.get(i).getOutputStream(), true).println(echoString);
                    }
                }
            }
        } catch (IOException e) {
            ServerMain.appendScrollPane("IOException: " + e.getMessage());
        } finally {
            try {
                sockets.get(clientId).close();
                // remove client's socket from list to remove unnecessary resources
                sockets.remove(sockets.get(clientId));
                MultiThreadServer.clientId--;
            } catch (IOException e) {
                ServerMain.appendScrollPane("Error closing socket for clientId + " + clientId);
            }
        }
    }
}
