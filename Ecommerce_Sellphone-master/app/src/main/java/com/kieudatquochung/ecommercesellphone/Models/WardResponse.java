package com.kieudatquochung.ecommercesellphone.Models;

import java.util.List;

public class WardResponse {
    private List<Ward> results;

    public WardResponse(List<Ward> results) {
        this.results = results;
    }

    public List<Ward> getResults() {
        return results;
    }

    public void setResults(List<Ward> results) {
        this.results = results;
    }
}
