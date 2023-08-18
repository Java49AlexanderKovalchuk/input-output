package telran.view.test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class DateCalculator {
	static InputOutput io = new ConsoleInputOutput();
	static Menu menu = new Menu("Date Operations", getItems());
	
	static LocalDate[] getTwoDates(InputOutput io) {
		LocalDate firstDate = io.readDate("Enter first date yyyy-mm-dd", 
				"Enter must be in format yyyy-mm-dd");
		LocalDate secondDate = io.readDate("Enter second date yyyy-mm-dd", 
				"Enter must be in format yyyy-mm-dd");
		return new LocalDate[] {firstDate, secondDate};
	}
	static Item[] getItems() {
		Item[] items = {
				Item.of("Date after N days", DateCalculator::dateAfter),
				Item.of("Date before N days", DateCalculator::dateBefore),
				Item.of("Days number between dates", DateCalculator::daysBetween),
				Item.ofExit()
		};
		return items;
	}
	static void dateAfter(InputOutput io) {
		int days = io.readInt("Enter days number", "Must be integer");
		io.writeLine(LocalDate.now().plusDays(days));
	}
	static void dateBefore(InputOutput io) {
		int days = io.readInt("Enter days number", "Must be integer");
		io.writeLine(LocalDate.now().minusDays(days));
	}
	static void daysBetween(InputOutput io) {
		LocalDate [] dates = getTwoDates(io);
		io.writeLine(Math.abs(dates[0].until(dates[1], ChronoUnit.DAYS)));
	}
}
