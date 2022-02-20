package com.example.SpringbootCrud.Repo;

import com.example.SpringbootCrud.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

//JpaRespository<Employee(class),Primary key is of type Long i.e empId>
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

       //Assignment case 2: Display Salary of Single employee (using Employee Name)
       @Query(value="Select salary from Employee where empName=?1")
       Long findByEmpName(String empName);

       //Assignment case 3: Update salary of Employee
       @Transactional
       @Modifying
       @Query(value="Update Employee employee SET employee.salary=salary+(salary*0.1) where employee.empName=?1")
       int findEmpName(String empName);

       //Assignment case 4:Display Employees with particular Department in Ascending order (EMPLOYEE ID)
       @Query(value = "from Employee where dept=?1 order by empId")
       List<Employee> findByDeptSorted(String dept);

}
