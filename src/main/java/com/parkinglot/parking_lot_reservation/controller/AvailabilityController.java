package com.parkinglot.parking_lot_reservation.controller;

import com.parkinglot.parking_lot_reservation.model.ParkingSlot;
import com.parkinglot.parking_lot_reservation.model.Reservation;
import com.parkinglot.parking_lot_reservation.repository.ParkingSlotRepository;
import com.parkinglot.parking_lot_reservation.repository.ReservationRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//  New Controller for Availability
@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    private final ParkingSlotRepository slotRepo;
    private final ReservationRepository reservationRepo;

    public AvailabilityController(ParkingSlotRepository slotRepo, ReservationRepository reservationRepo) {
        this.slotRepo = slotRepo;
        this.reservationRepo = reservationRepo;
    }
//  GET available slots within a time range
    @GetMapping
    public List<ParkingSlot> getAvailableSlots(@RequestParam String start, @RequestParam String end) {
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);

        List<ParkingSlot> allSlots = slotRepo.findAll();

        return allSlots.stream().filter(slot -> {
            List<Reservation> overlapping = reservationRepo.findBySlotAndEndTimeAfterAndStartTimeBefore(slot, startTime, endTime);
            return overlapping.isEmpty();
        }).collect(Collectors.toList());
    }
}
