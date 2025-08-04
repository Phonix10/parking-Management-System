package com.system.Parking_system.service;

public interface ParkingService {
    // void EntryVechicle(String vehicleNumber, String vehicleType , String entryTime);

    void EntryVechicle(String vehicleNumber, String vehicleType);

    void ExitVechicle(String vehicleNUmber);

    void GetAllVechicle();

    void GetallVechicleByType(String vehicleType);

    void GetBynumber(String vehicleNumber);

    
}
