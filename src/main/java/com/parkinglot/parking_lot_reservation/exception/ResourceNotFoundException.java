package com.parkinglot.parking_lot_reservation.exception;
// Custom Exception for Resource Not Found
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
