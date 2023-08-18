package telran.view.test;

import java.util.function.BinaryOperator;

import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class NumbCalculator {
	
		static InputOutput io = new ConsoleInputOutput();
		static Menu menu = new Menu("Number Operations", getItems());
			
		
		static void calculate (InputOutput io, BinaryOperator<Double> operator) {
			double first = io.readDouble("Enter first number", "Must be any number");
			double second = io.readDouble("Enter second number", "Must be any number");
			io.writeLine(operator.apply(first, second));
		}
		
		static Item [] getItems() {
			Item[] items = {
				Item.of("Add numbers", io -> calculate(io, (a, b) -> a + b)),
				Item.of("Subtract numbers", io -> calculate(io, (a, b) -> a - b)),
				Item.of("Multyply numbers", io -> calculate(io, (a, b) -> a * b)),
				Item.of("Divide numbers", io -> calculate(io, (a, b) -> a / b)), 
				Item.ofExit()
			};
			return items;
		} 
}
