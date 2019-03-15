package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department {

    private Long id;
    private String departmentName;
    private Long employeeId;

    private Set<Employee> employees = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public Department() {
    }

    public Department(Long id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        employee.setDepartment(this);
        this.employees.add(employee);
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    private Long chiefId;
    private Set<Chief> chiefs = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    public Set<Chief> getChiefs() {
        return this.chiefs;
    }

    public void setChiefs(Set<Chief> chiefs) {
        this.chiefs = chiefs;
    }

    public void addChief(Chief chief) {
        chief.setDepartment(this);
        this.chiefs.add(chief);
    }
}
