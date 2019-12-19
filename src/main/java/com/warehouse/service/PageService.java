package com.warehouse.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PageService {

    public Document parsePage(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            return doc;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getLastAvailablePage(Document doc) {
        Elements paginationElements = doc.select("#pagination a");
        paginationElements.remove(paginationElements.size() - 1);
        return paginationElements.last().text();
    }

}
