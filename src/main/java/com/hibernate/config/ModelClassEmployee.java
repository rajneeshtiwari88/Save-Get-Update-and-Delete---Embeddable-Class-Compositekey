package com.hibernate.config;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table
@Entity(name = "employee")
public class ModelClassEmployee {

	public static final String GET_EMPLOYEE_LIST = "emp.getListofEmployee";

	@Id
	@EmbeddedId
	private EmployeeId id;

	@Column(name = "employee_age")
	private int age;

	@Column(name = "employee_salary")
	private BigDecimal salary;

	public EmployeeId getId() {
		return id;
	}

	public void setId(EmployeeId id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
