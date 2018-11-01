package com.apap.tutorial07.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial07.model.FlightModel;
import com.apap.tutorial07.repository.FlightDb;

/**
 * FlightServiceImpl
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightDb flightDb;
    
    @Override
    public FlightModel addFlight(FlightModel flight) {
        return flightDb.save(flight);
    }

    @Override
    public void deleteByFlightNumber(String flightNumber) {
        flightDb.deleteByFlightNumber(flightNumber);
    }

    @Override
    public FlightModel getFlightDetailByFlightNumber(String flightNumber) {
        return flightDb.findByFlightNumber(flightNumber);
    }

	@Override
	public FlightModel getFlightDetailById(long flightId) {
		// TODO Auto-generated method stub
		return flightDb.findById(flightId);
	}

	@Override
	public void updateFlight(FlightModel flight, long id) {
		// TODO Auto-generated method stub
		FlightModel fl = flightDb.findById(id);
		fl.setDestination(flight.getDestination());
		fl.setOrigin(flight.getOrigin());
		fl.setTime(flight.getTime());
		flightDb.save(fl);
	}

	@Override
	public void deleteFlight(FlightModel flight) {
		flightDb.delete(flight);
		
	}

	@Override
	public List<FlightModel> selectAll() {
		// TODO Auto-generated method stub
		return flightDb.findAll();
	}
}