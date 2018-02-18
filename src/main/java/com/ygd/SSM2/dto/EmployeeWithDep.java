package com.ygd.SSM2.dto;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
public class EmployeeWithDep implements Serializable{
    /**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "emp_sex")
    private String empSex;


    @Column(name = "emp_email")
    private String empEmail;

    @Column(name = "dep_id")
    private Integer depId;
    
    @Transient
    private String depName;
}