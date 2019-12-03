package com.warehouse.service.dto;

import java.util.List;

public class Greeting {

	private long id;
	private String content;
	private List<Company> companies;

	public Greeting(long id, String content, List<Company> companies) {
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
