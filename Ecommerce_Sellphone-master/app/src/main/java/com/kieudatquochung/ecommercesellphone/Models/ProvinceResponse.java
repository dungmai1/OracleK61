package com.kieudatquochung.ecommercesellphone.Models;

import java.util.List;

public class ProvinceResponse {
    private List<Province> results;

    public ProvinceResponse(List<Province> results) {
        this.results = results;
    }

    public List<Province> getResults() {
        return results;
    }

    public void setResults(List<Province> results) {
        this.results = results;
    }
}
