package com.travelport.myproject.model;

import java.util.List;

public class City extends CodedReference {

    // Extended attributes
    private String country;

    protected int countryPos = 4;

    public City() {

    }

    public City(List<String> line) {
        super(line);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    protected void overrideVars() {
        setNamePos(2);
    }

    @Override
    protected void buildObjectCustom(List<String> line) {
        this.country = line.get(countryPos);
    }

}
