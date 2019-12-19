package com.warehouse.repository;

import com.warehouse.domain.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    List<Company> findAll();

    @Query("select c from Company c where c.reviewsAmount > :minAmount")
    List<Company> findByReviewsAmountGreaterThan(@Param("minAmount") Integer minAmount);

    Company findById(String id);

}
