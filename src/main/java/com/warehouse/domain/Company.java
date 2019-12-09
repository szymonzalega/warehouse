package com.warehouse.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "company_id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mail_address", nullable = true)
    private String mailAddress;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "rank", nullable = false)
    private Integer rank;

    @Column(name = "company_details_url", nullable = true)
    private String companyDetailsUrl;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "company")
    private Set<Review> reviews = new HashSet<>();

    public Company() {
    }

    public Company(String id, String name, String mailAddress, String address, Integer rank, String companyDetailsUrl, Set<Review> reviews) {
        this.id = id;
        this.name = name;
        this.mailAddress = mailAddress;
        this.address = address;
        this.rank = rank;
        this.companyDetailsUrl = companyDetailsUrl;
        this.reviews = reviews;
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

    public String getCompanyDetailsUrl() {
        return companyDetailsUrl;
    }

    public void setCompanyDetailsUrl(String companyDetailsUrl) {
        this.companyDetailsUrl = companyDetailsUrl;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                ", address='" + address + '\'' +
                ", rank=" + rank +
                ", companyDetailsUrl='" + companyDetailsUrl + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}
