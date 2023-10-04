package com.driver.services;
import com.driver.model.Airport;
import java.util.List;

public interface AirportService {
    String addAirport(Airport airport);
    String getLargestAirportName();
    Airport getAirportByName(String airportName);
    List<Airport> getAllAirports();
}
