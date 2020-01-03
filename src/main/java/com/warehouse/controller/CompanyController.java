package com.warehouse.controller;

import java.io.IOException;
import java.util.List;

import com.warehouse.domain.Company;
import com.warehouse.domain.Review;
import com.warehouse.dto.SearchResponse;
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
    SearchResponse extract(@RequestParam String searchValue, @RequestParam Integer pageAmount) throws IOException {

        return new SearchResponse(searchValue, companyService.extract(searchValue, pageAmount) -1);
    }

    @GetMapping("/transform")
    @CrossOrigin(origins = "http://localhost:3000")
    List<Company> companies(@RequestParam String searchValue, @RequestParam Boolean areReviews) throws IOException {

        return companyService.transform(searchValue, areReviews);
    }

    @GetMapping("/review")
    @CrossOrigin(origins = "http://localhost:3000")
    List<Review> reviews() throws IOException {

        return reviewRepository.findAll();
    }

    @GetMapping("/pageAmount")
    @CrossOrigin(origins = "http://localhost:3000")
    SearchResponse getPageAmount(@RequestParam String searchValue) throws IOException {

        return new SearchResponse(searchValue, companyService.getPageAmount(searchValue));
    }

    @GetMapping("/allProcess")
    @CrossOrigin(origins = "http://localhost:3000")
    List<Company> allProcess(@RequestParam String searchValue) throws IOException {

        return companyService.allProcess(searchValue, false);
    }
}
