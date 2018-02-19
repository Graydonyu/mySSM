package com.ygd.SSM2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
public class Employee implements Serializable{
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
}