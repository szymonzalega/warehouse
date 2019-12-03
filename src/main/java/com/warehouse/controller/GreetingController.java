package com.warehouse.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.warehouse.service.CompanyService;
import com.warehouse.service.dto.Company;
import com.warehouse.service.dto.Greeting;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final CompanyService companyService;

    public GreetingController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/greeting")
    @CrossOrigin(origins = "http://localhost:3000")
    Greeting greeting(@RequestParam String searchValue) throws IOException {


        List<Company> companies = companyService.getCompanyList(searchValue);

        return new Greeting(counter.incrementAndGet(), "jakis title", companies);
    }
}
