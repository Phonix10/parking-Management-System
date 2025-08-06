package com.system.Parking_system.service;


import java.util.List;
import java.util.Map;

// import com.system.Parking_system.model.ParkingEntry;
import com.system.Parking_system.model.VehicleType;

public interface ParkingService {

    String enterVehicle(String vehicleNumber, VehicleType vehicleType);
    String exitVehicle(String vehicleNumber);
    Map<String, Long> getAvailableSlots();
    List<String> getAllParkedVehicles();

    
    
}
