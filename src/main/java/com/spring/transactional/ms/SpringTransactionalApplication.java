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
		 * -----REQUIRED-----
		 * If both the service had propagation REQUIRED then inside service (employeeService) will not create the new transaction
		 * Check the logs it will have 2 insert statement in one connection only.
		 * -----SUPPORTS-----
		 * if the insertEmployee() method is called directly it does not create own new transaction
		 * if the calling method has transaction then method makes use of the existing transaction else it will not create a new transaction
		 * -----NOT_SUPPORTS-----
		 * if the insertEmployee() method is called directly it does not create own new transaction
		 * if the calling method has transaction then method does not make use of the existing transaction neither does it create its own transaction. it run without transaction
		 * if calling method does not has transaction then method will not create a new transaction and run without transaction
		 *  -----REQUIRED_NEW-----
		 *  if the insertEmployee() method is called directly it creates its own new transaction
		 *  if the calling service method has a transaction then method does not make use of the existing transaction but creates its own transaction
		 *  if the calling service method does not have a transaction the method will create a new transaction
		 *  -----NEVER-----
		 *  if insertEmployee() method is called directly it creates it does not create a new transaction
		 *  if calling service method has a transaction then method throws an exception
		 *  if the calling service method does not have a transaction the method will not create a new one and run without transaction
		 *  -----MANDATORY-----
		 *  if insertEmployee() method is called directly it will throw an exception
		 *  if the calling service method has a transaction then method makes use of existing transaction
		 *  if the calling service method does not have a transaction the method will throw an exception
		 */
		organizationService.joinOrganization(employee, employeeHealthInsurance);
		//employeeService.insertEmployee(employee);

		SpringApplication.run(SpringTransactionalApplication.class, args);
	}

}

/**
 * Propagation			Behaviour
 * REQUIRED			Always executes in a transaction. If there is any existing transaction it uses it. If none exists then only a new one is created
 * SUPPORTS			It may or may not run in a transaction. If current transaction exists then it is supported. If none exists then gets executed without transaction.
 * NOT_SUPPORTED	Always executes without a transaction. If there is any existing transaction it gets suspended
 * REQUIRES_NEW		Always executes in a new transaction. If there is any existing transaction it gets suspended
 * NEVER			Always executes without any transaction. It throws an exception if there is an existing transaction
 * MANDATORY		Always executes in a transaction. If there is any existing transaction it is used. If there is no existing transaction it will throw an exception.
 */
