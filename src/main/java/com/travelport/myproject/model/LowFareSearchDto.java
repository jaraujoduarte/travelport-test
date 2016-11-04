package com.travelport.myproject.model;

import java.util.List;

public class LowFareSearchDto {

    private City origin;
    private City destination;
    private List<PricingSolutionDto> pricingSolutionList;

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public List<PricingSolutionDto> getPricingSolutionList() {
        return pricingSolutionList;
    }

    public void setPricingSolutionList(List<PricingSolutionDto> pricingSolutionList) {
        this.pricingSolutionList = pricingSolutionList;
    }

}
