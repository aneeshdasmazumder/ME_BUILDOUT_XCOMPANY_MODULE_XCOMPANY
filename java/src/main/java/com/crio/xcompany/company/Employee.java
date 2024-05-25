package com.crio.xcompany.company;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private Gender gender;
    private List<Employee> directReports;
    private Employee manager;

    public Employee(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        directReports = new ArrayList<>();
        manager = null;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Employee getManager() {
        return manager;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    // TODO: CRIO_TASK_MODULE_XCOMPANY
    // Please define all the methods required here as mentioned in the XCompany BuildOut Milestone before implementing the logic.
    // This will ensure that the project can be compiled successfully.
    public void assignManager(Employee manager) {
        this.manager = manager;
    }

    public void assignDirectReports(Employee employee) {
        this.directReports.add(employee);
    }

    public List<Employee> getTeamMates() {
        return directReports;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", gender=" + gender + "]";
    }   
}
