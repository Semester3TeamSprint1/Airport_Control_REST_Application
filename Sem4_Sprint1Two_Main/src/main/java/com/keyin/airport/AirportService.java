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

    public List<Airport> getAllAirports() {
        return airportList;
    }

    public Airport getAirportById(int id) {

        Airport foundAirport = new Airport();

        for (Airport airport : airportList) {
            if (airport.getId() == id) {
                foundAirport = airport;
                return foundAirport;
            }
        }
        return foundAirport;
    }

    public Airport addAirport(Airport airport){
        airportList.add(airport);
        for (Airport list : airportList) {
            System.out.println(list.getName());

        }
        return airport;
    }

    public List<Airport> searchAirport(String toSearch){

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
                found = true;
            }
        }
        if(!found) {
            System.out.println("Sorry, this airport does not exist.");
        }
        return airportList;
    }

    public List<Airport> deleteAirportById(int id) {

        boolean found = false;

        for (Airport airport : airportList) {
            if (airport.getId() == id) {
                airportList.remove(airport);
                //found = true;
                System.out.println("The airport has been deleted");
                return airportList;
            }
        }
        if (!found){
            System.out.println("Sorry the airport you are trying to delete does not exist.");
        }
        return airportList;
    }

    public List<Airport> airportByCityId(int id) {
        CityService cityService = new CityService();

        List<Airport> airportByCityList = new ArrayList<>();

        for (Airport airport : airportList) {
            if (airport.getCityId() == id) {
                airportByCityList.add(airport);
            }
        }

        return airportByCityList;
    }
}