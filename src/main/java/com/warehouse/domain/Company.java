package com.warehouse.domain;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "companyx")
public class Company {

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mail_address", nullable = true)
    private String mailAddress;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "rank", nullable = false)
    private Integer rank;

    public Company() {
    }

    public Company(String id, String name, String mailAddress, String address, Integer rank) {
        this.id = id;
        this.name = name;
        this.mailAddress = mailAddress;
        this.address = address;
        this.rank = rank;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                ", address='" + address + '\'' +
                ", rank=" + rank +
                '}';
    }
}
