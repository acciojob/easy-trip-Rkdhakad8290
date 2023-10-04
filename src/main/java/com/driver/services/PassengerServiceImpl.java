package com.driver.services;

import com.driver.model.Passenger;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final Map<Integer, Passenger> passengerRepository = new HashMap<>();

    @Override
    public String addPassenger(Passenger passenger) {
        passengerRepository.put(passenger.getPassengerId(), passenger);
        return "SUCCESS";
    }

    @Override
    public int countOfBookingsDoneByPassengerAllCombined(int passengerId) {
        // Implement logic to count bookings done by a passenger
        return 0;
    }
}
