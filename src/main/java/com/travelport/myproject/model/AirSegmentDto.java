package com.travelport.myproject.model;

public class AirSegmentDto implements Comparable<AirSegmentDto> {

    private int group;
    private Airline carrier;
    private Airplane equipement;
    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private String flightTime;

    @Override
    public int compareTo(AirSegmentDto o) {
        return group - o.getGroup();
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public Airline getCarrier() {
        return carrier;
    }

    public void setCarrier(Airline carrier) {
        this.carrier = carrier;
    }

    public Airplane getEquipement() {
        return equipement;
    }

    public void setEquipement(Airplane equipement) {
        this.equipement = equipement;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

}
