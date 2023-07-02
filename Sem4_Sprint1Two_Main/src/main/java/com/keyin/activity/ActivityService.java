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
    /* TODO: check to see if above code works.
    public void undoActivity() {
        if(activityStack.size() > 0) {
            switch (activityStack.peek().getObject()){
                case "aircraft" :
                    if (activityStack.peek().getOperation() == "create"){
                        aircraftService.deleteAircraftById((Integer) activityStack.peek().getParameters().get("id"));
                        redoActivityStack.push(activityStack.peek());
                        activityStack.pop();
                    } else if (activityStack.peek().getOperation() == "delete"){
                        aircraftService.addAircraft(setAircraftFromActivityStack());
                        redoActivityStack.push(activityStack.peek());
                        activityStack.pop();
                        break;
                    } else {
                        aircraftService.updateAircraft((Integer) activityStack.peek().getParameters().get("id"), setAircraftFromActivityStack());
                        redoActivityStack.push(activityStack.peek());
                        activityStack.pop();
                        break;
                    }
                    break;
                case "airport":
                    if (activityStack.peek().getOperation() == "create"){
                        airportService.deleteAirportById((Integer) activityStack.peek().getParameters().get("id"));
                        redoActivityStack.push(activityStack.peek());
                        activityStack.pop();
                    } else if (activityStack.peek().getOperation() == "delete"){
                        airportService.addAirport(setAirportFromActivityStack());
                        redoActivityStack.push(activityStack.peek());
                        activityStack.pop();
                        break;
                    } else {
                        airportService.updateAirport((Integer) activityStack.peek().getParameters().get("id"), setAirportFromActivityStack());
                        redoActivityStack.push(activityStack.peek());
                        activityStack.pop();
                        break;
                    }
                    break;
                case "passenger":
                    if (activityStack.peek().getOperation() == "create"){
                        passengerService.deletePassengerById((Integer) activityStack.peek().getParameters().get("id"));
                        redoActivityStack.push(activityStack.peek());
                        activityStack.pop();
                    } else if (activityStack.peek().getOperation() == "delete"){
                        passengerService.addPassenger(setPassengerFromActivityStack());
                        redoActivityStack.push(activityStack.peek());
                        activityStack.pop();
                    } else {
                        passengerService.updatePassenger((Integer) activityStack.peek().getParameters().get("id"), setPassengerFromActivityStack());
                        redoActivityStack.push(activityStack.peek());
                        activityStack.pop();
                    }
                    break;
                case "city":
                    if (activityStack.peek().getOperation() == "create"){
                        cityService.deleteCityById((Integer) activityStack.peek().getParameters().get("id"));
                        redoActivityStack.push(activityStack.peek());
                        activityStack.pop();
                    } else if (activityStack.peek().getOperation() == "delete"){
                        cityService.addCity(setCityFromActivityStack());
                        redoActivityStack.push(activityStack.peek());
                        activityStack.pop();
                    } else {
                        cityService.updateCity((Integer) activityStack.peek().getParameters().get("id"), setCityFromActivityStack());
                        redoActivityStack.push(activityStack.peek());
                        activityStack.pop();
                    }
                    break;
                default :
                    break;
            }
        } else {
            System.out.println("Sorry the stack is empty.");
        }

    }
     */

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
    /* TODO: See if above code works properly.
    public void redoActivity() {
        if(redoActivityStack.size() > 0) {
            switch (redoActivityStack.peek().getObject()){
                case "aircraft" :
                    if (redoActivityStack.peek().getOperation() == "create"){
                        aircraftService.addAircraft(setAircraftFromRedoStack());
                        activityStack.push(redoActivityStack.peek());
                        redoActivityStack.pop();
                    } else if (redoActivityStack.peek().getOperation() == "delete"){
                        aircraftService.deleteAircraftById((Integer) redoActivityStack.peek().getParameters().get("id"));
                        activityStack.push(redoActivityStack.peek());
                        redoActivityStack.pop();
                        break;
                    }
                    break;
                case "airport":
                    if (redoActivityStack.peek().getOperation() == "create"){
                        airportService.addAirport(setAirportFromRedoStack());
                        activityStack.push(redoActivityStack.peek());
                        redoActivityStack.pop();
                        break;
                    } else if (redoActivityStack.peek().getOperation() == "delete"){
                        airportService.deleteAirportById((Integer) redoActivityStack.peek().getParameters().get("id"));
                        activityStack.push(redoActivityStack.peek());
                        redoActivityStack.pop();
                        break;
                    }
                    break;
                case "passenger":
                    if (redoActivityStack.peek().getOperation() == "create"){
                        passengerService.addPassenger(setPassengerFromRedoStack());
                        activityStack.push(redoActivityStack.peek());
                        redoActivityStack.pop();
                    } else if (redoActivityStack.peek().getOperation() == "delete"){
                        passengerService.deletePassengerById((Integer) redoActivityStack.peek().getParameters().get("id"));
                        activityStack.push(redoActivityStack.peek());
                        redoActivityStack.pop();
                    }
                    break;
                case "city":
                    if (redoActivityStack.peek().getOperation() == "create"){
                        cityService.addCity(setCityFromRedoStack());
                        activityStack.push(redoActivityStack.peek());
                        redoActivityStack.pop();
                    } else if (redoActivityStack.peek().getOperation() == "delete"){
                        cityService.deleteCityById((Integer) redoActivityStack.peek().getParameters().get("id"));
                        activityStack.push(redoActivityStack.peek());
                        redoActivityStack.pop();
                    }
                default :
                    System.out.println("Sorry, something went wrong...");
                    break;
            }

        } else {
            System.out.println("Sorry the stack is empty.");
        }
    }
     */

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

/*  TODO: Make sure above code works before deleting.
    public Aircraft setAircraftFromActivityStack(){
        Aircraft newAircraft = new Aircraft();
        newAircraft.setId((Integer) activityStack.peek().getParameters().get("id"));
        newAircraft.setType((String) activityStack.peek().getParameters().get("type"));
        newAircraft.setAirlineName((String) activityStack.peek().getParameters().get("airlineName"));
        newAircraft.setNoOfPassengers((Integer) activityStack.peek().getParameters().get("numberOfPassengers"));
        newAircraft.setAllowedAirportList((List<Airport>) activityStack.peek().getParameters().get("allowedAirportList"));
        return newAircraft;
    }

    public Aircraft setAircraftFromRedoStack(){
        Aircraft newAircraft = new Aircraft();
        newAircraft.setId((Integer) redoActivityStack.peek().getParameters().get("id"));
        newAircraft.setType((String) redoActivityStack.peek().getParameters().get("type"));
        newAircraft.setAirlineName((String) redoActivityStack.peek().getParameters().get("airlineName"));
        newAircraft.setNoOfPassengers((Integer) redoActivityStack.peek().getParameters().get("numberOfPassengers"));
        newAircraft.setAllowedAirportList((List<Airport>) redoActivityStack.peek().getParameters().get("allowedAirportList"));
        return newAircraft;
    }

    public Airport setAirportFromActivityStack(){
        Airport newAirport = new Airport();
        newAirport.setId((Integer) activityStack.peek().getParameters().get("id"));
        newAirport.setName((String) activityStack.peek().getParameters().get("name"));
        newAirport.setCityId((Integer) activityStack.peek().getParameters().get("cityId"));
        return newAirport;
    }

    public Airport setAirportFromRedoStack(){
        Airport newAirport = new Airport();
        newAirport.setId((Integer) redoActivityStack.peek().getParameters().get("id"));
        newAirport.setName((String) redoActivityStack.peek().getParameters().get("name"));
        newAirport.setCityId((Integer) redoActivityStack.peek().getParameters().get("cityId"));
        return newAirport;
    }

    public City setCityFromActivityStack(){
        City newCity = new City();
        newCity.setId((Integer) activityStack.peek().getParameters().get("id"));
        newCity.setName((String) activityStack.peek().getParameters().get("name"));
        newCity.setProvince((String) activityStack.peek().getParameters().get("province"));
        newCity.setPopulation((Integer) activityStack.peek().getParameters().get("population"));
        return newCity;
    }

    public City setCityFromRedoStack(){
        City newCity = new City();
        newCity.setId((Integer) redoActivityStack.peek().getParameters().get("id"));
        newCity.setName((String) redoActivityStack.peek().getParameters().get("name"));
        newCity.setProvince((String) redoActivityStack.peek().getParameters().get("province"));
        newCity.setPopulation((Integer) redoActivityStack.peek().getParameters().get("population"));
        return newCity;
    }

    public Passenger setPassengerFromActivityStack(){
        Passenger newPassenger = new Passenger();
        newPassenger.setId((Integer) activityStack.peek().getParameters().get("id"));
        newPassenger.setFirstname((String) activityStack.peek().getParameters().get("firstname"));
        newPassenger.setLastName((String) activityStack.peek().getParameters().get("lastName"));
        newPassenger.setPhoneNumber((String) activityStack.peek().getParameters().get("phoneNumber"));
        newPassenger.setAircraftIdsList((List<Integer>) activityStack.peek().getParameters().get("aircraftIdsList"));
        newPassenger.setAirportIdsList((List<Integer>) activityStack.peek().getParameters().get("airportIdsList"));
        return newPassenger;
    }

    public Passenger setPassengerFromRedoStack(){
        Passenger newPassenger = new Passenger();
        newPassenger.setId((Integer) redoActivityStack.peek().getParameters().get("id"));
        newPassenger.setFirstname((String) redoActivityStack.peek().getParameters().get("firstname"));
        newPassenger.setLastName((String) redoActivityStack.peek().getParameters().get("lastName"));
        newPassenger.setPhoneNumber((String) redoActivityStack.peek().getParameters().get("phoneNumber"));
        newPassenger.setAircraftIdsList((List<Integer>) redoActivityStack.peek().getParameters().get("aircraftIdsList"));
        newPassenger.setAirportIdsList((List<Integer>) redoActivityStack.peek().getParameters().get("airportIdsList"));
        return newPassenger;
    }

 */
}