package com.travelport.myproject.model;

public class AirSegmentDto implements Comparable<AirSegmentDto> {

    private int group;
    private City origin;
    private City destination;
    private Airline carrier;
    private Airplane equipement;
    private String flightNumber;
    private String departureDate;
    private String arrivalDate;
    private String departureTime;
    private String arrivalTime;
    private String flightTime;
    private String classCode;

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

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
