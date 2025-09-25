package com.parkinglot.parking_lot_reservation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

// Model for Parking Slot
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"slotNumber", "floor_id"})})
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Slot number is required")
    private String slotNumber;

    @ManyToOne
    private ParkingFloor floor;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
}
