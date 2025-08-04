package com.system.Parking_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.Parking_system.model.ParkingEntry;
import com.system.Parking_system.model.VehicleType;


@Repository
public interface ParkingRepository extends JpaRepository<ParkingEntry,Long> {
    Optional<ParkingEntry> findByVehicleNumberAndExitTimeIsNull(String vehicleNumber);
    long countByVehicleTypeAndExitTimeIsNull(VehicleType type);
    
}
