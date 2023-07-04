package com.keyin.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class AirportController {
    @Autowired
    private AirportService airportService;
    @Autowired
    private BrowserService browserService;
    @Autowired
    private ActivityService activityService;

    @GetMapping("/airport")
    public List<Airport> getAllAirports() {
        addToBrowser("getAllAirports()", "/airport");
        return airportService.getAllAirports();
    }

    @GetMapping("/airport/{id}")
    public Airport getAirportById(@PathVariable int id) {
        String url = "/airport/" + id;
        addToBrowser("getAirportById()", url);
        return airportService.getAirportById(id);
    }

    @GetMapping("/airport/search")
    public List<Airport> searchAirport(@RequestParam String toSearch) {
        addToBrowser("searchAirport()", "airport/search");
        return airportService.searchAirport(toSearch);
    }

    @PostMapping("/airport/addAirport")
    public void addAirport(@RequestBody Airport airport){
        activityService.addActivity("airport", "create", Map.of("id", airport.getId(), "code", airport.getCode(), "name",  airport.getName(),"cityId", airport.getCityId()));
        browserService.addToBrowser("addAirport()", "/airport/addAirport", LocalDateTime.now());
        airportService.addAirport(airport);
        addActivity("create", airport);
    }

    @DeleteMapping("/airport/deleteAirport/{id}")
    public List<Airport> deleteAirportById(@PathVariable int id) {
        List<Airport> airportList = airportService.getAllAirports();
        Airport airportForActivity = findAirportById(airportList, id);

        if (airportForActivity != null) {
            activityService.addActivity("airport", "delete", Map.of("id", airportForActivity.getId(), "code", airportForActivity.getCode(),"name",  airportForActivity.getName(), "cityId", airportForActivity.getCityId()));
        }

        String url = "/airport/deleteAirport/" + id;
        addToBrowser("deleteAirport()", url);
        return airportService.deleteAirportById(id);
    }

    @PutMapping("/airport/updateAirport/{id}")
    public List<Airport> updateAirport(@PathVariable int id, @RequestBody Airport airport) {
        List<Airport> airportList = airportService.getAllAirports();
        Airport airportForActivity = findAirportById(airportList, id);

        if (airportForActivity != null) {
            activityService.addActivity("airport", "update", Map.of("id", airportForActivity.getId(), "code", airportForActivity.getCode(),"name",  airportForActivity.getName(), "cityId", airportForActivity.getCityId()));
        }

        String url = "/airport/updateAirport/" + id;
        addToBrowser("updateAirport()", url);
        return airportService.updateAirport(id, airport);
    }

    @GetMapping("/airport/getByCityId/{id}")
    public List<Airport> airportByCityId(@PathVariable int id) {
        String url = "/airport/getByCity/" + id;
        addToBrowser("getAirportByCityId()", url);
        return airportService.airportByCityId(id);
    }

    private void addToBrowser(String method, String url) {
        browserService.addToBrowser(method, url, LocalDateTime.now());
    }

    private void addActivity(String action, Airport airport) {
        activityService.addActivity("airport", action, Map.of("id", airport.getId(), "name", airport.getName(), "cityId", airport.getCityId()));
    }

    private Airport findAirportById(List<Airport> airportList, int id) {
        for (Airport airport : airportList) {
            if (airport.getId() == id) {
                return airport;
            }
        }
        return null;
    }
}