package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import telran.employees.dto.*;
import telran.employees.service.Company;
import telran.employees.service.CompanyImpl;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyTest {
	private static final long ID1 = 123;
	private static final String DEP1 = "dep1";
	private static final int SALARY1 = 10000;
	private static final int YEAR1 = 2000; 
	private static final LocalDate DATE1 = LocalDate.ofYearDay(YEAR1, 100);
	private static final long ID2 = 124;
	private static final long ID3 = 125;
	private static final long ID4 = 126;
	private static final long ID5 = 127;
	private static final String DEP2 = "dep2";
	private static final String DEP3 = "dep3";
	private static final int SALARY2 = 5000;
	private static final int SALARY3 = 15000;
	private static final int YEAR2 = 1990;
	private static final int YEAR3 = 2003;
	private static final LocalDate DATE2 = LocalDate.ofYearDay(YEAR2, 100);
	private static final LocalDate DATE3 = LocalDate.ofYearDay(YEAR3, 100);
	private static final long ID_NOT_EXIST = 10000000;
	private static final String TEST_DATA = "test.data";
	private static final int INTERVAL = 5000;
	Employee empl1 = new Employee(ID1, "name", DEP1, SALARY1, DATE1);
	Employee empl2 = new Employee(ID2, "name", DEP2, SALARY2, DATE2);
	Employee empl3 = new Employee(ID3, "name", DEP1, SALARY1, DATE1);
	Employee empl4 = new Employee(ID4, "name", DEP2, SALARY2, DATE2);
	Employee empl5 = new Employee(ID5, "name", DEP3, SALARY3, DATE3);
	Employee[] employees = {empl1, empl2, empl3, empl4, empl5}; 
	Company company;
	
	@BeforeEach
	void setUp() throws Exception {
		company = new CompanyImpl();
		for(Employee empl: employees) {
			company.addEmployee(empl);
			
		}
	}

	@Test
	void testAddEmployee() {
		assertFalse(company.addEmployee(empl1));
		assertTrue(company.addEmployee(new Employee(ID_NOT_EXIST, "name", DEP1, SALARY1, DATE1)));
	}

	@Test
	void testRemoveEmployee() {
		assertNull(company.removeEmployee(ID_NOT_EXIST));
		assertEquals(empl1, company.removeEmployee(ID1));
		Employee[] expected = {empl2, empl3, empl4, empl5}; 
		assertArrayEquals(expected, company.getEmployees()
				.toArray(Employee[]::new));
	}

	@Test
	void testGetEmployee() {
		assertEquals(empl1, company.getEmployee(ID1));
		assertNull(company.getEmployee(ID_NOT_EXIST));
	}

	@Test
	void testGetEmployees() {
		assertArrayEquals(employees, company.getEmployees()
				.toArray(Employee[]::new));
	}

	@Test
	void testGetDepartmentSalaryDistribution() {
		DepartmentSalary[] expected = {
				new DepartmentSalary(DEP2, SALARY2),
				new DepartmentSalary(DEP1, SALARY1),
				new DepartmentSalary(DEP3, SALARY3)
				};
		DepartmentSalary[] depSal = company.getDepartmentSalaryDistribution()
				.toArray(DepartmentSalary[]::new);
		Arrays.sort(depSal, Comparator.comparing(DepartmentSalary::salary)
				.thenComparing(DepartmentSalary::department));
		assertArrayEquals(expected, depSal);
	}

	@Test
	void testGetSalaryDistribution() {
		List<SalaryDistribution> companySalaryDistr = 
				 company.getSalaryDistribution(INTERVAL);
		
		companySalaryDistr.forEach(salaryDistr -> System.out.println(salaryDistr.toString()));
		assertEquals(3, companySalaryDistr.size());
		assertEquals(2, companySalaryDistr.get(0).amountEmployees());
		assertEquals(1, companySalaryDistr.get(2).amountEmployees());
		assertEquals(SALARY2, companySalaryDistr.get(0).minSalary());
		assertEquals(SALARY3, companySalaryDistr.get(companySalaryDistr.size() - 1)
				.minSalary());
		
	}

	@Test
	@Order(2)
	void testRestore() {
		Company newCompany = new CompanyImpl();
		newCompany.restore(TEST_DATA);
		assertArrayEquals(employees, newCompany.getEmployees()
				.toArray(Employee[]::new));
	}

	@Test
	@Order(1)
	void testSave() {
		company.save(TEST_DATA);
	}

}