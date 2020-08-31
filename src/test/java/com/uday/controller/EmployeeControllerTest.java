package com.uday.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uday.controller.EmployeeController;
import com.uday.model.Employee;
import com.uday.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

	@MockBean
	private EmployeeService employeeService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	List<Employee> employeeList = new ArrayList<>();

	public List<Employee> before() {
		employeeList.add(new Employee(100L, "Uday", "HyD", "HYD", "20/08/2020", "1000.00"));
		employeeList.add(new Employee(101L, "Sagar", "HyD", "HYD", "20/08/2020", "1000.00"));
		return employeeList;
	}

	@Test
	public void testGetEmployees() throws Exception {
		Mockito.when(employeeService.fetchAll()).thenReturn(ResponseEntity.ok(before()));
		this.mockMvc.perform(get("/getAll")).andExpect(status().isOk());

	}

	@Test
	public void testGetEmployee() throws Exception {
		Employee e1 = new Employee(100L, "Uday", "HyD", "HYD", "20/08/2020", "1000.00");
		Mockito.when(employeeService.getEmpName(110L)).thenReturn(e1);
		this.mockMvc.perform(get("/getempame/1")).andExpect(status().isOk());

	}
//	@Test(expected = NullPointerException.class)
//	public void testGetEmployeeWithinputException() throws Exception {
//		Employee e1 = new Employee(120L, "Uday", "HyD", "HYD", "20/08/2020", "1000.00");
//		Mockito.when(employeeService.getEmpName(110L)).thenReturn(e1);
//		this.mockMvc.perform(get("/getempame/1")).andExpect(status().isOk());
//
//	}

	@Test
	public void testGetEmployeeWithConstraintException() throws Exception {
		Employee e1 = new Employee(100L, "Udaydsfs", "HyD", "HYD", "20/08/2020", "1000.00");
		Mockito.when(employeeService.getEmpName(Mockito.anyLong())).thenReturn(e1);
		this.mockMvc.perform(post("/emp").content(objectMapper.writeValueAsString(e1)))
				.andExpect(status().is4xxClientError());

	}
}
