package com.keyin.passenger;

import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftService;
import com.keyin.airport.Airport;
import com.keyin.airport.AirportService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerService {
    private List<Passenger> passengerList = new ArrayList<>();

    public PassengerService() {
        //populate();
    }

    public void populate(){
    }

    public List<Passenger> getAllPassenger() {
        return passengerList;
    }

    public Passenger getPassengerById(int id) {

        Passenger foundPassenger = new Passenger();

        for (Passenger passenger : passengerList) {
            if (passenger.getId() == id) {
                foundPassenger = passenger;
                return foundPassenger;
            }
        }
        return foundPassenger;
    }

    public List<Passenger> searchPassenger(String toSearch){

        List<Passenger> foundList = new ArrayList<>();

        for (Passenger passenger : passengerList) {
            String idToString = String.valueOf(passenger.getId());

            if(idToString.equals(toSearch) || passenger.getFirstName().equalsIgnoreCase(toSearch) || passenger.getLastName().equalsIgnoreCase(toSearch) || passenger.getPhoneNumber().equals(toSearch)) {
                foundList.add(passenger);

            }
        }
        return foundList;
    }

    public Passenger addPassenger(Passenger passenger){
        passengerList.add(passenger);
        return passenger;
    }

    public List<Passenger> updatePassenger(int id, Passenger passengerToChange){
        boolean found = false;

        for(Passenger passenger : passengerList) {
            if(passenger.getId() == id){
                passenger.setFirstName(passengerToChange.getFirstName());
                passenger.setLastName(passengerToChange.getLastName());
                passenger.setPhoneNumber(passengerToChange.getPhoneNumber());
                passenger.setAircraftIdsList(passengerToChange.getAircraftIdsList());
                passenger.setAirportIdsList(passengerToChange.getAirportIdsList());
                found = true;
            }
        }
        if(!found) {
            System.out.println("Sorry, this passenger does not exist.");
        }
        return passengerList;
    }

    public List<Passenger> deletePassengerById(int id) {

        boolean found = false;

        for (Passenger passenger : passengerList) {
            if (passenger.getId() == id) {
                passengerList.remove(passenger);
                //found = true;
                System.out.println("The airport has been deleted");
                return passengerList;
            }
        }
        if (!found){
            System.out.println("Sorry the passenger you are trying to delete does not exist.");
        }
        return passengerList;
    }

    public List<Aircraft> getAircraft(int id){
        List<Aircraft> allAircraftList = new ArrayList<>();
        AircraftService aircraftService = new AircraftService();
        allAircraftList = aircraftService.getAllAircrafts();

        List<Aircraft> aircraftFlownOn = new ArrayList<>();

        Passenger foundPassenger = new Passenger();

        for(Passenger passenger: passengerList) {
            if (id == passenger.getId()) {
                foundPassenger = passenger;
            }
        }

        for(Integer i : foundPassenger.getAircraftIdsList() ) {
            for(Aircraft aircraft : allAircraftList) {
                if(i.equals(aircraft.getId())) {
                    aircraftFlownOn.add(aircraft);
                }
            }
        }

        return aircraftFlownOn;
    }

    public List<Airport> getAirports(int id) {
        List<Airport> allAirportsList = new ArrayList<>();
        AirportService airportService = new AirportService();
        allAirportsList = airportService.getAllAirports();

        List<Airport> airportVisited = new ArrayList<>();

        Passenger foundPassenger = new Passenger();

        for(Passenger passenger : passengerList) {
            if (id == passenger.getId()) {
                foundPassenger = passenger;
            }
        }

        for(Integer i : foundPassenger.getAirportIdsList() ) {
            for(Airport airport : allAirportsList) {
                if(i.equals(airport.getId())) {
                    airportVisited.add(airport);
                }
            }
        }

        return airportVisited;
    }
}
