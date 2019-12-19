package com.warehouse.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @Column(name = "reviews_amount", nullable = true)
    private Integer reviewsAmount;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "company")
    private List<Review> reviews = new ArrayList<>();

    public Company() {
    }

    public Company(String id, String name, String mailAddress, String address, Integer rank, String companyDetailsUrl, List<Review> reviews, Integer reviewsAmount) {
        this.id = id;
        this.name = name;
        this.mailAddress = mailAddress;
        this.address = address;
        this.rank = rank;
        this.companyDetailsUrl = companyDetailsUrl;
        this.reviews = reviews;
        this.reviewsAmount = reviewsAmount;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Integer getReviewsAmount() {
        return reviewsAmount;
    }

    public void setReviewsAmount(Integer reviewsAmount) {
        this.reviewsAmount = reviewsAmount;
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
                ", reviewsAmount=" + reviewsAmount +
                '}';
    }
}
