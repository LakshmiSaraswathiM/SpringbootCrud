package com.example.SpringbootCrud.Controller;
import com.example.SpringbootCrud.Exception.UserNotFoundException;
import com.example.SpringbootCrud.Model.Employee;
import com.example.SpringbootCrud.Repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
public class ApiController {

    @Autowired
       EmployeeRepository EmployeeRepository;

        //basic operations upon the database as retrieving, inserting, updating and deleting data

        //inserting Employee Details into Employee table
        @PostMapping(path="/addEmployee")
        public String addEmployee(@Valid @RequestBody Employee employee)
         {
           EmployeeRepository.save(employee);
           return "Successfully added the Employee Details";
         }

        //retreiving Employee Details from Employee table
        @GetMapping(path="/employee")
        public  List<Employee> employeeDetails()
        {
        return EmployeeRepository.findAll();
        }
        //retreiving Employee details from Employee Table using EmployeeId
        @GetMapping("/employee/{empId}")
        public  Employee employeeDetailsById(@PathVariable("empId") Long empId)  {
               return EmployeeRepository.findById(empId)
                     .orElseThrow(( )-> new UserNotFoundException("Employee is not found with the id: "+empId));
        }

        //updating Employee Details in Employee table
        @PutMapping(path="/update/{empId}")
        public String updateEmployee( @Valid @PathVariable long empId,@RequestBody Employee employee)
        {
           Employee updatedEmployee=EmployeeRepository.findById(empId)
                           .orElseThrow(( )-> new UserNotFoundException("Employee is not found with the id: " + empId));
           updatedEmployee.setEmpId(employee.getEmpId());
           updatedEmployee.setSalary(employee.getSalary());
           updatedEmployee.setEmpName(employee.getEmpName());
           updatedEmployee.setDept(employee.getDept());
           EmployeeRepository.save(updatedEmployee);
           return "Successfully Updated the Employee Details";
        }

        //Deleting an Employee record from Employee table by using EmployeeId
        @DeleteMapping(path="/delete/{empId}")
        public String deleteEmployee(@PathVariable long empId)
        {
           Employee deletedEmployee=EmployeeRepository.findById(empId)
                           .orElseThrow(( )-> new UserNotFoundException("Employee is not found with the id: "+empId));
           EmployeeRepository.delete(deletedEmployee);
           return "Successfully Deleted the details of Employee with the id: "+empId;
        }

        //Assignment case 2: Display Salary of Single employee (using Employee Name)
        @GetMapping("/salarydetails/{empName}")
        public Long employeeSalaryByEmpName(@PathVariable("empName") String empName)
        {
            return EmployeeRepository.findByEmpName(empName);
        }

        // Assignment case 3: Update salary of Employee
        @PutMapping(path="/updatedEmployeeSalary/{empName}")
        public int updateeEmployeeSalary(@PathVariable String empName)
         {
           int updatecount=0;
           EmployeeRepository.findEmpName(empName);
           updatecount+=1;
           return updatecount;
         }

        //Assignment case 4:Display Employees with particular Department in Ascending order (EMPLOYEE ID)
        @GetMapping("/sortEmployee/{dept}")
        public List<Employee> sortEmployee(@PathVariable String dept)  {
           return EmployeeRepository.findByDeptSorted(dept);
        }


}
