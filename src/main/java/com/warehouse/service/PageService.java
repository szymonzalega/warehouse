package com.warehouse.service;

import com.warehouse.domain.Company;
import com.warehouse.domain.Page;
import com.warehouse.repository.PageRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Service
public class PageService {

    //    private final CompanyService companyService;
    private final PageRepository pageRepository;

    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public Document parsePage(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            return doc;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Document parsePageFromHtml(String page) {
        Document doc = Jsoup.parse(page);
        return doc;
    }


    public String getLastAvailablePage(Document doc) {
        Elements paginationElements = doc.select("#pagination a");
        paginationElements.remove(paginationElements.size() - 1);
        return paginationElements.last().text();
    }

}
