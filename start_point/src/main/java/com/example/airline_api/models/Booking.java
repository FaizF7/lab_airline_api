package com.example.airline_api.models;

public class Booking {

    private Long passengerId;
    private String passengerName;
    private Long flightId;
    private String flightDestination;

    public Booking(Long passengerId, String passengerName, Long flightId, String flightDestination) {
        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.flightId = flightId;
        this.flightDestination = flightDestination;
    }

    public Booking() {
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getFlightDestination() {
        return flightDestination;
    }

    public void setFlightDestination(String flightDestination) {
        this.flightDestination = flightDestination;
    }
}
