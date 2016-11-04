package com.travelport.myproject.model;

import java.util.List;

public class Country extends CodedReference {

    // Extended attributes
    private String currency;

    protected int currencyPos = 2;

    public Country() {

    }

    public Country(List<String> line) {
        super(line);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getCurrencyPos() {
        return currencyPos;
    }

    public void setCurrencyPos(int currencyPos) {
        this.currencyPos = currencyPos;
    }

    @Override
    protected void buildObjectCustom(List<String> line) {
        this.currency = line.get(currencyPos);
    }

}
