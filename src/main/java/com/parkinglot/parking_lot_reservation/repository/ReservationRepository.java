package com.parkinglot.parking_lot_reservation.repository;

import com.parkinglot.parking_lot_reservation.model.Reservation;
import com.parkinglot.parking_lot_reservation.model.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

// Repository for Reservation entity
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findBySlotAndEndTimeAfterAndStartTimeBefore(ParkingSlot slot, LocalDateTime startTime, LocalDateTime endTime);
}
