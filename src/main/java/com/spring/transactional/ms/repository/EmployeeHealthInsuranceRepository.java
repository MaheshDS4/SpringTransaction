package com.spring.transactional.ms.repository;

import com.spring.transactional.ms.entity.EmployeeHealthInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeHealthInsuranceRepository extends JpaRepository<EmployeeHealthInsurance, String > {
}