package com.keyin.aircraft;

import com.keyin.airport.Airport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Aircraft {
    private int id;
    private String type;
    private String airlineName;
    private int noOfPassengers;
    private List<Airport> allowedAirportList = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(int noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public List<Airport> getAllowedAirportList() {
        return allowedAirportList;
    }

    public void setAllowedAirportList(List<Airport> allowedAirportList) {
        this.allowedAirportList = allowedAirportList;
    }

    public void addAllowedAirport(Airport airport) {
        allowedAirportList.add(airport);
    }

    public void removeAllowedAirport(Airport airport) {
        allowedAirportList.removeIf(match -> Objects.equals(airport.getName(), match.getName()));
        System.out.println("removed");
    }
}