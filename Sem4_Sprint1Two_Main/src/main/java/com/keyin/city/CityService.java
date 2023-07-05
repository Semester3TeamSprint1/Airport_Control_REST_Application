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
        for (City city : cityList) {
            if (city.getId() == id) {
                return city;
            }
        }
        return null;
    }

    public List<City> searchCity(String toSearch) {
        List<City> foundList = new ArrayList<>();
        for (City city : cityList) {
            String idToString = String.valueOf(city.getId());

            if(idToString.equals(toSearch) || city.getName().equalsIgnoreCase(toSearch) || city.getProvince().equalsIgnoreCase(toSearch)) {
                foundList.add(city);
            }
        }
        return foundList;
    }

    public void addCity(City city) {
        cityList.add(city);
    }

    public List<City> deleteCityById(int id) {
        City cityToRemove = null;
        for (City city : cityList) {
            if (city.getId() == id) {
                cityToRemove = city;
                break;
            }
        }
        if (cityToRemove != null) {
            cityList.remove(cityToRemove);
            System.out.println("deleted");
        } else {
            System.out.println("city does not exist.");
        }
        return cityList;
    }

    public List<City> updateCity(int id, City cityToChange) {
        boolean found = false;
        for (City city : cityList) {
            if (city.getId() == id) {
                city.setName(cityToChange.getName());
                city.setProvince(cityToChange.getProvince());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("city does not exist.");
        }
        return cityList;
    }
}