package com.example.airline_api.controllers;

import com.example.airline_api.models.Booking;
import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.models.PassengerIdDTO;
import com.example.airline_api.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.InvalidSearchFilterException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    // Display all available flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(
            @RequestParam(required = false) String destination
    ) {
        if (destination!=null){
            try {
                return new ResponseEntity<>(flightService.searchFlight(destination), HttpStatus.OK);
            }catch (InvalidSearchFilterException e){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

        }
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }

    // Display a specific flight
    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Optional<Flight> optionalFlight = flightService.getFlightById(id);
        return new ResponseEntity<>(
                optionalFlight.isEmpty() ? null : optionalFlight.get(),
                optionalFlight.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    // Add details of a new flight
    @PostMapping
    public ResponseEntity<Flight> addNewFlight(@RequestBody Flight flight) {
        flightService.addFlight(flight);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    // Book passenger on a flight
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Booking> addPassengerToFlight(
            @RequestBody PassengerIdDTO passengerId,
            @PathVariable Long id
    ) {
        Flight flight = flightService.getFlightById(id).get();
        try{
            Booking booking = flightService.bookPassengerOnFlight(passengerId.getPassengerId(), id);
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    // Cancel flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> cancelFlight(@PathVariable Long id) {
        try{
        flightService.cancelBooking(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}