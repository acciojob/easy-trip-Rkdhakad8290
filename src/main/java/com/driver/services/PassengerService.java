package com.driver.services;

import com.driver.model.Passenger;

public interface PassengerService {
    String addPassenger(Passenger passenger);
    int countOfBookingsDoneByPassengerAllCombined(int passengerId);
}
