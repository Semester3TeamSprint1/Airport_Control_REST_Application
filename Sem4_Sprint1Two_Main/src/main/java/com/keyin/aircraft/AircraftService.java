package com.keyin.aircraft;

import com.keyin.airport.Airport;
import com.keyin.airport.AirportService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AircraftService {

    private List<Aircraft> aircraftList = new ArrayList<>();

    public AircraftService() {
        //populateList();
    }

    public void populateList() {
    }

    public List<Aircraft> getAllAircrafts() {
        return aircraftList;
    }

    public Aircraft getAircraftById(int id) {

        Aircraft foundCraft = new Aircraft();

        for (Aircraft aircraft : aircraftList) {
            if (aircraft.getId() == id) {
                foundCraft = aircraft;
                return foundCraft;
            }
        }
        return foundCraft;
    }

    public List<Aircraft> searchAircraft(String toSearch){

        List<Aircraft> foundList = new ArrayList<>();

        for (Aircraft aircraft : aircraftList) {
            String idToString = String.valueOf(aircraft.getId());

            if(idToString.equals(toSearch) || aircraft.getType().equalsIgnoreCase(toSearch) || aircraft.getAirlineName().equalsIgnoreCase(toSearch)) {
                foundList.add(aircraft);

            }
        }
        return foundList;
    }

    public Aircraft addAircraft(Aircraft aircraft){
        aircraftList.add(aircraft);
        return aircraft;
    }

    public List<Aircraft> updateAircraft(int id, Aircraft aircraftToChange){
        boolean found = false;

        for(Aircraft aircraft : aircraftList) {
            if(aircraft.getId() == id){
                aircraft.setType(aircraftToChange.getType());
                aircraft.setAirlineName(aircraftToChange.getAirlineName());
                aircraft.setNoOfPassengers(aircraftToChange.getNoOfPassengers());
                aircraft.setAllowedAirportList(aircraftToChange.getAllowedAirportList());
                found = true;
            }
        }
        if(!found) {
            System.out.println("Sorry, this aircraft does not exist.");
        }
        return aircraftList;
    }

    public List<Aircraft> deleteAircraftById(int id) {

        boolean found = false;

        for (Aircraft aircraft : aircraftList) {
            if (aircraft.getId() == id) {
                aircraftList.remove(aircraft);
                //found = true;
                System.out.println("The aircraft has been deleted");
                return aircraftList;
            }
        }
        if (!found){
            System.out.println("Sorry the aircraft you are trying to delete does not exist.");
        }
        return aircraftList;
    }

    public Aircraft addToAllowedList(String aircraftToAdd, String airportToAdd){
        List<Airport> allAirports = new ArrayList<>();
        AirportService airportService = new AirportService();
        allAirports = airportService.getAllAirports();

        Airport foundAirport = new Airport();

        for (Airport airport : allAirports ) {
            String idToString = String.valueOf(airport.getId());

            if (idToString.equals(airportToAdd) || airport.getName().equals(airportToAdd)){
                foundAirport = airport;

            }
        }

        Aircraft foundAircraft = new Aircraft();

        if (foundAirport.getName() != null) {
            for (Aircraft aircraft : aircraftList) {
                String idToString = String.valueOf(aircraft.getId());
                if (idToString.equals(aircraftToAdd) || aircraft.getType().equals(aircraftToAdd)) {
                    aircraft.addAllowedAirport(foundAirport);
                    foundAircraft = aircraft;
                    return aircraft;
                }
            }
        } else {
            System.out.println("The airport or aircraft you are looking for does not exist.");
        }

        return foundAircraft;
    }

    public Aircraft removeFromAllowedList(String aircraftSelected, String airportToRemove) {
        List<Airport> allAirports = new ArrayList<>();
        AirportService airportService = new AirportService();
        allAirports = airportService.getAllAirports();

        Airport foundAirport = new Airport();

        for (Airport airport : allAirports ) {
            String idToString = String.valueOf(airport.getId());

            if (idToString.equals(airportToRemove) || airport.getName().equals(airportToRemove)){
                foundAirport = airport;
            }
        }

        System.out.println(foundAirport.getName());

        Aircraft foundAircraft = new Aircraft();

        if (foundAirport.getName() != null) {
            for (Aircraft aircraft : aircraftList) {
                String idToString = String.valueOf(aircraft.getId());
                if (idToString.equals(aircraftSelected) || aircraft.getType().equals(aircraftSelected)) {
                    aircraft.removeAllowedAirport(foundAirport);
                    foundAircraft = aircraft;
                    return aircraft;
                }
            }
        } else {
            System.out.println("The airport or aircraft you are looking for does not exist.");
        }

        System.out.println(foundAircraft.getType());

        return foundAircraft;

    }

}