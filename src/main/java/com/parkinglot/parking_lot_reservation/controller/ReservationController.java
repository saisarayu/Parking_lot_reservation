package com.parkinglot.parking_lot_reservation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.parkinglot.parking_lot_reservation.model.Reservation;
import com.parkinglot.parking_lot_reservation.service.ReservationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // Reserve a slot
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation reserve(@Valid @RequestBody ReservationRequest request) {
        return reservationService.reserveSlot(
                request.getSlotId(),
                request.getVehicleNumber(),
                request.getStartTime(),
                request.getEndTime()
        );
    }

    // Get reservation details
    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }

    // Cancel a reservation
    @DeleteMapping("/{id}")
    public String cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return "Reservation cancelled successfully!";
    }

    /**
     * DTO for reservation request
     */
    public static class ReservationRequest {

        @NotNull(message = "Slot ID is required")
        private Long slotId;

        @NotBlank(message = "Vehicle number is required")
        @Pattern(regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$",
                 message = "Invalid vehicle number format (e.g., KA05MH1234)")
        private String vehicleNumber;

        @NotNull(message = "Start time is required")
        private LocalDateTime startTime;

        @NotNull(message = "End time is required")
        private LocalDateTime endTime;

        // Getters & Setters
        public Long getSlotId() { return slotId; }
        public void setSlotId(Long slotId) { this.slotId = slotId; }

        public String getVehicleNumber() { return vehicleNumber; }
        public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

        public LocalDateTime getStartTime() { return startTime; }
        public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

        public LocalDateTime getEndTime() { return endTime; }
        public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    }
}
