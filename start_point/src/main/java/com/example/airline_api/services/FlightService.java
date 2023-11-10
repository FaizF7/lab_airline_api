package com.example.airline_api.services;

import com.example.airline_api.models.Booking;
import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidSearchFilterException;
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

    public Booking bookPassengerOnFlight (Long passengerId, Long flightId) throws Exception{
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        Optional<Passenger> optionalPassenger = passengerRepository.findById(passengerId);
        if(optionalFlight.isEmpty()){
            throw new IllegalArgumentException("Flight not found");
        }
        Flight flight = optionalFlight.get();
        if(optionalPassenger.isEmpty()){
            throw new IllegalArgumentException("Passenger not found");
        }
        Passenger passenger = optionalPassenger.get();
        if (flight.getPassengers().contains(passenger)){
            throw new Exception("Passenger already on flight");
        }
        if(flight.getPassengers().size()>=flight.getCapacity()){
            throw new Exception("Flight full");
        }
        flight.addPassenger(passenger);
        flightRepository.save(flight);
        return new Booking(passengerId,passenger.getName(),flightId,flight.getDestination());
    }

    public void cancelFlight(Long id) throws Exception{
        if(flightRepository.findById(id).isEmpty()){
            throw new Exception("No flight with this Id");
        }
        flightRepository.deleteById(id);
    }

    public List<Flight> searchFlight(String destination) throws InvalidSearchFilterException{
        List<Flight> flights = flightRepository.findByDestinationIs(destination);
        if (flights.isEmpty()){
            throw new InvalidSearchFilterException("Flight not found");
        }

        return flights;
    }


}
