package com.ygd.SSM2.service;

import java.util.List;

import com.ygd.SSM2.dto.EmployeeWithDep;
import com.ygd.SSM2.entity.Employee;

public interface EmployeeService {
	
   List<EmployeeWithDep> getByPage(Integer page, Integer rows);
   
   void insertEmp(Employee employee);
   
   Employee getEmployeeById(Integer empId);
   
   void updateEmployeeById(Employee employee);
   
}
