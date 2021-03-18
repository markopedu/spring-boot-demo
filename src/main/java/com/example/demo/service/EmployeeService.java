package com.example.demo.service;

import com.example.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAll();
    public Employee findById(int theId);
    public void save(Employee employee);
    public void deleteById(int theId);
}
