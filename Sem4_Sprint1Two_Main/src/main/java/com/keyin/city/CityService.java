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
        City city1 = new City();
        //city1.setId(1);
        city1.setName("St Johns");
        city1.setProvince("Newfoundland");
        cityList.add(city1);

        City city2 = new City();
        //city2.setId(2);
        city2.setName("Edmonton");
        city2.setProvince("Alberta");
        cityList.add(city2);

        City city3 = new City();
        //city3.setId(3);
        city3.setName("Calgary");
        city3.setProvince("Alberta");
        cityList.add(city3);
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
            if (String.valueOf(city.getId()).equals(toSearch) || city.getName().equals(toSearch) || city.getProvince().equals(toSearch)) {
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