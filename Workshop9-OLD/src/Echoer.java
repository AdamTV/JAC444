import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Echoer extends Thread{
    private int clientId;
    private static List<Socket> sockets = new ArrayList<>();
    public Echoer(Socket socket, int clientId) {
        this.clientId = clientId;
        this.sockets.add(socket);
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(sockets.get(clientId).getInputStream()));
            //PrintWriter writer = new PrintWriter(sockets.get(clientId).getOutputStream(), true);
            output("Connection from " + sockets.get(clientId) + " at " + LocalDateTime.now());
            while(true) {
                String echoString = input.readLine();
                output("Received Client Input: " + echoString);
//                if(echoString.equals("exit")) {
//                    break;
//                }

				/*try {

					Thread.sleep(5000);

				}catch(InterruptedException e) {
					System.out.println("Thread Interrupted");
				}*/
                for(int i = 0; i < sockets.toArray().length; i++){
                    if(i != clientId){
                        new PrintWriter(sockets.get(i).getOutputStream(), true).println(echoString);
                    }
                }
                //writer.println(echoString);
            }
        }catch(IOException e) {
            output("Oooops " + e.getMessage());
        }finally {
            try {
                sockets.get(clientId).close();
            }catch(IOException e) {
                // later
            }

        }

    }

    static void output(String s){
        //ServerMain.console.log(s);
        System.out.println(s);
    }

}
