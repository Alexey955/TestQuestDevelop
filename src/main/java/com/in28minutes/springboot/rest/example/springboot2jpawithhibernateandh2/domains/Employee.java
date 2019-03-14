package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    private Long id;
    private String firstName;

    private Department department;
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}

