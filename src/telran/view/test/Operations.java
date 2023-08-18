package telran.view.test;
import telran.view.*;
import telran.view.test.SimpleCalculatorAppl.*;
import telran.view.test.NumbCalculator.*;
public class Operations {
	
	
	public static void main(String[] args) {
				
		InputOutput io = new ConsoleInputOutput();
		Menu menu = new Menu("Operations", getItems());
		menu.perform(io);
	}
	
	static Item [] getItems() {
		Item[] items = {
				Item.of("Number Operations", io -> getNumbOperations(io)),
				Item.of("Date Operations", io -> getDateOperations(io)),
				Item.ofExit()
		};
		return items;
	}

	private static void getDateOperations(InputOutput io) {
		DateCalculator.menu.perform(io);
		
	}

	private static void getNumbOperations(InputOutput io) {
		NumbCalculator.menu.perform(io);	
	}
	
}
