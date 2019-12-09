package com.warehouse.service;

import com.warehouse.domain.Company;
import com.warehouse.repository.CompanyRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    private Elements parsePageForCompanyElement(Document doc) {

        Elements elements = new Elements();

        elements = doc.select("#serpContent > article > ul li.business-card");

        return elements;
    }

    private String getLastAvailablePage(Document doc) {
        Elements paginationElements = doc.select("#pagination a");
        paginationElements.remove(paginationElements.size() - 1);
        return paginationElements.last().text();
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

    private List<Company> getCompaniesList(Elements companyElements) {
        List<Company> companiesList = new ArrayList<>();

        for (Element company : companyElements) {
            String id = company.attr("data-eid");
            String title = company.select("h2 a").text();
            String mailAddress = company.select("ul.business-card-bottom-bar li:nth-child(3) a").attr("href").replace("mailto:", "");
            String address = company.select("div.row.business-card-top-bar div.business-card-top-bar-address span").text();
            Integer rank = Integer.parseInt(company.select("div.row.business-card-top-bar div.star-rating.d-inline-block div.star-rating-active").attr("style").split(":")[1].replace("%;", "").replace(" ", "")) / 20;
            String companyDetailsUrl = company.attr("data-href");
            companiesList.add(new Company(id, title, mailAddress, address, rank, companyDetailsUrl, null));
        }

        return companiesList;
    }

    private Document parsePage(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            return doc;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Company> getCompanyList(String searchValue) {

        List<Company> x = companyRepository.findAll();


        Integer pageNumber = 1;
        Integer lastAvailablePage = pageNumber;

        List<Company> allCompaniesList = new ArrayList<>();



        do{
            Document doc = parsePage(buildUrlToParse(searchValue, pageNumber));
            lastAvailablePage = Integer.parseInt(getLastAvailablePage(doc));
            pageNumber++;

            Elements companyElements = parsePageForCompanyElement(doc);
            List<Company> companiesOnePage = getCompaniesList(companyElements);
            allCompaniesList.addAll(companiesOnePage);
        }
        while(1 > pageNumber);

        /*com.warehouse.domain.Company y = new com.warehouse.domain.Company("name", "mailaddress");
        companyRepository.save(y);*/

        companyRepository.saveAll(allCompaniesList);

        return allCompaniesList;
    }
}
