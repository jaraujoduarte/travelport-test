package com.travelport.myproject.model;

import java.util.List;

public class PricingSolutionDto implements Comparable<PricingSolutionDto> {

    private String price;
    private List<AirSegmentDto> airSegmentList;

    @Override
    public int compareTo(PricingSolutionDto o) {
        return price.compareTo((((PricingSolutionDto) o).getPrice()));
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<AirSegmentDto> getAirSegmentList() {
        return airSegmentList;
    }

    public void setAirSegmentList(List<AirSegmentDto> airSegmentList) {
        this.airSegmentList = airSegmentList;
    }

}
