//package com.keyin.aircraft;
//
//import com.keyin.airport.Airport;
//import com.keyin.airport.AirportService;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class AircraftService {
//
//    private List<Aircraft> aircraftList = new ArrayList<>();
//
//    public AircraftService() {
//        populateList();
//    }
//
//    public void populateList() {
//        List<Airport> allAirports = new ArrayList<>();
//        AirportService airportService = new AirportService();
//        allAirports = airportService.getAllAirports();
//
//        Aircraft aircraft1 = new Aircraft();
//        aircraft1.setId(1);
//        aircraft1.setType("Boeing 737");
//        aircraft1.setAirlineName("Air Canada");
//        aircraft1.setNoOfPassengers(100);
//        aircraft1.addAllowedAirport(allAirports.get(0));
//        aircraft1.addAllowedAirport(allAirports.get(1));
//        aircraftList.add(aircraft1);
//
//        Aircraft aircraft2 = new Aircraft();
//        aircraft2.setId(2);
//        aircraft2.setType("Boeing 101");
//        aircraft2.setAirlineName("West Jet");
//        aircraft2.setNoOfPassengers(70);
//        aircraftList.add(aircraft2);
//
//        Aircraft aircraft3 = new Aircraft();
//        aircraft3.setId(3);
//        aircraft3.setType("Boeing 408");
//        aircraft3.setAirlineName("Air Canada");
//        aircraft3.setNoOfPassengers(70);
//        aircraftList.add(aircraft3);
//
//        Aircraft aircraft4 = new Aircraft();
//        aircraft4.setId(4);
//        aircraft4.setType("Boeing 709");
//        aircraft4.setAirlineName("West Jet");
//        aircraft4.setNoOfPassengers(25);
//        aircraftList.add(aircraft4);
//
//        Aircraft aircraft5 = new Aircraft();
//        aircraft5.setId(5);
//        aircraft5.setType("Boeing 402");
//        aircraft5.setAirlineName("East Jet");
//        aircraft5.setNoOfPassengers(90);
//        aircraftList.add(aircraft5);
//    }
//
//    public List<Aircraft> getAllAircrafts() {
//        return aircraftList;
//    }
//
//    public Aircraft getAircraftById(int id) {
//
//        Aircraft foundCraft = new Aircraft();
//
//        for (Aircraft aircraft : aircraftList) {
//            if (aircraft.getId() == id) {
//                foundCraft = aircraft;
//                return foundCraft;
//            }
//        }
//        return foundCraft;
//    }
//
//    public List<Aircraft> searchAircraft(String toSearch){
//
//        List<Aircraft> foundList = new ArrayList<>();
//
//        for (Aircraft aircraft : aircraftList) {
//            String idToString = String.valueOf(aircraft.getId());
//
//            if(idToString.equals(toSearch) || aircraft.getType().equals(toSearch) || aircraft.getAirlineName().equals(toSearch)) {
//                foundList.add(aircraft);
//
//            }
//        }
//        return foundList;
//    }
//
//    public Aircraft addAircraft(Aircraft aircraft){
//        aircraftList.add(aircraft);
//        return aircraft;
//    }
//
//    public List<Aircraft> updateAircraft(int id, Aircraft aircraftToChange){
//        boolean found = false;
//
//        for(Aircraft aircraft : aircraftList) {
//            if(aircraft.getId() == id){
//                aircraft.setType(aircraftToChange.getType());
//                aircraft.setAirlineName(aircraftToChange.getAirlineName());
//                aircraft.setNoOfPassengers(aircraftToChange.getNoOfPassengers());
//                aircraft.setAllowedAirportList(aircraftToChange.getAllowedAirportList());
//                found = true;
//            }
//        }
//        if(!found) {
//            System.out.println("Sorry, this aircraft does not exist.");
//        }
//        return aircraftList;
//    }
//
//    // doesn't like it when you delete the last item in the list that makes it empty
//    public List<Aircraft> deleteAircraftById(int id) {
//
//        boolean found = false;
//
//        for (Aircraft aircraft : aircraftList) {
//            if (aircraft.getId() == id) {
//                aircraftList.remove(aircraft);
//                //found = true;
//                System.out.println("The aircraft has been deleted");
//                return aircraftList;
//            }
//        }
//        if (!found){
//            System.out.println("Sorry the aircraft you are trying to delete does not exist.");
//        }
//        return aircraftList;
//    }
//
//
//    //------------------------------------------------------------------------------------------------------------------
//
//    // fix the problem for searching aircraft that doesn't exist
//    // fix so you cant add duplicates
//    public Aircraft addToAllowedList(String aircraftToAdd, String airportToAdd){
//        List<Airport> allAirports = new ArrayList<>();
//        AirportService airportService = new AirportService();
//        allAirports = airportService.getAllAirports();
//
//        Airport foundAirport = new Airport();
//
//        for (Airport airport : allAirports ) {
//            String idToString = String.valueOf(airport.getId());
//
//            if (idToString.equals(airportToAdd) || airport.getName().equals(airportToAdd)){
//                foundAirport = airport;
//
//            }
//        }
//
//        Aircraft foundAircraft = new Aircraft();
//
//        if (foundAirport.getName() != null) {
//            for (Aircraft aircraft : aircraftList) {
//                String idToString = String.valueOf(aircraft.getId());
//                if (idToString.equals(aircraftToAdd) || aircraft.getType().equals(aircraftToAdd)) {
//                    aircraft.addAllowedAirport(foundAirport);
//                    foundAircraft = aircraft;
//                    return aircraft;
//                }
//            }
//        } else {
//            System.out.println("The airport or aircraft you are looking for does not exist.");
//        }
//
//        return foundAircraft;
//    }
//
//    // doesn't like when i delete from end of list
//    public Aircraft removeFromAllowedList(String aircraftSelected, String airportToRemove) {
//        List<Airport> allAirports = new ArrayList<>();
//        AirportService airportService = new AirportService();
//        allAirports = airportService.getAllAirports();
//
//        Airport foundAirport = new Airport();
//
//        for (Airport airport : allAirports ) {
//            String idToString = String.valueOf(airport.getId());
//
//            if (idToString.equals(airportToRemove) || airport.getName().equals(airportToRemove)){
//                foundAirport = airport;
//            }
//        }
//
//        System.out.println(foundAirport.getName());
//
//        Aircraft foundAircraft = new Aircraft();
//
//        if (foundAirport.getName() != null) {
//            for (Aircraft aircraft : aircraftList) {
//                String idToString = String.valueOf(aircraft.getId());
//                if (idToString.equals(aircraftSelected) || aircraft.getType().equals(aircraftSelected)) {
//                    aircraft.removeAllowedAirport(foundAirport);
//                    foundAircraft = aircraft;
//                    return aircraft;
//                }
//            }
//        } else {
//            System.out.println("The airport or aircraft you are looking for does not exist.");
//        }
//
//        System.out.println(foundAircraft.getType());
//
//        return foundAircraft;
//
//    }
//
//}

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

    public AircraftService(AirportService airportService) {
        this.airportService = airportService;
        populateList();
    }

    private void populateList() {
        List<Airport> allAirports = airportService.getAllAirports();

        Aircraft aircraft1 = new Aircraft(1, "Airbus A220", "euroAir", 100);
        aircraft1.addAllowedAirport(allAirports.get(0));
        aircraft1.addAllowedAirport(allAirports.get(1));
        aircraftList.add(aircraft1);

        Aircraft aircraft2 = new Aircraft(2, "Comac C919", "West Jet", 70);
        aircraftList.add(aircraft2);

        Aircraft aircraft3 = new Aircraft(3, "Boeing 737", "Air Canada", 70);
        aircraftList.add(aircraft3);

        Aircraft aircraft4 = new Aircraft(4, "Fokker 70", "KLM Royal Dutch", 25);
        aircraftList.add(aircraft4);

        Aircraft aircraft5 = new Aircraft(5, "Embraer E-Jet E2 family", "Brazil air", 90);
        aircraftList.add(aircraft5);
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

            if (idToString.equals(toSearch) || aircraft.getType().equals(toSearch) || aircraft.getAirlineName().equals(toSearch)) {
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

    public Aircraft addToAllowedList(String aircraftToAdd, String airportToAdd) {
        List<Airport> allAirports = airportService.getAllAirports();

        Optional<Airport> optionalAirport = allAirports.stream()
                .filter(airport -> airport.getId().equals(airportToAdd) || airport.getName().equals(airportToAdd))
                .findFirst();

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