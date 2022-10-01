package com.spring.transactional.ms.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class EmployeeHealthInsurance {
	@Id
	private String empId;
	private String healthInsuranceSchemeName;
	private int coverageAmount;
}
