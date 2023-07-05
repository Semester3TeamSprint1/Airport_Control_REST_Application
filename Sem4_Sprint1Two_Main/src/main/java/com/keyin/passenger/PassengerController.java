package com.keyin.passenger;

import com.keyin.activity.ActivityService;
import com.keyin.aircraft.Aircraft;
import com.keyin.airport.Airport;
import com.keyin.browser.BrowserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private BrowserService browserService;
    @Autowired
    private ActivityService activityService;

    @GetMapping("/passenger")
    public List<Passenger> getAllPassenger() {
        browserService.addToBrowser("getAllPassenger()", "/passenger", LocalDateTime.now());
        return passengerService.getAllPassenger();
    }

    @GetMapping("/passenger/{id}")
    public Passenger getPassengerById(@PathVariable int id) {
        String url = "/passenger/" + String.valueOf(id);
        browserService.addToBrowser("getPassengerById()", url, LocalDateTime.now());
        return passengerService.getPassengerById(id);
    }

    @GetMapping("/passenger/search")
    public List<Passenger> searchPassenger(@RequestParam String toSearch){
        browserService.addToBrowser("searchPassenger()", "passenger/search", LocalDateTime.now());
        return passengerService.searchPassenger(toSearch);
    }

    @PostMapping("/passenger/addPassenger")
    public void addPassenger(@RequestBody Passenger passenger){
        activityService.addActivity("passenger", "create", Map.of("id", passenger.getId(), "firstName",  passenger.getFirstName(), "lastName", passenger.getLastName(), "phoneNumber", passenger.getPhoneNumber(), "aircraftIdsList", passenger.getAircraftIdsList(), "airportIdsList", passenger.getAirportIdsList()));
        browserService.addToBrowser("addPassenger()", "/passenger/addPassenger", LocalDateTime.now());
        passengerService.addPassenger(passenger);
    }

    @DeleteMapping("/passenger/deletePassenger/{id}")
    public List<Passenger> deletePassengerById(@PathVariable int id) {
        Passenger passengerForActivity = new Passenger();
        List<Passenger> passengerlist = passengerService.getAllPassenger();
        for (Passenger passenger : passengerlist){
            if (passenger.getId() == id) {
                passengerForActivity = passenger;
            }
        }
        if (passengerForActivity != null) {
            activityService.addActivity("passenger", "delete", Map.of("id", passengerForActivity.getId(), "firstName",  passengerForActivity.getFirstName(), "lastName", passengerForActivity.getLastName(), "phoneNumber", passengerForActivity.getPhoneNumber(), "aircraftIdsList", passengerForActivity.getAircraftIdsList(), "airportIdsList", passengerForActivity.getAirportIdsList()));
        }

        String url = "/passenger/deletePassenger/" + String.valueOf(id);
        browserService.addToBrowser("deletePassenger()", url, LocalDateTime.now());
        return passengerService.deletePassengerById(id);
    }

    @PutMapping("/passenger/updatePassenger/{id}")
    public List<Passenger> updatePassenger(@PathVariable int id, @RequestBody Passenger passenger){
        Passenger passengerForActivity = new Passenger();
        List<Passenger> passengerlist = passengerService.getAllPassenger();
        for (Passenger passengerToFind : passengerlist){
            if (passengerToFind.getId() == id) {
                passengerForActivity = passengerToFind;
            }
        }
        if (passengerForActivity != null) {
            activityService.addActivity("passenger", "update", Map.of("id", passengerForActivity.getId(), "firstName",  passengerForActivity.getFirstName(), "lastName", passengerForActivity.getLastName(), "phoneNumber", passengerForActivity.getPhoneNumber(), "aircraftIdsList", passengerForActivity.getAircraftIdsList(), "airportIdsList", passengerForActivity.getAirportIdsList()));
        }

        String url = "/passenger/updatePassenger/" + String.valueOf(id);
        browserService.addToBrowser("updatePassenger()", url, LocalDateTime.now());
        return passengerService.updatePassenger(id, passenger);
    }

    @GetMapping("/passenger/{id}/getAircraft")
    public List<Aircraft> getAircraftPassengerTravelledOn(@PathVariable int id) {
        String url = "/passenger/"  + String.valueOf(id) + "/getAircraft";
        browserService.addToBrowser("getAircraft()", url, LocalDateTime.now());
        return passengerService.getAircraft(id);
    }

    @GetMapping("/passenger/{id}/getAirport")
    public List<Airport> getAirportPassengerVisited(@PathVariable int id) {
        String url = "/passenger/"  + String.valueOf(id) + "/getAirport";
        browserService.addToBrowser("getAirport()", url, LocalDateTime.now());
        return passengerService.getAirports(id);
    }


}
