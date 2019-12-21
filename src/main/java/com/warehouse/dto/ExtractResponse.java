package com.warehouse.dto;

public class ExtractResponse {

    private String searchValue;
    private Integer pageAmount;

    public ExtractResponse(String searchValue, Integer pageAmount) {
        this.searchValue = searchValue;
        this.pageAmount = pageAmount;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Integer getPageAmount() {
        return pageAmount;
    }

    public void setPageAmount(Integer pageAmount) {
        this.pageAmount = pageAmount;
    }
}
