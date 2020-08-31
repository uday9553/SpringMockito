package com.uday.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uday.model.Employee;
import com.uday.repository.EmployeeRepository;

@Service
public class EmployeeService {
	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;

	}

	public ResponseEntity<List<Employee>> fetchAll() {
		System.out.println("hit fetchAll()");
		List<Employee> empList=employeeRepository.findAll();
		return ResponseEntity.ok(empList);
		 

	}

	public Employee postEmployee(Employee employee) {
		System.out.println("hit putEmployee()");
		return employeeRepository.save(employee);
	}

	public Employee getEmpName(Long id) {
		if(id==120L) {
			throw new NullPointerException();
		}
		System.out.println("hit getEmpName()");
		Optional<Employee> employee = employeeRepository.findById(id);
		Employee emp = employee.get();
		return emp;

	}

	public Employee putEmployee(Employee employee) {
		System.out.println("hit putEmployee()");
		return employeeRepository.save(employee);
	}

	public void remove(Employee employee) {
		// log.info("Executing removed all customers", customer);
		employeeRepository.delete(employee);
		
	}

	public void clearAllCaches() {
		System.out.println("Cleared customer cache");
	}

}
