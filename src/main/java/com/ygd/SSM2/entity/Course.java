package com.ygd.SSM2.entity;

import javax.persistence.*;

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Cid")
    private String cid;

    @Column(name = "Cname")
    private String cname;

    @Column(name = "Ccredit")
    private String ccredit;

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
     * @return Cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * @param cid
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * @return Cname
     */
    public String getCname() {
        return cname;
    }

    /**
     * @param cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     * @return Ccredit
     */
    public String getCcredit() {
        return ccredit;
    }

    /**
     * @param ccredit
     */
    public void setCcredit(String ccredit) {
        this.ccredit = ccredit;
    }
}