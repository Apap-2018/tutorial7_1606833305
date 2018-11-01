package com.apap.tutorial07.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial07.model.FlightModel;
import com.apap.tutorial07.service.FlightService;
import com.apap.tutorial7.rest.Setting;


/**
 * FlightController
 */
@RestController
@RequestMapping ("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
   
   
    @PostMapping(value = "/add")
    public FlightModel addFlightSubmit(@RequestBody FlightModel flight) {
        return flightService.addFlight(flight);
    }
    
    @GetMapping (value = "/view/{flightNumber}")
    public FlightModel pilotView (@PathVariable("flightNumber") String flightNumber) {
    	FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
    	return flight;
    }
    
    @PutMapping (value = "/update/{flightId}")
    public String updateFlightSubmit (@PathVariable (value = "flightId", required = false) long flightId,
    		@RequestParam (value = "destination", required = false) String destination,
    		@RequestParam(value = "origin", required = false) String origin,
    		@RequestParam (value = "time", required = false) Date time) {
    	FlightModel flight = flightService.getFlightDetailById(flightId);
    	if (flight == null) {
    		return "Couldn't find your flight";
    	}
    	
    	flight.setDestination(destination);
    	flight.setOrigin(origin);
    	flight.setTime(time);
    	flightService.updateFlight(flight, flightId);
    	return "flight update success"; 
    }
    
    @DeleteMapping (value = "/delete/{flightId}")
    public String deleteFlight (@PathVariable("flightId") long flightId) {
    	FlightModel flight = flightService.getFlightDetailById(flightId);
    	flightService.deleteFlight(flight);
    	return "flight has been deleted";
    }
    
    @GetMapping (value = "/all")
    public List <FlightModel> flightView () {
    	List <FlightModel> allFlight = flightService.selectAll();
    	return allFlight;
    }
    
    @Autowired
    RestTemplate restTemplateAirport;
    
    @Bean
    public RestTemplate restAirport () {
    	return new RestTemplate();
    }
    
    @GetMapping(value="/airport/{cityTerm}")
    public String getAirport(@PathVariable("cityTerm") String cityTerm) throws Exception{
    	String path = Setting.airportUrl  + cityTerm;
    	return restTemplateAirport.getForEntity(path, String.class).getBody();
    }
    
}