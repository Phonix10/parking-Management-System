package com.system.Parking_system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.system.Parking_system.model.VehicleType;
import com.system.Parking_system.service.ParkingService;

@Controller
@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/entry")
    public String enterVehicle(
            @RequestParam String vehicleNumber,
            @RequestParam VehicleType vehicleType) {
        return parkingService.enterVehicle(vehicleNumber, vehicleType);
    }

   @PostMapping("/exit/{vehicleNumber}")
   public String exitVehicle(@PathVariable String vehicleNumber) {
       return parkingService.exitVehicle(vehicleNumber);
   }
    
    

    @GetMapping("/slots")
    public Map<String, Long> getAvailableSlots() {
        return parkingService.getAvailableSlots();
    }

    // Additional method to get all parked vehicles
    @GetMapping("/parked-vehicles")
    public ResponseEntity<?> getAllParkedVehicles() {
        return ResponseEntity.ok(parkingService.getAllParkedVehicles());
    }

    
}
