package com.crio.xcompany.company.services;

import com.crio.xcompany.company.Employee;
import com.crio.xcompany.company.repository.EmployeeRepository;
import com.crio.xcompany.company.repository.IEmployeeRespository;

public class CompanyService {

    private IEmployeeRespository empRepo = new EmployeeRepository();
    
    public Employee storeEmployee(Employee emp) {
        return empRepo.save(emp);
    }

    public Employee fetchEmployee(String empName) {
        return empRepo.findByName(empName);
    }

    public Employee removeEmployee(String empName) {
        return empRepo.delete(empName);
    }

    public Employee updateEmployee(Employee emp) {
        return empRepo.update(emp);
    }
}
