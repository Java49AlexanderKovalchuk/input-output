package telran.io;

import java.io.*;

public class TextCommentSeparation {
	private static final String pattern = "//";
	public static void main(String[] args) {
		
		if(args.length != 3) {
			System.out.println("Usage: must be three arguments "
					+ "(source, destination1, destination2)");
		} else {
			try(BufferedReader reader = 
					new BufferedReader(new FileReader(args[0]));
				PrintWriter outputNoCom = new PrintWriter(args[1]);
				PrintWriter outputCom = new PrintWriter(args[2])) {
				inputOutput(reader, outputNoCom, outputCom);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void inputOutput(BufferedReader reader,
			PrintWriter outputNoCom, PrintWriter outputCom) {
		reader.lines().forEach(line -> getOutput(line, outputNoCom, outputCom));
		outputCom.close();
		outputNoCom.close();
	}
	
	//using regex to no consider spaces that may be before "//"  
	private static void getOutput(String line, PrintWriter outputNoCom, PrintWriter outputCom) {
		if(line.replaceAll("^\\s+", "").substring(0, 2).equals(pattern)) {
			outputNoCom.println(line);
		} else {
			outputCom.println(line);
		}	
	}
	
}
