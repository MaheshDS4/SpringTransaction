package com.spring.transactional.ms.service;

import com.spring.transactional.ms.entity.EmployeeHealthInsurance;
import com.spring.transactional.ms.service.impl.InvalidInsuranceAmountException;

public interface HealthInsuranceService {
	void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance) throws InvalidInsuranceAmountException;

	void deleteEmployeeHealthInsuranceById(String empid);
}