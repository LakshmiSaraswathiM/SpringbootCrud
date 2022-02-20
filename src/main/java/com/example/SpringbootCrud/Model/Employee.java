package com.example.SpringbootCrud.Model;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

//Assignment case 1: Add New Employee to System (Emp ID, Name, Salary, Department)
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId;


    @Column
    @NotNull
    private long salary;

    @NotNull
    @Size(min=2,message="empName should have atleast two characters")
    @Column
    private String empName;

    @NotNull
    @Size(min=2,message="empName should have atleast two characters")
    @Column
    private String dept;


    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
