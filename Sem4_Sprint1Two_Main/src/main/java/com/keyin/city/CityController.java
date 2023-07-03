
package com.keyin.city;

import com.keyin.activity.ActivityService;
import com.keyin.browser.BrowserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private BrowserService browserService;
    @Autowired
    private ActivityService activityService;

    @GetMapping("/city")
    public List<City> getAllCities() {
        browserService.addToBrowser("getAllCities()", "/city", LocalDateTime.now());
        return cityService.getAllCities();
    }

    @GetMapping("/city/{id}")
    public City getCityById(@PathVariable int id) {
        String url = "/city/" + id;
        browserService.addToBrowser("getCityById()", url, LocalDateTime.now());
        return cityService.getCityById(id);
    }

    @GetMapping("/city/search")
    public List<City> searchCity(@RequestParam String toSearch) {
        browserService.addToBrowser("searchCity()", "city/search", LocalDateTime.now());
        return cityService.searchCity(toSearch);
    }

    @PostMapping("/city/addCity")
    public void addCity(@RequestBody City city) {
        activityService.addActivity("city", "create", Map.of("id", city.getId(), "name", city.getName(), "province", city.getProvince(), "population", city.getPopulation()));
        browserService.addToBrowser("addCity()", "/city/addCity", LocalDateTime.now());
        cityService.addCity(city);
    }

    @DeleteMapping("/city/deleteCity/{id}")
    public List<City> deleteCityById(@PathVariable int id) {
        City cityForActivity = cityService.getCityById(id);
        if (cityForActivity != null) {
            activityService.addActivity("city", "delete", Map.of("id", cityForActivity.getId(), "name", cityForActivity.getName(), "province", cityForActivity.getProvince(), "population", cityForActivity.getPopulation()));
        }

        String url = "/city/deleteCity/" + id;
        browserService.addToBrowser("deleteCity()", url, LocalDateTime.now());
        return cityService.deleteCityById(id);
    }

    @PutMapping("/city/updateCity/{id}")
    public List<City> updateCity(@PathVariable int id, @RequestBody City city) {
        City cityForActivity = cityService.getCityById(id);
        if (cityForActivity != null) {
            activityService.addActivity("city", "update", Map.of("id", cityForActivity.getId(), "name", cityForActivity.getName(), "province", cityForActivity.getProvince(), "population", cityForActivity.getPopulation()));
        }

        String url = "/city/updateCity/" + id;
        browserService.addToBrowser("updateCity()", url, LocalDateTime.now());
        return cityService.updateCity(id, city);
    }
}