package com.keyin.activity;

import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftService;
import com.keyin.airport.Airport;
import com.keyin.airport.AirportService;
import com.keyin.city.City;
import com.keyin.city.CityService;
import com.keyin.passenger.Passenger;
import com.keyin.passenger.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Stack;

@Service
public class ActivityService {

    private Stack<Activity> activityStack = new Stack<>();
    private Stack<Activity> redoActivityStack = new Stack<>();

    @Autowired
    private AircraftService aircraftService;
    @Autowired
    private AirportService airportService;
    @Autowired
    private CityService cityService;
    @Autowired
    private PassengerService passengerService;

    public void addActivity(String objectName, String operation, Map<String, Object> theObject) {
        Activity activity = new Activity(objectName, operation, theObject);
        activityStack.push(activity);
        Activity topActivity = activityStack.peek();
        String objectId = topActivity.getParameters().get("id").toString();
        String message = String.format("The activity has been added to the stack %s %s object id: %s",
                topActivity.getObject(), topActivity.getOperation(), objectId);
        System.out.println(message);

        /* TODO: Remove if above function works.
        Activity activity = new Activity(objectName, operation, theObject);
        activityStack.push(activity);
        System.out.println("the activity has been added to the stack " + activityStack.peek().getObject()
        +" " + activityStack.peek().getOperation() + " object id: " + activityStack.peek().getParameters().get("id"));
        */
    }

    public void undoActivity() {
        if (activityStack.isEmpty()) {
            System.out.println("Sorry, the stack is empty.");
            return;
        }

        Activity activity = activityStack.peek();
        String objectType = activity.getObject();
        String operation = activity.getOperation();

        switch (objectType) {
            case "aircraft":
                handleAircraftActivity(activity, operation);
                break;
            case "airport":
                handleAirportActivity(activity, operation);
                break;
            case "passenger":
                handlePassengerActivity(activity, operation);
                break;
            case "city":
                handleCityActivity(activity, operation);
                break;
            default:
                break;
        }
    }

    private void handleAircraftActivity(Activity activity, String operation) {
        if (operation.equals("create")) {
            aircraftService.deleteAircraftById((Integer) activity.getParameters().get("id"));
        } else if (operation.equals("delete")) {
            aircraftService.addAircraft(setAircraftFromActivityStack());
        } else {
            aircraftService.updateAircraft((Integer) activity.getParameters().get("id"), setAircraftFromActivityStack());
        }
        redoActivityStack.push(activityStack.pop());
    }

    private void handleAirportActivity(Activity activity, String operation) {
        if (operation.equals("create")) {
            airportService.deleteAirportById((Integer) activity.getParameters().get("id"));
        } else if (operation.equals("delete")) {
            airportService.addAirport(setAirportFromActivityStack());
        } else {
            airportService.updateAirport((Integer) activity.getParameters().get("id"), setAirportFromActivityStack());
        }
        redoActivityStack.push(activityStack.pop());
    }

    private void handlePassengerActivity(Activity activity, String operation) {
        if (operation.equals("create")) {
            passengerService.deletePassengerById((Integer) activity.getParameters().get("id"));
        } else if (operation.equals("delete")) {
            passengerService.addPassenger(setPassengerFromActivityStack());
        } else {
            passengerService.updatePassenger((Integer) activity.getParameters().get("id"), setPassengerFromActivityStack());
        }
        redoActivityStack.push(activityStack.pop());
    }

    private void handleCityActivity(Activity activity, String operation) {
        if (operation.equals("create")) {
            cityService.deleteCityById((Integer) activity.getParameters().get("id"));
        } else if (operation.equals("delete")) {
            cityService.addCity(setCityFromActivityStack());
        } else {
            cityService.updateCity((Integer) activity.getParameters().get("id"), setCityFromActivityStack());
        }
        redoActivityStack.push(activityStack.pop());
    }
    public void redoActivity() {
        if (redoActivityStack.isEmpty()) {
            System.out.println("Sorry, the stack is empty.");
            return;
        }

        Activity redoActivity = redoActivityStack.peek();
        String objectType = redoActivity.getObject();
        String operation = redoActivity.getOperation();

        switch (objectType) {
            case "aircraft":
                handleRedoAircraftActivity(redoActivity, operation);
                break;
            case "airport":
                handleRedoAirportActivity(redoActivity, operation);
                break;
            case "passenger":
                handleRedoPassengerActivity(redoActivity, operation);
                break;
            case "city":
                handleRedoCityActivity(redoActivity, operation);
                break;
            default:
                System.out.println("Sorry, something went wrong...");
                break;
        }
    }

    private void handleRedoAircraftActivity(Activity redoActivity, String operation) {
        if (operation.equals("create")) {
            aircraftService.addAircraft(setAircraftFromRedoStack());
        } else if (operation.equals("delete")) {
            aircraftService.deleteAircraftById((Integer) redoActivity.getParameters().get("id"));
        }
        activityStack.push(redoActivityStack.pop());
    }

    private void handleRedoAirportActivity(Activity redoActivity, String operation) {
        if (operation.equals("create")) {
            airportService.addAirport(setAirportFromRedoStack());
        } else if (operation.equals("delete")) {
            airportService.deleteAirportById((Integer) redoActivity.getParameters().get("id"));
        }
        activityStack.push(redoActivityStack.pop());
    }

    private void handleRedoPassengerActivity(Activity redoActivity, String operation) {
        if (operation.equals("create")) {
            passengerService.addPassenger(setPassengerFromRedoStack());
        } else if (operation.equals("delete")) {
            passengerService.deletePassengerById((Integer) redoActivity.getParameters().get("id"));
        }
        activityStack.push(redoActivityStack.pop());
    }

    private void handleRedoCityActivity(Activity redoActivity, String operation) {
        if (operation.equals("create")) {
            cityService.addCity(setCityFromRedoStack());
        } else if (operation.equals("delete")) {
            cityService.deleteCityById((Integer) redoActivity.getParameters().get("id"));
        }
        activityStack.push(redoActivityStack.pop());
    }
    public Aircraft setAircraftFromActivityStack() {
        Activity activity = activityStack.peek();
        Map<String, Object> parameters = activity.getParameters();

        Aircraft newAircraft = new Aircraft();
        newAircraft.setId((Integer) parameters.get("id"));
        newAircraft.setType((String) parameters.get("type"));
        newAircraft.setAirlineName((String) parameters.get("airlineName"));
        newAircraft.setNoOfPassengers((Integer) parameters.get("numberOfPassengers"));
        newAircraft.setAllowedAirportList((List<Airport>) parameters.get("allowedAirportList"));

        return newAircraft;
    }

    public Aircraft setAircraftFromRedoStack() {
        Activity redoActivity = redoActivityStack.peek();
        Map<String, Object> parameters = redoActivity.getParameters();

        Aircraft newAircraft = new Aircraft();
        newAircraft.setId((Integer) parameters.get("id"));
        newAircraft.setType((String) parameters.get("type"));
        newAircraft.setAirlineName((String) parameters.get("airlineName"));
        newAircraft.setNoOfPassengers((Integer) parameters.get("numberOfPassengers"));
        newAircraft.setAllowedAirportList((List<Airport>) parameters.get("allowedAirportList"));

        return newAircraft;
    }

    public Airport setAirportFromActivityStack() {
        Activity activity = activityStack.peek();
        Map<String, Object> parameters = activity.getParameters();

        Airport newAirport = new Airport();
        newAirport.setId((Integer) parameters.get("id"));
        newAirport.setName((String) parameters.get("name"));
        newAirport.setCityId((Integer) parameters.get("cityId"));

        return newAirport;
    }

    public Airport setAirportFromRedoStack() {
        Activity redoActivity = redoActivityStack.peek();
        Map<String, Object> parameters = redoActivity.getParameters();

        Airport newAirport = new Airport();
        newAirport.setId((Integer) parameters.get("id"));
        newAirport.setName((String) parameters.get("name"));
        newAirport.setCityId((Integer) parameters.get("cityId"));

        return newAirport;
    }

    public City setCityFromActivityStack() {
        Activity activity = activityStack.peek();
        Map<String, Object> parameters = activity.getParameters();

        City newCity = new City();
        newCity.setId((Integer) parameters.get("id"));
        newCity.setName((String) parameters.get("name"));
        newCity.setProvince((String) parameters.get("province"));
        newCity.setPopulation((Integer) parameters.get("population"));

        return newCity;
    }

    public City setCityFromRedoStack() {
        Activity redoActivity = redoActivityStack.peek();
        Map<String, Object> parameters = redoActivity.getParameters();

        City newCity = new City();
        newCity.setId((Integer) parameters.get("id"));
        newCity.setName((String) parameters.get("name"));
        newCity.setProvince((String) parameters.get("province"));
        newCity.setPopulation((Integer) parameters.get("population"));

        return newCity;
    }

    public Passenger setPassengerFromActivityStack() {
        Activity activity = activityStack.peek();
        Map<String, Object> parameters = activity.getParameters();

        Passenger newPassenger = new Passenger();
        newPassenger.setId((Integer) parameters.get("id"));
        newPassenger.setFirstname((String) parameters.get("firstname"));
        newPassenger.setLastName((String) parameters.get("lastName"));
        newPassenger.setPhoneNumber((String) parameters.get("phoneNumber"));
        newPassenger.setAircraftIdsList((List<Integer>) parameters.get("aircraftIdsList"));
        newPassenger.setAirportIdsList((List<Integer>) parameters.get("airportIdsList"));

        return newPassenger;
    }

    public Passenger setPassengerFromRedoStack() {
        Activity redoActivity = redoActivityStack.peek();
        Map<String, Object> parameters = redoActivity.getParameters();

        Passenger newPassenger = new Passenger();
        newPassenger.setId((Integer) parameters.get("id"));
        newPassenger.setFirstname((String) parameters.get("firstname"));
        newPassenger.setLastName((String) parameters.get("lastName"));
        newPassenger.setPhoneNumber((String) parameters.get("phoneNumber"));
        newPassenger.setAircraftIdsList((List<Integer>) parameters.get("aircraftIdsList"));
        newPassenger.setAirportIdsList((List<Integer>) parameters.get("airportIdsList"));

        return newPassenger;
    }

