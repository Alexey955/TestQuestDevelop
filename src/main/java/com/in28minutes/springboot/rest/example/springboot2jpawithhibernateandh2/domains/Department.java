package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {

    private Long departmentId;
    private String departmentName;
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "department")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        employee.setDepartment(this);
        this.employee = employee;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", nullable = false, insertable = true, updatable = true)
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    //Test

    //Test


    public Department() {
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
