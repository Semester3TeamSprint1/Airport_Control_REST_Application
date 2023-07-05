package com.keyin.city;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {
    private List<City> cityList = new ArrayList<>();

    public CityService() {
        //populateList();
    }

    public void populateList() {
    }

    public List<City> getAllCities() {
        return cityList;
    }

    public City getCityById(int id) {

        City foundCity = new City();

        for (City city : cityList) {
            if (city.getId() == id) {
                foundCity = city;
                return foundCity;
            }
        }
        return foundCity;
    }

    public List<City> searchCity(String toSearch){

        List<City> foundList = new ArrayList<>();

        for (City city : cityList) {
            String idToString = String.valueOf(city.getId());

            if(idToString.equals(toSearch) || city.getName().equalsIgnoreCase(toSearch) || city.getProvince().equalsIgnoreCase(toSearch)) {
                foundList.add(city);

            }
        }
        return foundList;
    }

    public City addCity(City city){
        cityList.add(city);
        return city;
    }

    public List<City> deleteCityById(int id) {

        boolean found = false;

        for (City city : cityList) {
            if (city.getId() == id) {
                cityList.remove(city);
                //found = true;
                System.out.println("The city has been deleted");
                return cityList;
            }
        }
        if (!found){
            System.out.println("Sorry the city you are trying to delete does not exist.");
        }
        return cityList;
    }

    public List<City> updateCity(int id, City cityToChange){
        boolean found = false;

        for(City city : cityList) {
            if(city.getId() == id){
                city.setName(cityToChange.getName());
                city.setProvince(cityToChange.getProvince());
                found = true;
            }
        }
        if(!found) {
            System.out.println("Sorry, the city you are trying to update does not exist.");
        }
        return cityList;
    }

}
