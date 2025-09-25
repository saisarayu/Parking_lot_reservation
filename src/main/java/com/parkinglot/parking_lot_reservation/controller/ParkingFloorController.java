package com.parkinglot.parking_lot_reservation.controller;

import com.parkinglot.parking_lot_reservation.model.ParkingFloor;
import com.parkinglot.parking_lot_reservation.service.FloorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Controller for Parking Floor operations
@RestController
@RequestMapping("/floors")
public class ParkingFloorController {

    private final FloorService floorService;

    public ParkingFloorController(FloorService floorService) {
        this.floorService = floorService;
    }
// Create a new parking floor
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingFloor createFloor(@Valid @RequestBody ParkingFloor floor) {
        return floorService.createFloor(floor);
    }
}
