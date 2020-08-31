package com.uday.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.uday.model.Employee;
import com.uday.repository.EmployeeRepository;
import com.uday.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeServiceTest {


	@MockBean
	private EmployeeRepository empRepository;
	
	@Autowired
	private EmployeeService empService;
	
	
	List<Employee> employeeList=new ArrayList<>();
	
	public List<Employee> before() {		
		employeeList.add(new Employee( 100L, "Uday", "HyD", "HYD","20/08/2020", "1000.00"));
		employeeList.add(new Employee(101L, "Sagar", "HyD", "HYD","20/08/2020", "1000.00"));
		return employeeList;
	}
	
	@Test
	public void testFetchAll() {
		List<Employee> empList1=before();
		Mockito.when(empRepository.findAll()).thenReturn(empList1);
		ResponseEntity<List<Employee>> employeeList=empService.fetchAll();
		assertEquals(2,	employeeList.getBody().size());
	}
	
	@Test
	public void testPostEmployee() {
		Employee e1=new Employee( 100L, "Uday", "HyD", "HYD","20/08/2020", "1000.00");
		Mockito.when(empRepository.save(ArgumentMatchers.any())).thenReturn(e1);
		assertEquals(e1, empService.postEmployee(e1));
	}
	
	@Test
	public void testGetEmpName() throws NullPointerException {
		Employee e1=new Employee( 100L, "Uday", "HyD", "HYD","20/08/2020", "1000.00");
		Optional<Employee> emp=Optional.of(e1);
		Mockito.when(empRepository.findById(Mockito.anyLong())).thenReturn(emp);
		assertEquals(e1, empService.getEmpName(10L));
	}
	
	@Test
	public void testGetEmpNameWithException() throws NullPointerException {
		assertThrows(NullPointerException.class, new Executable() {            
            @Override
            public void execute() throws Throwable {
            	empService.getEmpName(120L);
            }
        });
	}
	
	@Test
	public void testPutEmployee() {
		Employee e1=new Employee( 100L, "Uday", "HyD", "HYD","20/08/2020", "1000.00");
		Mockito.when(empRepository.save(Mockito.any(Employee.class))).thenReturn(e1);
		assertEquals(e1, empService.putEmployee(e1));
	}

}
