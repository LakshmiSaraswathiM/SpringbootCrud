package com.example.SpringbootCrud;



import com.example.SpringbootCrud.Model.Employee;


import com.example.SpringbootCrud.Repo.EmployeeRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpringbootCrudApplicationTests {

	@Autowired
	private EmployeeRepository EmployeeRepository;


	@Test
	@Order(1)
	public  void TestAddEmployee()
	{
		Employee e=new Employee();
		e.setEmpId(20L);
		e.setSalary(40000);
		e.setEmpName("Latha");
		e.setDept("Sales");
		EmployeeRepository.save(e);
		assertNotNull(EmployeeRepository.findById(20L).get());
	}

	@Test
	@Order(2)
	//Testcase for Retreiving all the employee details
	public void TestEmployeeDetails()
	{
		List<Employee> list = EmployeeRepository.findAll();
		assertThat(list).size().isGreaterThan(4);
	}

	@Test
	@Order(3)
	//Testcase for Retreiving Single Employee details by using employee Id
	public void TestEmployeeDetailsById()
	{
		Employee employee = EmployeeRepository.findById(10L).get();
		assertEquals("Lakshmi", employee.getEmpName());
	}

	@Test
	@Order(4)
	//Testcase for updating Single employee details by using employee Id
	public void TestUpdateEmployee()
	{
		Employee emp=EmployeeRepository.findById(2L).get();
		emp.setSalary(27400);
		EmployeeRepository.save(emp);
		assertNotEquals(28000,EmployeeRepository.findById(2L).get().getSalary());

	}

	@Test
	@Order(5)
	//Testcase for deleting Single employee details by using employee Id
	public void TestDeleteEmployee()
	{
		Employee empl=EmployeeRepository.findById(5L).get();
		EmployeeRepository.delete(empl);
		assertThat(EmployeeRepository.existsById(5L)).isFalse();
	}

	@Test
	//Testcase for retreiving Single Employee Salary by using Employee Name
	public void TestEmployeeSalaryByEmpName()
	{
		Long list = EmployeeRepository.findByEmpName("Raghu");
		assertEquals(22000, list);
	}

	@Test
	//Testcase for updating Single employee Salary by using Employee Name
	public void TestUpdateeEmployeeSalary()
	{
		assertNotEquals(70000,EmployeeRepository.findEmpName("Bavani"));
	}

	@Test
	//Testcase for sorting (on basis of Employee Id) Employee Details by using department
	public void TestSortEmployee()
	{
		List<Employee> list = EmployeeRepository.findByDeptSorted("IT");
		assertThat(list).size().isEqualTo(4);
	}

}





