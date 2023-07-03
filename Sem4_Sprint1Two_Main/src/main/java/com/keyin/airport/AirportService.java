//package com.keyin.airport;
//
//
//import com.keyin.aircraft.AircraftService;
//import com.keyin.city.City;
//import com.keyin.city.CityService;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class AirportService {
//    private List<Airport> airportList = new ArrayList<>();
//
//    private List<City> cityList = new ArrayList<>();
//
//    // make a method to do the population instead of in the constructor
//
//    public AirportService() {
//        populateList();
//    }
//
//    public void populateList(){
//        Airport airport1 = new Airport();
//        airport1.setId(1);
//        airport1.setName("NL Airport");
//        airport1.setCityId(1);
//        airportList.add(airport1);
//
//        Airport airport2 = new Airport();
//        airport2.setId(2);
//        airport2.setName("Edmonton Airlines");
//        airport2.setCityId(2);
//        airportList.add(airport2);
//
//        Airport airport3 = new Airport();
//        airport3.setId(3);
//        airport3.setName("Calgary Airport");
//        airport3.setCityId(3);
//        airportList.add(airport3);
//    }
//
//    public List<Airport> getAllAirports() {
//        return airportList;
//    }
//
//    public Airport getAirportById(int id) {
//
//        Airport foundAirport = new Airport();
//
//        for (Airport airport : airportList) {
//            if (airport.getId() == id) {
//                foundAirport = airport;
//                return foundAirport;
//            }
//        }
//        return foundAirport;
//    }
//
//    public Airport addAirport(Airport airport){
//        airportList.add(airport);
//        for (Airport list : airportList) {
//            System.out.println(list.getName());
//
//        }
//        return airport;
//    }
//
//    public List<Airport> searchAirport(String toSearch){
//
//        List<Airport> foundList = new ArrayList<>();
//
//        for (Airport airport : airportList) {
//            String idToString = String.valueOf(airport.getId());
//
//            if(idToString.equals(toSearch) || airport.getName().equals(toSearch)) {
//                foundList.add(airport);
//
//            }
//        }
//        return foundList;
//    }
//
//    public List<Airport> updateAirport(int id, Airport airportToChange){
//        boolean found = false;
//
//        for(Airport airport : airportList) {
//            if(airport.getId() == id){
//                airport.setName(airportToChange.getName());
//                airport.setCityId(airportToChange.getCityId());
//                found = true;
//            }
//        }
//        if(!found) {
//            System.out.println("Sorry, this airport does not exist.");
//        }
//        return airportList;
//    }
//
//    // doesn't like it when you delete the last item in the list that makes it empty
//    public List<Airport> deleteAirportById(int id) {
//
//        boolean found = false;
//
//        for (Airport airport : airportList) {
//            if (airport.getId() == id) {
//                airportList.remove(airport);
//                //found = true;
//                System.out.println("The airport has been deleted");
//                return airportList;
//            }
//        }
//        if (!found){
//            System.out.println("Sorry the airport you are trying to delete does not exist.");
//        }
//        return airportList;
//    }
//
//    public List<Airport> airportByCityId(int id) {
//        CityService cityService = new CityService();
//
//        List<Airport> airportByCityList = new ArrayList<>();
//
//        for (Airport airport : airportList) {
//            if (airport.getCityId() == id) {
//                airportByCityList.add(airport);
//            }
//        }
//
//        return airportByCityList;
//    }
//}

package com.keyin.airport;

import com.keyin.city.City;
import com.keyin.city.CityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportService {
    private List<Airport> airportList = new ArrayList<>();

    private List<City> cityList = new ArrayList<>();

    public AirportService() {
        populateList();
    }

    private void populateList() {
        airportList.add(createAirport(1, "NL Airport", 1));
        airportList.add(createAirport(2, "Edmonton Airlines", 2));
        airportList.add(createAirport(3, "Calgary Airport", 3));
    }

    private Airport createAirport(int id, String name, int cityId) {
        Airport airport = new Airport();
        airport.setId(id);
        airport.setName(name);
        airport.setCityId(cityId);
        return airport;
    }

    public List<Airport> getAllAirports() {
        return airportList;
    }

    public Airport getAirportById(int id) {
        for (Airport airport : airportList) {
            if (airport.getId() == id) {
                return airport;
            }
        }
        return null;
    }

    public Airport addAirport(Airport airport) {
        airportList.add(airport);
        return airport;
    }

    public List<Airport> searchAirport(String toSearch) {
        List<Airport> foundList = new ArrayList<>();
        for (Airport airport : airportList) {
            if (String.valueOf(airport.getId()).equals(toSearch) || airport.getName().equals(toSearch)) {
                foundList.add(airport);
            }
        }
        return foundList;
    }

    public List<Airport> updateAirport(int id, Airport airportToChange) {
        for (Airport airport : airportList) {
            if (airport.getId() == id) {
                airport.setName(airportToChange.getName());
                airport.setCityId(airportToChange.getCityId());
                return airportList;
            }
        }
        System.out.println("Sorry, this airport cannot be found");
        return airportList;
    }

    public List<Airport> deleteAirportById(int id) {
        for (Airport airport : airportList) {
            if (airport.getId() == id) {
                airportList.remove(airport);
                System.out.println("The selected airport has been deleted");
                return airportList;
            }
        }
        System.out.println("Sorry, this airport cannot be found");
        return airportList;
    }

    public List<Airport> airportByCityId(int id) {
        List<Airport> airportByCityList = new ArrayList<>();
        for (Airport airport : airportList) {
            if (airport.getCityId() == id) {
                airportByCityList.add(airport);
            }
        }
        return airportByCityList;
    }
}