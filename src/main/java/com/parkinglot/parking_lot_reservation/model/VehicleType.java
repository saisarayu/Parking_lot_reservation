package com.parkinglot.parking_lot_reservation.model;


// Enum for Vehicle Types with Rates
public enum VehicleType {
    TWO_WHEELER(20),
    FOUR_WHEELER(30);

    private final int ratePerHour;

    VehicleType(int ratePerHour) {
        
        this.ratePerHour = ratePerHour;
    }

    public int getRatePerHour() {
        return ratePerHour;
    }
}
