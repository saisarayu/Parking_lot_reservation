package com.parkinglot.parking_lot_reservation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

// Model for Reservation
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ParkingSlot slot;
// Vehicle number with validation
    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$", message = "Invalid vehicle number format")
    private String vehicleNumber;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private int totalCost;

    @Version
    private int version; // for optimistic locking
}
