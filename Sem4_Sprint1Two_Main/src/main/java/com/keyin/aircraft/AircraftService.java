package com.keyin.aircraft;

import com.keyin.airport.Airport;
import com.keyin.airport.AirportService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {
    private final List<Aircraft> aircraftList = new ArrayList<>();
    private final AirportService airportService;


    private List<Aircraft> aircraftList = new ArrayList<>();

    public AircraftService() {
        //populateList();

    }

    public void populateList() {
    }

    public List<Aircraft> getAllAircrafts() {
        return aircraftList;
    }

    public Optional<Aircraft> getAircraftById(int id) {
        return aircraftList.stream().filter(aircraft -> aircraft.getId() == id).findFirst();
    }

    public List<Aircraft> searchAircraft(String toSearch) {
        List<Aircraft> foundList = new ArrayList<>();

        for (Aircraft aircraft : aircraftList) {
            String idToString = String.valueOf(aircraft.getId());

            if(idToString.equals(toSearch) || aircraft.getType().equalsIgnoreCase(toSearch) || aircraft.getAirlineName().equalsIgnoreCase(toSearch)) {

                foundList.add(aircraft);
            }
        }

        return foundList;
    }

    public Aircraft addAircraft(Aircraft aircraft) {
        aircraftList.add(aircraft);
        return aircraft;
    }

    public List<Aircraft> updateAircraft(int id, Aircraft aircraftToChange) {
        Optional<Aircraft> optionalAircraft = getAircraftById(id);

        if (optionalAircraft.isPresent()) {
            Aircraft aircraft = optionalAircraft.get();
            aircraft.setType(aircraftToChange.getType());
            aircraft.setAirlineName(aircraftToChange.getAirlineName());
            aircraft.setNoOfPassengers(aircraftToChange.getNoOfPassengers());
            aircraft.setAllowedAirportList(aircraftToChange.getAllowedAirportList());
        } else {
            System.out.println("the aircraft does not exist.");
        }

        return aircraftList;
    }

    public List<Aircraft> deleteAircraftById(int id) {
        Optional<Aircraft> optionalAircraft = getAircraftById(id);

        if (optionalAircraft.isPresent()) {
            Aircraft aircraft = optionalAircraft.get();
            aircraftList.remove(aircraft);
            System.out.println("deleted");
        } else {
            System.out.println("the aircraft does not exist.");
        }

        return aircraftList;
    }

    public Aircraft addToAllowedList(String aircraftToAdd, String airportToAdd){
        List<Airport> allAirports = new ArrayList<>();
        AirportService airportService = new AirportService();
        allAirports = airportService.getAllAirports();

        if (optionalAirport.isPresent()) {
            Airport foundAirport = optionalAirport.get();

            Optional<Aircraft> optionalAircraft = aircraftList.stream()
                    .filter(aircraft -> aircraft.getId().equals(aircraftToAdd) || aircraft.getType().equals(aircraftToAdd))
                    .findFirst();

            if (optionalAircraft.isPresent()) {
                Aircraft aircraft = optionalAircraft.get();
                aircraft.addAllowedAirport(foundAirport);
                return aircraft;
            } else {
                System.out.println("The aircraft does not exist.");
            }
        } else {
            System.out.println("The airport does not exist.");
        }

        return new Aircraft();
    }

    public Aircraft removeFromAllowedList(String aircraftSelected, String airportToRemove) {
        List<Airport> allAirports = airportService.getAllAirports();

        Optional<Airport> optionalAirport = allAirports.stream()
                .filter(airport -> airport.getId().equals(airportToRemove) || airport.getName().equals(airportToRemove))
                .findFirst();

        if (optionalAirport.isPresent()) {
            Airport foundAirport = optionalAirport.get();

            Optional<Aircraft> optionalAircraft = aircraftList.stream()
                    .filter(aircraft -> aircraft.getId().equals(aircraftSelected) || aircraft.getType().equals(aircraftSelected))
                    .findFirst();

            if (optionalAircraft.isPresent()) {
                Aircraft aircraft = optionalAircraft.get();
                aircraft.removeAllowedAirport(foundAirport);
                return aircraft;
            } else {
                System.out.println("The aircraft does not exist.");
            }
        } else {
            System.out.println("The airport does not exist.");
        }

        return new Aircraft();
    }
}