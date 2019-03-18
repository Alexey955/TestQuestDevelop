package com.alex.testQuest.dto;

import org.hibernate.validator.constraints.Length;

public class Person {
    private Long id;
    @Length(min = 5, message = "Less than 5 symbols.")
    @Length(max = 15, message = "More than 15 symbols.")
    private String firstName;

    @Length(min = 5, message = "Less than 5 symbols.")
    @Length(max = 15, message = "More than 15 symbols.")
    private String lastName;

    @Length(min = 5, message = "Less than 5 symbols.")
    @Length(max = 15, message = "More than 15 symbols.")
    private String departmentName;

    private String role;

    public Person() {
    }

    public Person(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
