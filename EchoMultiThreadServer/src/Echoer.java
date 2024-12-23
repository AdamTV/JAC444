import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread{
	
	private Socket socket;
	public Echoer(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			
			while(true) {
				
				String echoString = input.readLine();
				System.out.println("Received Client Input: " + echoString);
				if(echoString.equals("exit")) {
					break;
				}
				
				/*try {
					
					Thread.sleep(5000);
					
				}catch(InterruptedException e) {
					System.out.println("Thread Interrupted");
				}*/
				
				writer.println(echoString);
			}
		}catch(IOException e) {
			System.out.println("Oooops " + e.getMessage());
		}finally {
			try {
				socket.close();
			}catch(IOException e) {
				// later
			}
			
		}
		
	}

}
