package com.example.airline_api.components;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner{

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    public DataLoader(){

    }

    public void run(ApplicationArguments args) throws Exception {
        Flight flight1 = new Flight("Dubai", 200,"07/12/23", "17:15");
        flightRepository.save(flight1);

        Flight flight2 = new Flight("Istanbul", 150,"08/11/23", "07:00");
        flightRepository.save(flight2);

        Passenger passenger1 = new Passenger("Faiz","faiz@gmail.com");
        passenger1.addFlight(flight1);
        passenger1.addFlight(flight2);
        passengerRepository.save(passenger1);

        Passenger passenger2 = new Passenger("Pat","pat@gmail.com");
        passenger2.addFlight(flight2);
        passengerRepository.save(passenger2);

        Passenger passenger3 = new Passenger("Ismail","Ismail@gmail.com");
        passenger3.addFlight(flight1);
        passengerRepository.save(passenger3);
    }
}
