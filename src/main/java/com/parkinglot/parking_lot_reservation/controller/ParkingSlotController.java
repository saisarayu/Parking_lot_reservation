package com.parkinglot.parking_lot_reservation.controller;

import com.parkinglot.parking_lot_reservation.model.ParkingSlot;
import com.parkinglot.parking_lot_reservation.service.SlotService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

// Controller for Parking Slot operations
@RestController
@RequestMapping("/slots")
public class ParkingSlotController {

    private final SlotService slotService;

    public ParkingSlotController(SlotService slotService) {
        this.slotService = slotService;
    }
// Creating a new parking slot
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public ParkingSlot createSlot(@Valid @RequestBody ParkingSlot slot) {
        return slotService.createSlot(slot);
    }
// Getting all parking slots
    @GetMapping
    public List<ParkingSlot> getAllSlots() {
        return slotService.getAllSlots();
    }

    @GetMapping("/{id}")
    public ParkingSlot getSlotById(@PathVariable Long id) {
        return slotService.getSlotById(id);
    }
}
