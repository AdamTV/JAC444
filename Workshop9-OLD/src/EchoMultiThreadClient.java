import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoMultiThreadClient {

    public static String name = null;
    public static String echoString;
    static Socket socket;
    static BufferedReader reader;
    static PrintWriter writer;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 4000);
            //socket.setSoTimeout(5000);


            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your name: ");
            //ClientMain.appendScrollPane("Enter your name");
            //getNameInput();

            name = scanner.nextLine();

            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            Thread t = new Thread(()->checkForMsgs());
            t.start();
            while (true) {
                msgServer();
            }

            // }catch(SocketTimeoutException e) {
            //	System.out.println("The Socket has been timed out");

        } catch (IOException e) {
            //ClientMain.appendScrollPane("Client Error: " + e.getMessage());
            System.out.println("Client Error: " + e.getMessage());

        }
    }

    private static void checkForMsgs() {
        try{
            String response;

            while(true){
                response = reader.readLine();
                if(response != null){
                    //ClientMain.appendScrollPane(response);
                    System.out.println(response);
                    reader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    //reader.reset();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void msgServer() {
        try {


            echoString = new Scanner(System.in).nextLine();
            //getEchoString();

            writer.println(name + ": " + echoString);


//            if (!echoString.equals("exit")) {
//                response = reader.readLine();
//                ClientMain.appendScrollPane(response);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getEchoString() {
        while (true) {
            if (echoString != null) return;
            else {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void getNameInput() {
        while (true) {
            if (name != null) return;
            else {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
