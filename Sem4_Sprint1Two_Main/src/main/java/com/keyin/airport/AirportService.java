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
       // populateList();
    }

    public void populateList(){
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
            String idToString = String.valueOf(airport.getId());

            if(idToString.equals(toSearch) || airport.getName().equalsIgnoreCase(toSearch) || airport.getCode().equalsIgnoreCase(toSearch)) {
                foundList.add(airport);
            }
        }
        return foundList;
    }


    public List<Airport> updateAirport(int id, Airport airportToChange){
        boolean found = false;

        for(Airport airport : airportList) {
            if(airport.getId() == id){
                airport.setCode(airportToChange.getCode());
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