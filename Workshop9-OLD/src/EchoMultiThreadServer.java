import java.io.IOException;
import java.net.ServerSocket;
import java.time.LocalDateTime;

public class EchoMultiThreadServer {
    public static void main(String[] args) {
        output("Server started at " + LocalDateTime.now());
        try (ServerSocket serverSocket = new ServerSocket(4000)) {
            int clientId = 0;
            while (true) {
//                        System.out.println("Waiting for client");
//
//						Socket socket = serverSocket.accept(); // creating socket and opening for connection from client
//						System.out.println("Client Connected");
//
//						BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
//						PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
//
//
//						String echoString = input.readLine();
                new Echoer(serverSocket.accept(), clientId++).start();

						/* //break down
						 Socket socket = serverSocket.accept();
						 Echoer echoer = new Echoer(socket);
						 echoer.start();
						 */

						/*try {
							Thread.sleep(3000);
						}	catch(InterruptedException e) {
							System.out.println("Thread Interrupted");
							}*//*
							if(echoString.equals("exit")){
								break;
							}
						output.println("Echo from server: "+ echoString);*/
            }
        } catch (IOException e) {
            output("Server Exception" + e.getMessage());
        }

    }
    static void output(String s){
        //ServerMain.console.log(s);
        System.out.println(s);
    }
}
