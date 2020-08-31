package com.uday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uday.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	//Optional<Employee> findById(long id);
	List<Employee> findAll(); 
	//List<Employee> findByEmpname(@Param(value="empname")String empname);
}
