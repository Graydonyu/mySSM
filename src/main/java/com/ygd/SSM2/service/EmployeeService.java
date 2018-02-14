package com.ygd.SSM2.service;

import java.util.List;

import com.ygd.SSM2.entity.Employee;

public interface EmployeeService {
	
   List<Employee> getByPage(Integer page, Integer rows);
   
}
