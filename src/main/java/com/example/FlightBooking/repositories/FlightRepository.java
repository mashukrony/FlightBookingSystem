package com.example.FlightBooking.repositories;

import com.example.FlightBooking.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByFromLocationAndToLocation(String fromLocation, String toLocation);
}
