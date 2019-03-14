package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    private Long employeeid;
    private String name;
    private Long departmentId;
    private Department department;

    @OneToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false, insertable = true, updatable = true)
    public Long getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Long employeeid) {
        this.employeeid = employeeid;
    }

    //Test

    //Test

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

