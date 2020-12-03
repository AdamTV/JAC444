
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.PrintWriter;
	import java.net.ServerSocket;
	import java.net.Socket;

	public class EchoMultiThreadServer {
		public static void main(String [] args) {
			try(ServerSocket serverSocket = new ServerSocket(4000)){
				while(true) {
					
						/*Socket socket = serverSocket.accept(); // creating socket and opening for connection from client
						System.out.println("Client Connected");
						
						BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
						PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
						

						String echoString = input.readLine();*/
						new Echoer(serverSocket.accept()).start();
						 
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
					}catch(IOException e) {
						System.out.println("Server Exception"+e.getMessage());
				}
				
		}
}
