package com.driver.controllers;


import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import com.driver.services.AirportService;
import com.driver.services.FlightService;
import com.driver.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
@RestController
public class AirportController {
    private final AirportService airportService;
    private final FlightService flightService;
    private final PassengerService passengerService;

    @Autowired
    public AirportController(
            AirportService airportService,
            FlightService flightService,
            PassengerService passengerService) {
        this.airportService = airportService;
        this.flightService = flightService;
        this.passengerService = passengerService;
    }

    @PostMapping("/add_airport")
    public String addAirport(@RequestBody Airport airport) {
        return airportService.addAirport(airport);
    }

    @GetMapping("/get-largest-aiport")
    public String getLargestAirportName() {
        return airportService.getLargestAirportName();
    }

    @GetMapping("/get-shortest-time-travel-between-cities")
    public double getShortestDurationOfPossibleBetweenTwoCities(
            @RequestParam("fromCity") String fromCity,
            @RequestParam("toCity") String toCity) {
        double shortestDuration = flightService.getShortestDurationOfPossibleBetweenTwoCities(fromCity,toCity);
        return shortestDuration;
    }

    @GetMapping("/get-number-of-people-on-airport-on/{date}")
    public int getNumberOfPeopleOn(
            @PathVariable("date") Date date,
            @RequestParam("airportName") String airportName) {
        // Implement logic to calculate the number of people on an airport on a given date
        return 0;
    }

    @GetMapping("/calculate-fare")
    public int calculateFlightFare(@RequestParam("flightId") Integer flightId) {
        return flightService.calculateFlightFare(flightId);
    }

    @PostMapping("/book-a-ticket")
    public String bookATicket(
            @RequestParam("flightId") Integer flightId,
            @RequestParam("passengerId") Integer passengerId) {
        return flightService.bookATicket(flightId, passengerId);
    }

    @PutMapping("/cancel-a-ticket")
    public String cancelATicket(
            @RequestParam("flightId") Integer flightId,
            @RequestParam("passengerId") Integer passengerId) {
        return flightService.cancelATicket(flightId, passengerId);
    }

    @GetMapping("/get-count-of-bookings-done-by-a-passenger/{passengerId}")
    public int countOfBookingsDoneByPassengerAllCombined(
            @PathVariable("passengerId") Integer passengerId) {
        return passengerService.countOfBookingsDoneByPassengerAllCombined(passengerId);
    }

    @PostMapping("/add-flight")
    public String addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    @GetMapping("/get-aiportName-from-flight-takeoff/{flightId}")
    public String getAirportNameFromFlightId(@PathVariable("flightId") Integer flightId) {
        return flightService.getAirportNameFromFlightId(flightId);
    }

    @GetMapping("/calculate-revenue-collected/{flightId}")
    public int calculateRevenueOfAFlight(@PathVariable("flightId") Integer flightId) {
        return flightService.calculateRevenueOfAFlight(flightId);
    }

    @PostMapping("/add-passenger")
    public String addPassenger(@RequestBody Passenger passenger) {
        return passengerService.addPassenger(passenger);
    }
}
