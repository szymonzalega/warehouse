package com.warehouse.service.dto;

import com.warehouse.domain.Company;

import java.util.List;

public class Greeting {

	private long id;
	private String content;
	private List<com.warehouse.domain.Company> companies;

	public Greeting(long id, String content, List<com.warehouse.domain.Company> companies) {
		this.id = id;
		this.content = content;
		this.companies = companies;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public List<Company> getCompanies() {
		return companies;
	}


}
