package com.spring.transactional.ms.repository;

import com.spring.transactional.ms.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}