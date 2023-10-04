package com.driver.services;

import com.driver.model.Airport;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AirportServiceImpl implements AirportService {
    private final Map<String, Airport> airportRepository = new HashMap<>();

    @Override
    public String addAirport(Airport airport) {
        airportRepository.put(airport.getAirportName(), airport);
        return "SUCCESS";
    }

    @Override
    public String getLargestAirportName() {
        // Implement logic to find the largest airport and return its name
        return null;
    }

    @Override
    public Airport getAirportByName(String airportName) {
        return airportRepository.get(airportName);
    }

    @Override
    public List<Airport> getAllAirports() {
        return new ArrayList<>(airportRepository.values());
    }
}
