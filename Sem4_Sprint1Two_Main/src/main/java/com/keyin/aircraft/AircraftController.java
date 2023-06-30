package com.keyin.aircraft;

import com.keyin.action.ActionService;
import com.keyin.airport.Airport;
import com.keyin.browser.BrowserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class AircraftController {
    @Autowired
    private AircraftService aircraftService;
    @Autowired
    private BrowserService browserService;
    @Autowired
    private ActionService actionService;

    @GetMapping("/aircraft")
    public List<Aircraft> getAllAircrafts() {
        browserService.addToBrowser("getAllAircrafts()", "/aircraft", LocalDateTime.now());
        return aircraftService.getAllAircrafts();
    }

    @GetMapping("/aircraft/{id}")
    public Aircraft getAircraftById(@PathVariable int id) {
        String url = "/aircraft/" + String.valueOf(id);
        browserService.addToBrowser("getAircraftById()", url, LocalDateTime.now());
        return aircraftService.getAircraftById(id);
    }

    @GetMapping("/aircraft/search")
    public List<Aircraft> searchAircraft(@RequestParam String toSearch){
        browserService.addToBrowser("searchAircraft()", "aircraft/search", LocalDateTime.now());
        return aircraftService.searchAircraft(toSearch);
    }

    @PostMapping("/aircraft/addAircraft")
    public void addAircraft(@RequestBody Aircraft aircraft){
        actionService.addAction("aircraft", "create", Map.of("id", aircraft.getId(), "type",  aircraft.getType(), "airlineName",aircraft.getAirlineName(), "noOfPassengers", aircraft.getNoOfPassengers(), "allowedAirportList", aircraft.getAllowedAirportList()));
        browserService.addToBrowser("addAircraft()", "/aircraft/addAircraft", LocalDateTime.now());
        aircraftService.addAircraft(aircraft);
    }

    @DeleteMapping("/aircraft/deleteAircraft/{id}")
    public List<Aircraft> deleteAirportById(@PathVariable int id) {
        Aircraft aircraftForAction = new Aircraft();
        List<Aircraft> aircraftlist = aircraftService.getAllAircrafts();
        for (Aircraft aircraft : aircraftlist){
            if (aircraft.getId() == id) {
                aircraftForAction = aircraft;
            }
        }
        if (aircraftForAction != null) {
            actionService.addAction("aircraft", "delete", Map.of("id", aircraftForAction.getId(), "type", aircraftForAction.getType(),"airlineName", aircraftForAction.getAirlineName(), "noOfPassengers", aircraftForAction.getNoOfPassengers(), "allowedAirportList", aircraftForAction.getAllowedAirportList()));
        }

        String url = "/aircraft/deleteAircraft/" + String.valueOf(id);
        browserService.addToBrowser("deleteAircraft()", url, LocalDateTime.now());
        return aircraftService.deleteAircraftById(id);
    }

    @PutMapping("/aircraft/updateAircraft/{id}")
    public List<Aircraft> updateAircraft(@PathVariable int id, @RequestBody Aircraft aircraft){
        Aircraft aircraftForAction = new Aircraft();
        List<Aircraft> aircraftlist = aircraftService.getAllAircrafts();
        for (Aircraft aircraftToFind : aircraftlist){
            if (aircraftToFind.getId() == id) {
                aircraftForAction = aircraftToFind;
            }
        }
        if (aircraftForAction != null) {
            actionService.addAction("aircraft", "update",Map.of("id", aircraftForAction.getId(), "type", aircraftForAction.getType(),"airlineName", aircraftForAction.getAirlineName(), "noOfPassengers", aircraftForAction.getNoOfPassengers(), "allowedAirportList", aircraftForAction.getAllowedAirportList()));
        }

        String url = "/aircraft/updateAircraft/" + String.valueOf(id);
        browserService.addToBrowser("updateAircraft()", url, LocalDateTime.now());
        return aircraftService.updateAircraft(id, aircraft);
    }

    @PostMapping("/aircraft/addAllowedAirport")
    public Aircraft addAirport(@RequestParam String aircraftToSearch, String airportToSearch){
        browserService.addToBrowser("addToAllowedList()", "/aircraft/addAllowedAirport", LocalDateTime.now());
        return aircraftService.addToAllowedList(aircraftToSearch, airportToSearch);
    }

    @PostMapping("/aircraft/removeAllowedAirport")
    public Aircraft removeAirport(@RequestParam String aircraftToSearch, String airportToSearch){
        browserService.addToBrowser("removeFromAllowedList()", "/aircraft/addAirport", LocalDateTime.now());
        return aircraftService.removeFromAllowedList(aircraftToSearch, airportToSearch);
    }

}
