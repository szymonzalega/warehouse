package com.warehouse.domain;

import javax.persistence.*;

@Entity
@Table(name = "companyx")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mail_address", nullable = false)
    private String mailAddress;

    public Company() {
    }

    public Company(String name, String mailAddress) {
        this.name = name;
        this.mailAddress = mailAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                '}';
    }
}
