package com.example.demo.rest;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
       Employee employee = employeeService.findById(employeeId);

       if(employee == null) {
           throw new RuntimeException("Employee id not found - " + employeeId);
       }

       return employee;
    }

    @PostMapping("/employees/")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        theEmployee.setId(0);
        employeeService.save(theEmployee);
        return theEmployee;
    }

    @PostMapping("/employeeslist/")
    public List<Employee> addEmployee(@RequestBody List<Employee> employeeList) {

        for (Employee employee: employeeList) {
            employee.setId(0);
            employeeService.save(employee);
        }

        return employeeList;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {

        if(employee == null || employee.getId() == 0) {
            throw new RuntimeException("Employee not found!");
        }
        
        employeeService.save(employee);

        return employee;
    }


    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployeeById(@PathVariable int employeeId) {
        employeeService.deleteById(employeeId);
        return "OK";
    }



}
