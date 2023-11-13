package com.kieudatquochung.ecommercesellphone.Models;

import java.util.List;

public class DistrictResponse {
    private List<District> results;

    public DistrictResponse(List<District> results) {
        this.results = results;
    }

    public List<District> getResults() {
        return results;
    }

    public void setResults(List<District> results) {
        this.results = results;
    }
}
