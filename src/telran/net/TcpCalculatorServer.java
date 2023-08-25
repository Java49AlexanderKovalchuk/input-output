package telran.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;

public class TcpCalculatorServer {
	
	static final int PORT = 5000;
	
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(PORT);
		while(true) {
			Socket socket = serverSocket.accept();
			clientRun(socket);
		}
	}
	
	private static void clientRun(Socket socket) {
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream writer = new PrintStream(socket.getOutputStream())) {
			while(true) {
				String line = reader.readLine();
				if(line == null) {
					System.out.println("Client closed normally connection");
					break;
				}
				String response = getResponse(line);
				writer.println(response);
			}
		} catch (Exception e){
			System.out.println("Client closed abnormally connection");
		}
		
	}

	private static String getResponse(String line) {
		
		String response = "Wrong request structure, usage: <operation symbol>#<1st operand>#<2nd operand";
		String[] tokens = line.split("#");
		if(tokens.length == 3) {
			response = switch (tokens[0]) {
			case "+" -> Double.toString(getOperandDouble(tokens[1]) + getOperandDouble(tokens[2]));
			case "-" -> Double.toString(getOperandDouble(tokens[1]) - getOperandDouble(tokens[2]));
			case "*" -> Double.toString(getOperandDouble(tokens[1]) * getOperandDouble(tokens[2]));
			case "/" -> Double.toString(getOperandDouble(tokens[1]) / getOperandDouble(tokens[2]));
			default -> "wrong request";
			};
		}
		return response;
	}

	private static double getOperandDouble(String string) {
		return Double.parseDouble(string);
		
	}

}
