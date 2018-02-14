package com.ygd.SSM2.entity;

import javax.persistence.*;

public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Sid")
    private String sid;

    @Column(name = "Sname")
    private String sname;

    @Column(name = "Sdepartment")
    private String sdepartment;

    @Column(name = "Sage")
    private Integer sage;

    @Column(name = "Ssex")
    private String ssex;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * @param sid
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * @return Sname
     */
    public String getSname() {
        return sname;
    }

    /**
     * @param sname
     */
    public void setSname(String sname) {
        this.sname = sname;
    }

    /**
     * @return Sdepartment
     */
    public String getSdepartment() {
        return sdepartment;
    }

    /**
     * @param sdepartment
     */
    public void setSdepartment(String sdepartment) {
        this.sdepartment = sdepartment;
    }

    /**
     * @return Sage
     */
    public Integer getSage() {
        return sage;
    }

    /**
     * @param sage
     */
    public void setSage(Integer sage) {
        this.sage = sage;
    }

    /**
     * @return Ssex
     */
    public String getSsex() {
        return ssex;
    }

    /**
     * @param ssex
     */
    public void setSsex(String ssex) {
        this.ssex = ssex;
    }
}