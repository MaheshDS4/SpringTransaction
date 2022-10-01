package com.spring.transactional.ms.service.impl;

import com.spring.transactional.ms.entity.Employee;
import com.spring.transactional.ms.repository.EmployeeRepository;
import com.spring.transactional.ms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	//@Transactional(propagation = Propagation.SUPPORTS)
	public void insertEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	@Override
	public void deleteEmployeeById(String empid) {
		employeeRepository.deleteById(empid);
	}

}