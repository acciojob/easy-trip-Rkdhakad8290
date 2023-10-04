package com.driver.services;
import com.driver.model.Passenger;
import com.driver.model.Flight;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.String;
import com.driver.model.Airport;

@Service
public class FlightServiceImpl implements FlightService {
    private final Map<Integer, Flight> flightRepository = new HashMap<>();

    private final Map<Integer, Flight> flightDatabase = new HashMap<>(); // Your flight database

    private final Map<Integer, Passenger> passengerDatabase = new HashMap<>(); // Your passenger database

    private final Map<Integer, Integer> flightBookings = new HashMap<>(); // Flight bookings tracking

    @Override
    public String addFlight(Flight flight) {
        int flightId = flight.getFlightId();
        if (flightRepository.containsKey(flightId)) {
            return "FAILURE"; // Flight with the same ID already exists
        }

        flightRepository.put(flightId, flight);
        return "SUCCESS";
    }

    @Override
    public double getShortestDurationOfPossibleBetweenTwoCities(String fromCity, String toCity) {
        // Implement logic to find the shortest duration between two cities
        List<Flight> flights = getAllFlights(); // Implement this method.
        double shortestDuration = -1;

        for (Flight flight : flights) {
            if (flight.getFromCity().equals(fromCity) && flight.getToCity().equals(toCity)) {
                if (shortestDuration == -1 || flight.getDuration() < shortestDuration) {
                    shortestDuration = flight.getDuration();
                }
            }
        }

        return shortestDuration;

    }

    @Override
    public int calculateFlightFare(int flightId) {
        // Check if the flight exists in the database
        if (!flightDatabase.containsKey(flightId)) {
            return -1; // Indicate that the flight does not exist
        }

        // Get the flight from the database
        Flight flight = flightDatabase.get(flightId);

        // Calculate the flight fare based on the number of people who have already booked
        int baseFare = 3000;
        int bookedPassengers = flightBookings.getOrDefault(flightId, 0);
        int fare = baseFare + (bookedPassengers * 50);

        return fare;
    }

    @Override
    public List<Flight> getAllFlights() {
        return new ArrayList<>(flightRepository.values());
    }

    @Override
    public String bookATicket(Integer flightId, Integer passengerId) {
        if (!flightDatabase.containsKey(flightId)) {
            return "FAILURE: Flight not found";
        }

        // Check if the passenger exists in the database
        if (!passengerDatabase.containsKey(passengerId)) {
            return "FAILURE: Passenger not found";
        }

        // Check if the flight is already fully booked
        Flight flight = flightDatabase.get(flightId);
        if (isFlightFullyBooked(flightId, flight.getMaxCapacity())) {
            return "FAILURE: Flight is fully booked";
        }

        // Check if the passenger has already booked this flight
        if (hasPassengerAlreadyBooked(flightId, passengerId)) {
            return "FAILURE: Passenger has already booked this flight";
        }

        // Book the ticket for the passenger
        int currentBookings = flightBookings.getOrDefault(flightId, 0);
        flightBookings.put(flightId, currentBookings + 1);

        // You can perform additional logic here, such as updating payment status, etc.

        return "SUCCESS: Ticket booked successfully";
    }



    // ... Other methods from the FlightService interface ...

    @Override
    public String cancelATicket(Integer flightId, Integer passengerId) {
        // Check if the flight exists in the database
        if (!flightDatabase.containsKey(flightId)) {
            return "FAILURE: Flight not found";
        }

        // You can implement logic to cancel the ticket for the specified passenger on the given flight
        // For example, if the cancellation is successful, you can remove the passenger from the flight's passenger list.

        // Return a success or failure message based on the cancellation result
        return "SUCCESS: Ticket canceled successfully";
    }

    @Override
    public String getAirportNameFromFlightId(Integer flightId) {
        // Check if the flight exists in the database
        if (!flightDatabase.containsKey(flightId)) {
            return "FAILURE: Flight not found";
        }

        // Get the flight from the database
        Airport airport = flightDatabase.get(flightId);

        // Get the airport name from the flight object
        String airportName = airport.getAirportName(); // Assuming 'getFromAirport()' returns the airport object

        // Return the airport name
        return airportName;
    }


    @Override
    public int calculateRevenueOfAFlight(Integer flightId) {
        // Check if the flight exists in the database
        if (!flightDatabase.containsKey(flightId)) {
            return -1; // Flight not found
        }

        // Get the flight from the database
        Flight flight = flightDatabase.get(flightId);

        // Calculate the total revenue for the flight
        int totalRevenue = 3000 + flight.getBookedPassengerCount() * 50;

        return totalRevenue;
    }

    // Helper method to check if a flight is fully booked
    private boolean isFlightFullyBooked(Integer flightId, int maxCapacity) {
        return flightBookings.getOrDefault(flightId, 0) >= maxCapacity;
    }

    // Helper method to check if a passenger has already booked a flight
    private boolean hasPassengerAlreadyBooked(Integer flightId, Integer passengerId) {

        for (Map.Entry<Integer, Integer> entry : flightBookings.entrySet()) {
            if (entry.getKey().equals(flightId) && entry.getValue().equals(passengerId)) {
                return true; // Passenger has already booked this flight
            }
        }
        return false;
    }


}

