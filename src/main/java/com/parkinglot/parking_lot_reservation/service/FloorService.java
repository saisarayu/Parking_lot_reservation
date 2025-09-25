package com.parkinglot.parking_lot_reservation.service;

import com.parkinglot.parking_lot_reservation.exception.BadRequestException;
import com.parkinglot.parking_lot_reservation.model.ParkingFloor;
import com.parkinglot.parking_lot_reservation.repository.ParkingFloorRepository;
import org.springframework.stereotype.Service;

// Service for managing ParkingFloor entities
@Service
public class FloorService {

    private final ParkingFloorRepository floorRepo;

    public FloorService(ParkingFloorRepository floorRepo) {
        this.floorRepo = floorRepo;
    }

    public ParkingFloor createFloor(ParkingFloor floor) {
        if(floorRepo.existsByFloorName(floor.getFloorName()))
            throw new BadRequestException("Floor name already exists");
        return floorRepo.save(floor);
    }
}
