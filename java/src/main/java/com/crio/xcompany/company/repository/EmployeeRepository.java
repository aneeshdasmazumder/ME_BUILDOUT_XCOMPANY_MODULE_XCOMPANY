package com.crio.xcompany.company.repository;

import java.util.HashMap;
import java.util.Map;
import com.crio.xcompany.company.Employee;
import com.crio.xcompany.company.exception.EmployeeNotFoundException;

public class EmployeeRepository implements IEmployeeRespository {

    private static Map<String, Employee> empMap = new HashMap<>();

    @Override
    public Employee save(Employee entity) {
        if(entity == null)
            return null;
            // throw new EmployeeNotFoundException("Employee Not Found To Store In DB!!");
        
        empMap.put(entity.getName(), entity);
        Employee emp = empMap.get(entity.getName());
        // System.out.println("save : "+emp.toString());
        return emp;
    }

    @Override
    public Employee findByName(String empName) {
        // System.out.println("findByName : "+empName);
        if(empMap.containsKey(empName)) {
            Employee emp = empMap.get(empName);
            return emp;
        }
        // throw new EmployeeNotFoundException("Employee Not Found in DB!!" + empName);

        return null;
    }

    @Override
    public Employee delete(String entity) {
        if(entity == null || !empMap.containsKey(entity))
            return null;
            // throw new EmployeeNotFoundException("Employee Not Found To Delete From DB!!");
        
        Employee emp = empMap.get(entity);
        empMap.remove(entity);
        return emp;
    }

    @Override
    public Employee update(Employee entity) {
        Employee e = findByName(entity.getName());
        
        if(e != null) {
            empMap.put(e.getName(), entity);
            return empMap.get(e.getName());
        }
        return null;
        // throw new EmployeeNotFoundException("Employee Not Found To update in DB!!");
    }

}
