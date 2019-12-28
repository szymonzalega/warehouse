package com.warehouse.service;

import com.warehouse.domain.Company;
import com.warehouse.domain.Review;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    private final PageService pageService;

    public ReviewService(PageService pageService) {
        this.pageService = pageService;
    }

    public List<Review> getElementsWithReviews(Company company) {

        Document parsedPage = pageService.parsePage(company.getCompanyDetailsUrl());
        if(parsedPage != null) {
            Elements reviewsElement = parsePageForReviews(parsedPage);
            List<Review> reviewsList = getReviewsList(reviewsElement, company);
            return reviewsList;
        }
        return new ArrayList<>();
    }

    public Elements parsePageForReviews(Document doc) {
        return doc.select("#reviews div.review-item");
    }

    public List<Review> getReviewsList(Elements reviewsElement, Company company) {
        List<Review> reviewsList = new ArrayList<>();

        for (Element review : reviewsElement) {
            String content = review.select("div.col-12").text();
            reviewsList.add(new Review(content, company));
        }

        return reviewsList;
    }

}
