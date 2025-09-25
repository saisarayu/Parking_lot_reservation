package com.parkinglot.parking_lot_reservation.repository;

import com.parkinglot.parking_lot_reservation.model.ParkingFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Repository for ParkingFloor entity
@Repository
public interface ParkingFloorRepository extends JpaRepository<ParkingFloor, Long> {
    boolean existsByFloorName(String floorName);
}
