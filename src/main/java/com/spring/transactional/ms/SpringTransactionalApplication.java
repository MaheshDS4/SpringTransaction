package com.spring.transactional.ms;

import com.spring.transactional.ms.entity.Employee;
import com.spring.transactional.ms.entity.EmployeeHealthInsurance;
import com.spring.transactional.ms.service.EmployeeService;
import com.spring.transactional.ms.service.OrganizationService;
import com.spring.transactional.ms.service.impl.InvalidInsuranceAmountException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Type of Propagation
 * REQUIRED (By Default)
 * SUPPORTS
 * NOT_SUPPORTS
 * REQUIRED_NEW
 * NEVER
 * MANDATORY
 */
@SpringBootApplication
public class SpringTransactionalApplication {

	public static void main(String[] args) throws InvalidInsuranceAmountException {
		ApplicationContext context = SpringApplication.run(SpringTransactionalApplication.class, args);
		OrganizationService organizationService = context.getBean(OrganizationService.class);
		EmployeeService employeeService = context.getBean(EmployeeService.class);

		Employee employee = new Employee();
		employee.setEmpId("1");
		employee.setEmpName("Rahul");

		EmployeeHealthInsurance employeeHealthInsurance = new EmployeeHealthInsurance();
		employeeHealthInsurance.setEmpId("1");
		employeeHealthInsurance.setHealthInsuranceSchemeName("MediClaim");
		employeeHealthInsurance.setCoverageAmount(2000);

		/**
		 * If both the service had propagation REQUIRED then inside service (employeeService) will not create the new transaction
		 * Check the logs it will have 2 insert statement in one connection only.
		 */
		organizationService.joinOrganization(employee, employeeHealthInsurance);
		//employeeService.insertEmployee(employee);

		SpringApplication.run(SpringTransactionalApplication.class, args);
	}

}
