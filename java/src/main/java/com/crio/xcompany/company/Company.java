package com.crio.xcompany.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.xcompany.company.exception.EmployeeNotFoundException;
import com.crio.xcompany.company.services.CompanyService;

public class Company{
    private String companyName;
    private Employee founder;
    private Map<String,Employee> employeeBook;
    
    private CompanyService cService;

    private Company(String companyName, Employee founder) {
        this.companyName = companyName;
        this.founder = founder;
        employeeBook = new HashMap<String,Employee>();
        employeeBook.put(founder.getName(), founder);
        this.cService = new CompanyService();
    }
    

    public static Company create(String companyName, Employee founder){
        return new Company(companyName,founder);
    } 


    public String getCompanyName() {
        return companyName;
    }

    // TODO: CRIO_TASK_MODULE_XCOMPANY
    // Please define all the methods required here as mentioned in the XCompany BuildOut Milestone for each functionality before implementing the logic.
    // This will ensure that the project can be compiled successfully.

    // Method to register employee
    public void registerEmployee(String employeeName, Gender gender) {
        if(employeeName != null && employeeName != "") {
            Employee emp = cService.storeEmployee(new Employee(employeeName, gender));

            if(emp != null) {
                // System.out.println("Employee Registration Succeeded!!");
                return;
            }
        }
    }

    // Method to fetch Employee
    public Employee getEmployee(String employeeName) {
        if(employeeName != null && employeeName != "") {
            Employee emp = cService.fetchEmployee(employeeName);
            // if(emp == null)
            //     throw new EmployeeNotFoundException("Employee Not Found In DB!!");

            return emp;
        }
        return null;
        // throw new EmployeeNotFoundException("Employee is Invalid!!");
    }

    // Method to delete Employee
    public void deleteEmployee(String employeeName) {
        if(employeeName != null && employeeName != "") {
            cService.removeEmployee(employeeName);
        }
    }

    // Method to Assign Manager
    public void assignManager(String employeeName, String managerName) {
        Employee emp = getEmployee(employeeName);
        Employee mEmp = getEmployee(managerName);
        emp.assignManager(mEmp);
        mEmp.assignDirectReports(emp);
        cService.updateEmployee(mEmp);
        cService.updateEmployee(emp);
    }

    // Method to get Direct Reports
    public List<Employee> getDirectReports(String managerName) {
        Employee emp = getEmployee(managerName);
        // System.out.println("getDirectReports: "+emp.getDirectReports() + " " + managerName);
        return emp.getDirectReports();
    }

    // Method to get Team Mates
    public List<Employee> getTeamMates(String employeeName) {
        Employee emp = getEmployee(employeeName);
        List<Employee> listOfTeamMates = new ArrayList<>();
        List<Employee> teamEmp = new ArrayList<>();
        if(emp.getManager() == null) {
            return new ArrayList<>();
            // throw new EmployeeNotFoundException("No TeamMates found !!");
        }
        // System.out.println(emp.getManager().getName());
        // System.out.println("EmployeeName: "+emp.toString()+ " Team: "+getEmployee(emp.getManager().getName()).getTeamMates());
        // listOfTeamMates.add(emp.getManager());
        // listOfTeamMates.add(getEmployee(emp.getManager().getName()).getTeamMates());
        listOfTeamMates.add(emp.getManager());
        teamEmp = getEmployee(emp.getManager().getName()).getTeamMates();
        for(Employee e : teamEmp) {
            listOfTeamMates.add(e);
        }
        // System.out.println("listOfTeamMates: "+listOfTeamMates);
        return listOfTeamMates;
    }

    // Method to get Hierarchy
    public List<List<Employee>> getEmployeeHierarchy(String managerName) {
        List<List<Employee>> hierarchy = new ArrayList<>();
        // System.out.println("ManagerName: "+managerName);
        Employee mEmp = getEmployee(managerName);
        List<Employee> emp = new ArrayList<>();
        emp.add(mEmp);
        hierarchy.add(emp);
        // // hierarchy.add(mEmp.getDirectReports());
        // System.out.println("Manager: "+hierarchy);
        // System.out.println("Team: "+hierarchy.get(0));
        // System.out.println("TeamMates: "+mEmp.getDirectReports());
        getHeirarchy(mEmp, hierarchy, mEmp.getDirectReports());
        // for (List<Employee> empL : hierarchy) {
        //     System.out.println("Team: "+empL);
        //     hierarchy = getHeirarchy(mEmp, hierarchy, mEmp.getDirectReports());
        // }
        
        // System.out.println("Heirarchy : "+hierarchy);
        return hierarchy;
    }

    public void getHeirarchy(Employee mng, List<List<Employee>> hierarchy, List<Employee> lemp) {
        if(mng.getDirectReports().size() == 0) {
            // System.out.println("Null : "+lemp);
            return;
        }
            
        // System.out.println("TeamMates: "+lemp);
        hierarchy.add(lemp);
        for(Employee e : lemp) {
            // System.out.println("Employee: "+e.toString()+" Team: "+e.getDirectReports());
            
            getHeirarchy(e, hierarchy, e.getDirectReports());
        }
    }
    
}
