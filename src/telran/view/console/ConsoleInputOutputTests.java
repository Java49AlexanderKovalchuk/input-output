package telran.view.console;

import java.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ConsoleInputOutputTests {
	
	ConsoleInputOutput inputOutput = new ConsoleInputOutput();
	
	@Test
	@Disabled
	void readIntTest() {
		int res = inputOutput.readInt("enter int number", "invalid value was entered: ");
		inputOutput.writeLine("int number :" + res);
	}
	
	@Test
	@Disabled
	void readIntWithIntervalTest() {
		int res = inputOutput.readInt("enter int number in the range ", "invalid value was entered", 10, 1000); 
		inputOutput.writeLine("was entered: " + res);
	}
	
	@Test
	@Disabled
	void readLongTest() {
		long res = inputOutput.readLong("enter long number", "invalid value was entered ");
		inputOutput.writeLine("was entered: " + res);
	}

	@Test
	@Disabled
	void readLongWithIntervalTest() {
		long res = inputOutput.readLong("enter long number in the range: ", "invalid value was entered ", 
				100, 20_000_000_000l);
		inputOutput.writeLine("was entered " + res);
	}
	
	@Test
	@Disabled
	void readStringPredicateTest() {
		String res = inputOutput.readString("enter symbols containing @ ", "invalid enter", 
				string -> string.contains("@"));
		inputOutput.writeLine("was entered " + res);
	}
	
	@Test
	@Disabled
	void readStringWithSet() {
		Set <String> options = new HashSet<>();
		options.add("alpha");
		options.add("betha");
		options.add("gamma");
		String prompt = "enter one of the options: ";
		String res = inputOutput.readString(prompt, "entered no one options", options);
		inputOutput.writeLine("was entered " + res);
	}
	
	@Test
	@Disabled
	void readDateTest() {
		LocalDate res = inputOutput.readDate("enter date in format yyyy-mm-dd", 
				"Invalid entering, follow the format yyyy-mm-dd");
		inputOutput.writeLine("was entered " + res);
	}
	
	@Test
	@Disabled
	void readDateIntervalTest() {
		LocalDate from = LocalDate.of(2000, 01, 20);
		LocalDate to = LocalDate.of(2023, 9, 20);
		
		LocalDate res = inputOutput.readDate(
				"enter date from " + from + " to " + to + " in format yyyy-mm-dd", 
				"invalid date", from, to);
		inputOutput.writeLine("was entered " + res);
	}
	
	@Test
	void readDoubleTest() {
		double res = inputOutput.readDouble("enter double number ", "entered value is not a double");
		inputOutput.writeLine("was entered " + res);
	}
}
