package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public List<ParkingLot> findParkingLots(int page, int pageSize) {
        return (page == 0 || pageSize == 0) ? findAll() : parkingLotRepository.findAll(PageRequest.of(page -1, pageSize)).getContent();
    }
}
