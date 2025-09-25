package com.parkinglot.parking_lot_reservation.exception;
// Custom Exception for Bad Requests
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
