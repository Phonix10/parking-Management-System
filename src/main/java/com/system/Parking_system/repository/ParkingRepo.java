package com.system.Parking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.Parking_system.model.Parking;

public interface ParkingRepo extends JpaRepository<Parking,String>{
    
}
