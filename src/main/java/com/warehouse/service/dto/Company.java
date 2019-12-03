package com.warehouse.service.dto;

public class Company {
    private String name;
    private String mailAddress;

    public Company(String name, String mailAddress) {
        this.name = name;
        this.mailAddress = mailAddress;
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
}
