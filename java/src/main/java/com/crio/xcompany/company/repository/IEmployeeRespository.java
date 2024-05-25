package com.crio.xcompany.company.repository;

import com.crio.xcompany.company.Employee;

public interface IEmployeeRespository extends CRUDRepository<Employee, String> {
    public Employee findByName(String empName);
}
