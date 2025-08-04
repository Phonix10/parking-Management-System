package com.system.Parking_system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    
}
