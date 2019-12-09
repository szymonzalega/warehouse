package com.warehouse.controller;

import java.io.IOException;
import java.util.List;

import com.warehouse.domain.Company;
import com.warehouse.domain.Review;
import com.warehouse.repository.ReviewRepository;
import com.warehouse.service.CompanyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CompanyController {

    private final CompanyService companyService;
    private final ReviewRepository reviewRepository;

    public CompanyController(CompanyService companyService, ReviewRepository reviewRepository) {
        this.companyService = companyService;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/greeting")
    @CrossOrigin(origins = "http://localhost:3000")
    List<Company> companies(@RequestParam String searchValue) throws IOException {

        return companyService.getCompanyList(searchValue);
    }

    @GetMapping("/review")
    @CrossOrigin(origins = "http://localhost:3000")
    List<Review> reviews() throws IOException {

        return reviewRepository.findAll();
    }
}
