package com.driver.services;

import com.driver.model.Flight;
import java.util.List;

public interface FlightService {
    String addFlight(Flight flight);
    double getShortestDurationOfPossibleBetweenTwoCities(String fromCity, String toCity);
    int calculateFlightFare(int flightId);
    List<Flight> getAllFlights();
    String bookATicket(Integer flightId, Integer passengerId);

    String cancelATicket(Integer flightId, Integer passengerId);

    String getAirportNameFromFlightId(Integer flightId);

    int calculateRevenueOfAFlight(Integer flightId);
}