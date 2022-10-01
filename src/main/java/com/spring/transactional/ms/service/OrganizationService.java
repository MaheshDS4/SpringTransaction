package com.spring.transactional.ms.service;

import com.spring.transactional.ms.entity.Employee;
import com.spring.transactional.ms.entity.EmployeeHealthInsurance;
import com.spring.transactional.ms.service.impl.InvalidInsuranceAmountException;

public interface OrganizationService {
	
	void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) throws InvalidInsuranceAmountException;
	void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance);
}
