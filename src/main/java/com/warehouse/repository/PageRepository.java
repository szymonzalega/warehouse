package com.warehouse.repository;

import com.warehouse.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends CrudRepository<Page, Long> {

    @Query("select p from Page p where p.searchValue = :searchValue")
    List<Page> findBySearchValue(@Param("searchValue") String searchValue);

    void deleteAllBySearchValue(String searchValue);

    List<Page> findAll();

}
