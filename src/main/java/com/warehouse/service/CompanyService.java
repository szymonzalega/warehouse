package com.warehouse.service;

import com.warehouse.domain.Company;
import com.warehouse.domain.Review;
import com.warehouse.repository.CompanyRepository;
import com.warehouse.repository.ReviewRepository;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ReviewRepository reviewRepository;

    private final PageService pageService;
    private final ReviewService reviewService;

    public CompanyService(CompanyRepository companyRepository, ReviewRepository reviewRepository, PageService pageService, ReviewService reviewService) {
        this.companyRepository = companyRepository;
        this.reviewRepository = reviewRepository;
        this.pageService = pageService;
        this.reviewService = reviewService;
    }

    private String buildUrlToParse(String searchValue, Integer pageNumber) {

        String paramToSearch = searchValue.replace(" ", "_");

        StringBuilder url = new StringBuilder();
        url
                .append("https://panoramafirm.pl/")
                .append(paramToSearch)
                .append("/firmy,")
                .append(pageNumber.toString())
                .append(".html");

        return url.toString();
    }

    private Elements parsePageForCompanyElement(Document doc) {

        return doc.select("#serpContent > article > ul li.business-card");
    }

    private List<Company> getCompaniesList(Elements companyElements) {
        List<Company> companiesList = new ArrayList<>();

        for (Element company : companyElements) {
            Company companyWithData = getDataFromCompany(company);
            companiesList.add(companyWithData);
        }

        return companiesList;
    }

    private Company getDataFromCompany(Element company) {
        String id = company
                .attr("data-eid");
        String title = company
                .select("h2 a")
                .text();
        String mailAddress = company
                .select("ul.business-card-bottom-bar li:nth-child(3) a")
                .attr("href")
                .replace("mailto:", "");
        String address = company
                .select("div.row.business-card-top-bar div.business-card-top-bar-address span")
                .text();

        Elements rankElem = company.select("div.star-rating div.star-rating-active");
        Integer rank = 0;

        if (rankElem != null) {
            rank = Integer.parseInt(rankElem
                    .attr("style")
                    .split(":")[1]
                    .replace("%;", "")
                    .replace(" ", "")) / 20;
        }

        String companyDetailsUrl = company
                .attr("data-href");
        Integer reviewsAmount = Character.getNumericValue(company
                .select("div.reviews-count")
                .text()
                .charAt(1));

        System.out.println("title: " + title);

        Company companyWithData = new Company(id, title, mailAddress, address, rank, companyDetailsUrl, null, reviewsAmount);
        companyWithData.setReviews(reviewService.getElementsWithReviews(companyWithData));

        return companyWithData;
    }

    public List<Company> getAllCompany(String searchValue) {

        List<Company> x = companyRepository.findAll();


        Integer pageNumber = 1;
        Integer lastAvailablePage = pageNumber;

        List<Company> allCompaniesList = new ArrayList<>();

        do {
            System.out.println("page: " + pageNumber);
            Document doc = pageService.parsePage(buildUrlToParse(searchValue, pageNumber));
            lastAvailablePage = Integer.parseInt(pageService.getLastAvailablePage(doc));
            pageNumber++;

            Elements companyElements = parsePageForCompanyElement(doc);
            List<Company> companiesOnePage = getCompaniesList(companyElements);
            allCompaniesList.addAll(companiesOnePage);
        }
        while (lastAvailablePage > pageNumber);  //TODO change 1 to lastAvailablePage variable

        companyRepository.saveAll(allCompaniesList);

        return allCompaniesList;
    }

    /*public List<Company> getCompaniesWithReviews() {
        List<Company> companiesWithReviews = companyRepository.findByReviewsAmountGreaterThan(0);

        for(Company company: companiesWithReviews) {
            Document parsedPage = pageService.parsePage(company.getCompanyDetailsUrl());
            Elements reviewsElement = reviewService.parsePageForReviews(parsedPage);
            List<Review> reviewsList = reviewService.getReviewsList(reviewsElement, company);
            reviewRepository.saveAll(reviewsList);
        }

        return companiesWithReviews;
    }*/


}
