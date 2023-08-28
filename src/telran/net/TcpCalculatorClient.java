package telran.net;
import java.net.*;
import java.util.*;
import java.io.*;
import telran.view.*;
public class TcpCalculatorClient {
	
	static final String HOST = "localhost";
	static final int PORT = 5000;
	
	
	public static void main(String[] args) throws Exception {
		
		try(Socket socket = new Socket(HOST, PORT);
			PrintStream writer = new PrintStream(socket.getOutputStream());
			BufferedReader rider = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			InputOutput io = new ConsoleInputOutput();
			
			Menu menu = new Menu("Calculator Application", Item.of("send request", io1 -> {
				HashSet<String> requests = new HashSet<>(Arrays.asList("add", "minus", 
						"multiply", "divide"));
				String requestType = io1.readString("Enter operation type " + requests, "Wrong operation", requests);
				double op1 = io1.readDouble("Enter first number", "Wrong number");
				double op2 = io1.readDouble("Enter second number", "Wrong number");
				writer.println(String.format("%s#%f#%f", requestType, op1, op2));
				try {
					String response = rider.readLine();
					io1.writeLine(response);
				} catch(IOException e) {
					throw new RuntimeException(e.toString());
				}
			}), Item.ofExit());
			menu.perform(io);
		}
	}
}
				
