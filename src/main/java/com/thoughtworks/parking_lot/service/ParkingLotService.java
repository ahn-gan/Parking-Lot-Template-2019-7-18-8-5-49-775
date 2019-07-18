package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    public ParkingLot addParkingLot(ParkingLot parkingLot) {
       return parkingLotRepository.save(parkingLot);
    }

    @Transactional
    public void removeParkingLot(String parkingLotName) {
        ParkingLot parkingLot = parkingLotRepository.findByName(parkingLotName);
        if (null !=  parkingLot)
            parkingLotRepository.delete(parkingLot);
    }

    public List<ParkingLot> findAll() {
        return parkingLotRepository.findAll();
    }
}
