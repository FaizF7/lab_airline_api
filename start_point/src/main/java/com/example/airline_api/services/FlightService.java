package com.example.airline_api.services;

import com.example.airline_api.models.Booking;
import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public Flight addFlight(Flight flight){
        flightRepository.save(flight);
        return flight;
    }

    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(long id){
        return flightRepository.findById(id);
    }

    public Booking addPassengerToFlight(Long passengerId, Long flightId) {
        Flight flight = flightRepository.getById(flightId);
        Passenger passenger = passengerRepository.getById(passengerId);
        flight.addPassenger(passenger);
        flightRepository.save(flight);
        return new Booking(passengerId,passenger.getName(),flightId,flight.getDestination());
    }


}
