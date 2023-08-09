package telran.employees.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.SalaryDistribution;

public class CompanyImpl implements Company {
	LinkedHashMap<Long, Employee> employees = new LinkedHashMap<>(); 
	
	@Override
	public boolean addEmployee(Employee empl) {
		
		return employees.putIfAbsent(empl.id(), empl) == null;
	}

	@Override
	public Employee removeEmployee(long id) {
		
		return employees.remove(id);
	}

	@Override
	public Employee getEmployee(long id) {
		
		return employees.get(id);
	}

	@Override
	public List<Employee> getEmployees() {
		
		return new ArrayList<>(employees.values());
	}

	@Override
	public List<DepartmentSalary> getDepartmentSalaryDistribution() {
		
		return employees.values().stream()
				.collect(Collectors.groupingBy(Employee::department, 
						Collectors.averagingInt(Employee::salary)))
				.entrySet().stream().map(e -> new DepartmentSalary(e.getKey(),
						e.getValue())).toList();
	} 

	@Override
	public List<SalaryDistribution> getSalaryDistribution(int interval) {
		
		return employees.values().stream()
				.collect(Collectors.groupingBy(n -> n.salary() / interval, 
						Collectors.counting()))
				.entrySet().stream()
				.map(e -> new SalaryDistribution(e.getKey() * interval, 
						e.getKey() * interval + interval - 1,
						e.getValue().intValue())).toList();
	
	}

	

	@Override
	public void restore(String filePath) {

		try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))) {
			List<Employee> list = (ArrayList<Employee>) input.readObject();
			list.stream().forEach(empl -> this.addEmployee(empl));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void save(String filePath) {
		
		try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePath))) {
			List<Employee> list = new ArrayList<>(employees.values());
			output.writeObject(list);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}