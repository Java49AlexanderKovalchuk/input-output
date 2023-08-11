package telran.employees.service;

import telran.employees.dto.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
public interface Company {
	boolean addEmployee(Employee empl);
	Employee removeEmployee(long id);
	Employee getEmployee(long id);
	List<Employee> getEmployees();
	List<DepartmentSalary> getDepartmentSalaryDistribution(); //returns list of all departments with average salary 
	List<SalaryDistribution> getSalaryDistribution(int interval);//returns salary values distributions
	default void restore(String filePath) {
		if(Files.exists(Path.of(filePath))) {
			try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))) {
				List<Employee> employeesRestore = (List<Employee>) input.readObject();
				employeesRestore.forEach(this::addEmployee);
				} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
				}
			}
	}
	default public void save(String filePath) {
		try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filePath))) {
			stream.writeObject(getEmployees());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}  
	
	List<Employee> getEmployeesByDepartment(String department);
	List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo);
	List<Employee> getEmployeesByAge(int ageFrom, int ageTo);
	Employee updateSalary(long id, int newSalary);
	Employee updateDepartment(long id, String newDepartment);
	
	
	
}
