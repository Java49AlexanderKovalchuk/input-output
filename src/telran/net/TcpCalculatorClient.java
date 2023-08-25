package telran.net;
import java.net.*;
import java.util.*;
import java.io.*;
import telran.view.*;
public class TcpCalculatorClient {
	
	static final String HOST = "localhost";
	static final int PORT = 5000;
	static Set<String> operatorSymbols = new LinkedHashSet<>(Arrays.asList("+", "-", "*", "/"));
	
	public static void main(String[] args) throws Exception {
		
		try(Socket socket = new Socket(HOST, PORT);
			PrintStream writer = new PrintStream(socket.getOutputStream());
			BufferedReader rider = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			InputOutput io = new ConsoleInputOutput();
			
			Menu menu = new Menu("TCP client calculator", Item.of("Send request to calculate", io1 -> {
				String operator = io1.readString(String.format("Enter operator symbol: %s", operatorSymbols), 
						"Wrong symbol", operatorSymbols);
				String operand_1 = getOperandString(io1, "1st");
				String operand_2 = getOperandString(io1, "2nd");
				writer.println(String.format("%s#%s#%s", operator, operand_1, operand_2));
				String response;
				try {
					response = rider.readLine();
					io1.writeLine("result: " + response);
				} catch (IOException e) {
					throw new RuntimeException(e.toString());
				}
			
			}), Item.ofExit());
			menu.perform(io);
		}
	}

	private static String getOperandString(InputOutput io, String byOrder) {
		return io.readString(String.format("Enter %s operand: any number", byOrder), 
					"Wrong operand", str -> str.matches("([0-9]*)\\.?([0-9]*)"));
	}

}
