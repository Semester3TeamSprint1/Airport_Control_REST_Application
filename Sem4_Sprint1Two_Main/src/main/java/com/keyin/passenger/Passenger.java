package com.keyin.passenger;

import java.util.ArrayList;
import java.util.List;

public class Passenger {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<Integer> aircraftIdsList = new ArrayList<>();
    private List<Integer> airportIdsList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Integer> getAircraftIdsList() {
        return aircraftIdsList;
    }

    public List<Integer> getAirportIdsList() {
        return airportIdsList;
    }

    public void addToAircraftIdsList(int id) {
        aircraftIdsList.add(id);
    }

    public void removeFromAircraftIdsList(int id) {
        aircraftIdsList.removeIf(match -> match == id);
        System.out.println("removed from list");
    }

    public void addToAirportIdsList(int id) {
        airportIdsList.add(id);
    }

    public void removeFromAirportIdsList(int id){
        for (Integer match : airportIdsList) {
            if(match == id){
                airportIdsList.remove(match);
                System.out.println("The airport has been removed from the list");
            }
        }
    }
}