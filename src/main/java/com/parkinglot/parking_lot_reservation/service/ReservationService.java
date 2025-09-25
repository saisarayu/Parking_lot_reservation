package com.parkinglot.parking_lot_reservation.service;

import com.parkinglot.parking_lot_reservation.exception.BadRequestException;
import com.parkinglot.parking_lot_reservation.exception.ResourceNotFoundException;
import com.parkinglot.parking_lot_reservation.model.*;
import com.parkinglot.parking_lot_reservation.repository.ParkingSlotRepository;
import com.parkinglot.parking_lot_reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepo;
    private final ParkingSlotRepository slotRepo;

    public ReservationService(ReservationRepository reservationRepo, ParkingSlotRepository slotRepo) {
        this.reservationRepo = reservationRepo;
        this.slotRepo = slotRepo;
    }

    /**
     * Reserve a parking slot
     */
    @Transactional
    public Reservation reserveSlot(Long slotId, String vehicleNumber, LocalDateTime start, LocalDateTime end) {

        // Validate times
        if (start.isAfter(end))
            throw new BadRequestException("Start time must be before end time");

        if (Duration.between(start, end).toHours() > 24)
            throw new BadRequestException("Reservation duration cannot exceed 24 hours");

        if (start.isBefore(LocalDateTime.now()))
            throw new BadRequestException("Start time cannot be in the past");

        // Fetch slot
        ParkingSlot slot = slotRepo.findById(slotId)
                .orElseThrow(() -> new ResourceNotFoundException("Slot not found with id: " + slotId));

        // Check vehicle type matches slot type
        if (!vehicleNumberIsValid(vehicleNumber))
            throw new BadRequestException("Invalid vehicle number format");

        // Check overlapping reservations
        List<Reservation> overlapping = reservationRepo.findBySlotAndEndTimeAfterAndStartTimeBefore(slot, start, end);
        if (!overlapping.isEmpty())
            throw new BadRequestException("Slot already booked for this time range");

        // Calculate duration (round up partial hours)
        int duration = (int) Math.ceil((double) Duration.between(start, end).toMinutes() / 60);
        int cost = duration * slot.getVehicleType().getRatePerHour();

        // Create reservation
        Reservation reservation = new Reservation();
        reservation.setSlot(slot);
        reservation.setVehicleNumber(vehicleNumber);
        reservation.setStartTime(start);
        reservation.setEndTime(end);
        reservation.setTotalCost(cost);

        return reservationRepo.save(reservation);
    }

    /**
     * Cancel a reservation
     */
    public void cancelReservation(Long id) {
        if (!reservationRepo.existsById(id))
            throw new ResourceNotFoundException("Reservation not found with id: " + id);
        reservationRepo.deleteById(id);
    }

    /**
     * Get reservation details
     */
    public Reservation getReservation(Long id) {
        return reservationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id: " + id));
    }

    /**
     * Validate vehicle number format
     */
    private boolean vehicleNumberIsValid(String vehicleNumber) {
        return vehicleNumber != null && vehicleNumber.matches("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$");
    }
}
