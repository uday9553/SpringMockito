package com.uday.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Table(name="eemployee")
@Data
public class Employee {
	
	public Employee() {
		
	}
	@Id
	@Column(name="empid")
	@NotNull
	private Long empid;
	
	@Column(name="empname")
	@NotNull
	@Size(max = 5)
	private String empname;
	
	/**
	 * @param empid
	 * @param empname
	 * @param locationcode
	 * @param location
	 * @param date
	 * @param salary
	 */
	public Employee(Long empid, String empname, String locationcode, String location, String date, String salary) {
		this.empid = empid;
		this.empname = empname;
		this.locationcode = locationcode;
		this.location = location;
		this.date = date;
		this.salary = salary;
	}

	@Column(name="locationcode")
	private String locationcode;
  
	@Column(name="location")
	private String location;
	
	@Column(name="date")
	private String date;
	
	@Column(name="salary")
	private String salary;
  

 
}
