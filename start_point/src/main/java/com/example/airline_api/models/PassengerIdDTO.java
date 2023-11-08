package com.example.airline_api.models;

public class PassengerIdDTO {
    private Long PassengerId;

    public PassengerIdDTO(Long passengerId) {
        PassengerId = passengerId;
    }

    public PassengerIdDTO() {
    }

    public Long getPassengerId() {
        return PassengerId;
    }

    public void setPassengerId(Long passengerId) {
        PassengerId = passengerId;
    }
}
