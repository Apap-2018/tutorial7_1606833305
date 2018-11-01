package com.apap.tutorial07.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial07.model.FlightModel;

/**
 * FlightService
 */
public interface FlightService {
    FlightModel addFlight(FlightModel flight);
    
    void deleteByFlightNumber(String flightNumber);

    FlightModel getFlightDetailByFlightNumber(String flightNumber);
    FlightModel getFlightDetailById(long flightId);
    void updateFlight (FlightModel flight, long id);
    void deleteFlight(FlightModel flight);
    List <FlightModel> selectAll ();

    
}