package com.ygd.SSM2.entity;

import java.io.Serializable;

import javax.persistence.*;

public class Employee implements Serializable{
    /**
	 * 
	 */
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

    /**
     * @return emp_id
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * @param empId
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

 
    public String getEmpName() {
        return empName;
    }

 
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {
        this.empSex = empSex;
    }


    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    /**
     * @return dep_id
     */
    public Integer getDepId() {
        return depId;
    }

    /**
     * @param depId
     */
    public void setDepId(Integer depId) {
        this.depId = depId;
    }
}