package com.parkinglot.parking_lot_reservation.repository;

import com.parkinglot.parking_lot_reservation.model.ParkingSlot;
import com.parkinglot.parking_lot_reservation.model.ParkingFloor;
import com.parkinglot.parking_lot_reservation.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
// Repository for ParkingSlot entity
@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    List<ParkingSlot> findByVehicleType(VehicleType vehicleType);
    boolean existsBySlotNumberAndFloor(String slotNumber, ParkingFloor floor);
}
