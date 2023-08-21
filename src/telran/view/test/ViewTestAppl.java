package telran.view.test;


import java.util.ArrayList;

import telran.view.*;
public class ViewTestAppl {

	
	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		
		Menu menu = new Menu("Operations", getItems());
		
		menu.perform(io);

	}

	private static Item[] getItems() {
		Item[] items = {
				NumbersOperationsMenu.getOperationsItem("Number operations"),
				DateOperationsMenu.getDateOperationsItem("Date operations"),
				Item.ofExit()
		};
		return items;
	}

}
