package com.parkinglot.parking_lot_reservation.service;

import com.parkinglot.parking_lot_reservation.exception.BadRequestException;
import com.parkinglot.parking_lot_reservation.exception.ResourceNotFoundException;
import com.parkinglot.parking_lot_reservation.model.ParkingSlot;
import com.parkinglot.parking_lot_reservation.repository.ParkingSlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
// Service for managing ParkingSlot entities
@Service
public class SlotService {

    private final ParkingSlotRepository slotRepo;

    public SlotService(ParkingSlotRepository slotRepo) {
        this.slotRepo = slotRepo;
    }

    public ParkingSlot createSlot(ParkingSlot slot) {
        if(slotRepo.existsBySlotNumberAndFloor(slot.getSlotNumber(), slot.getFloor()))
            throw new BadRequestException("Slot number already exists on this floor");
        return slotRepo.save(slot);
    }

    public List<ParkingSlot> getAllSlots() {
        return slotRepo.findAll();
    }

    public ParkingSlot getSlotById(Long id) {
        return slotRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Slot not found with id: " + id));
    }
}
