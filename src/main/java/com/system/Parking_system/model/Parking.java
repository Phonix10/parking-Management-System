package com.system.Parking_system.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String vehicleNumber;

    @Column(nullable = false)
    private String vehicleType;

    @Column(nullable = false)
    private LocalDateTime entryTime;

    
    private LocalDateTime exitTime;
    
    
}
