package com.example.FlightBooking.services;

import com.example.FlightBooking.models.Flight;
import com.example.FlightBooking.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> searchFlights(String fromLocation, String toLocation) {
        return flightRepository.findByFromLocationAndToLocation(fromLocation, toLocation);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
}
