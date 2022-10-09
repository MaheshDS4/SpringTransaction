package com.spring.transactional.ms.service.impl;

import com.spring.transactional.ms.entity.Employee;
import com.spring.transactional.ms.entity.EmployeeHealthInsurance;
import com.spring.transactional.ms.service.EmployeeService;
import com.spring.transactional.ms.service.HealthInsuranceService;
import com.spring.transactional.ms.service.OrganizationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganzationServiceImpl implements OrganizationService {
	private final EmployeeService employeeService;

	private final HealthInsuranceService healthInsuranceService;

	public OrganzationServiceImpl(EmployeeService employeeService, HealthInsuranceService healthInsuranceService) {
		this.employeeService = employeeService;
		this.healthInsuranceService = healthInsuranceService;
	}

	@Override
	//@Transactional(rollbackFor = InvalidInsuranceAmountException.class)
	public void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) throws InvalidInsuranceAmountException {
		employeeService.insertEmployee(employee);
		try {
			healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);
		} catch (InvalidInsuranceAmountException e) {
			throw new InvalidInsuranceAmountException("Exception is thrown");
		}
	}

	@Override
	public void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		employeeService.deleteEmployeeById(employee.getEmpId());
		healthInsuranceService.deleteEmployeeHealthInsuranceById(employeeHealthInsurance.getEmpId());
	}
}