package com.spring.transactional.ms.service.impl;

import com.spring.transactional.ms.repository.EmployeeHealthInsuranceRepository;
import com.spring.transactional.ms.entity.EmployeeHealthInsurance;
import com.spring.transactional.ms.service.HealthInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HealthInsuranceServiceImpl implements HealthInsuranceService {

	@Autowired
	EmployeeHealthInsuranceRepository healthInsuranceDaoRepository;

	@Override
	public void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance)
			throws InvalidInsuranceAmountException {
		/*if (employeeHealthInsurance.getCoverageAmount() <= 0) {
			throw new InvalidInsuranceAmountException("Coverage Amount Should not be negative");
		}*/
		healthInsuranceDaoRepository.save(employeeHealthInsurance);
	}

	@Override
	public void deleteEmployeeHealthInsuranceById(String empid) {
		healthInsuranceDaoRepository.deleteById(empid);
	}

}