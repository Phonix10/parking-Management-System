package com.system.Parking_system.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.Parking_system.model.ParkingEntry;
import com.system.Parking_system.model.VehicleType;
import com.system.Parking_system.repository.ParkingRepository;


import java.time.Duration;


@Service
public class ParkingServiceimpl implements ParkingService{

    private static final int MAX_TWO_WHEELER = 60;
    private static final int MAX_FOUR_WHEELER = 30;

    @Autowired
    private ParkingRepository parkingRepository;


    @Override
    public String enterVehicle(String vehicleNumber, VehicleType vehicleType) {
        if (vehicleType == null || vehicleNumber == null || vehicleNumber.isEmpty()){
            return "Invalid vehicle type or number";
        }
        if (parkingRepository.findByVehicleNumberAndExitTimeIsNull(vehicleNumber).isPresent()){
            return "Vehicle with number" + vehicleNumber + " is already parked.";
        }

        long currentCount = parkingRepository.countByVehicleTypeAndExitTimeIsNull(vehicleType);

        if((vehicleType == VehicleType.TWO_WHEELER && currentCount >= MAX_TWO_WHEELER)||
        (vehicleType == VehicleType.FOUR_WHEELER && currentCount >= MAX_FOUR_WHEELER))
        {
            return "Parking full for " + vehicleType + " . ";
        }
        ParkingEntry entry = ParkingEntry.builder()
                .vehicleNumber(vehicleNumber)
                .vehicleType(vehicleType)
                .entryTime(LocalDateTime.now())
                .build();

        parkingRepository.save(entry);
        return "Vehicle entered.";
    }

    @Override
    public String exitVehicle(String vehicleNumber) {
        ParkingEntry entry = parkingRepository
                .findByVehicleNumberAndExitTimeIsNull(vehicleNumber)
                .orElse(null);

        if (entry == null) return "Vehicle not found or already exited.";

        LocalDateTime exitTime = LocalDateTime.now();
        long minutes = Duration.between(entry.getEntryTime(), exitTime).toMinutes();
        long charge = calculateCharge(minutes, entry.getVehicleType());

        entry.setExitTime(exitTime);
        entry.setCharge(charge);

        parkingRepository.save(entry);
        return "Vehicle exited. Total charge: ₹" + charge;
    }

    

    private long calculateCharge(long minutes, VehicleType type) {
        long hours = (long) Math.ceil(minutes / 60.0);

        if (type == VehicleType.TWO_WHEELER) {
            return hours <= 2 ? 10 : 10 + (hours - 2) * 4;
        } else {
            return hours <= 2 ? 20 : 20 + (hours - 2) * 7;
        }
    }
    

    @Override
    public Map<String, Long> getAvailableSlots() {
        long twoWheelerCount = parkingRepository.countByVehicleTypeAndExitTimeIsNull(VehicleType.TWO_WHEELER);
        long fourWheelerCount = parkingRepository.countByVehicleTypeAndExitTimeIsNull(VehicleType.FOUR_WHEELER);

        Map<String, Long> slots = new HashMap<>();
        slots.put("2-wheeler", MAX_TWO_WHEELER - twoWheelerCount);
        slots.put("4-wheeler", MAX_FOUR_WHEELER - fourWheelerCount);
        return slots;
    }

    
    @Override
    public List<String> getAllParkedVehicles(){
        return parkingRepository.findAll().stream().
                filter(entry -> entry.getExitTime() == null)
                .map(ParkingEntry::getVehicleNumber)
                .toList();
        
    }



    
    
}
