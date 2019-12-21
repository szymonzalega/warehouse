package com.warehouse.controller;

import java.io.IOException;
import java.util.List;

import com.warehouse.domain.Company;
import com.warehouse.domain.Review;
import com.warehouse.dto.ExtractResponse;
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

    @GetMapping("/extract")
    @CrossOrigin(origins = "http://localhost:3000")
    ExtractResponse extract(@RequestParam String searchValue) throws IOException {

        return new ExtractResponse(searchValue, companyService.extract(searchValue) -1);
    }

    @GetMapping("/transform")
    @CrossOrigin(origins = "http://localhost:3000")
    List<Company> companies(@RequestParam String searchValue) throws IOException {

        return companyService.transform(searchValue);
    }

    @GetMapping("/review")
    @CrossOrigin(origins = "http://localhost:3000")
    List<Review> reviews() throws IOException {

        return reviewRepository.findAll();
    }

   /* @GetMapping("/getReviewsForCompany")
    @CrossOrigin(origins = "http://localhost:3000")
    List<Company> companiesWithReviews() throws IOException {
        return companyService.getCompaniesWithReviews();
    }*/
}
