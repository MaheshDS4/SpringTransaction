package com.spring.transactional.ms.service;

import com.spring.transactional.ms.entity.Employee;

public interface EmployeeService {
    void insertEmployee(Employee emp);
    void deleteEmployeeById(String empid);
}
