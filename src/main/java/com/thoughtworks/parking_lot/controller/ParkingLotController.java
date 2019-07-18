package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-lots")
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @PostMapping
    public ResponseEntity addParkingLot(@RequestBody ParkingLot parkingLot) {
        return ResponseEntity.ok(parkingLotService.addParkingLot(parkingLot));
    }

    @DeleteMapping("/{parkingLotName}")
    public ResponseEntity removeParkingLot(@PathVariable String parkingLotName) {
        parkingLotService.removeParkingLot(parkingLotName);
        return ResponseEntity.ok(parkingLotService.findAll());
    }

    @GetMapping
    public ResponseEntity findParkingLots(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "pageSize", defaultValue = "0") int pageSize) {
        return ResponseEntity.ok(parkingLotService.findParkingLots(page, pageSize));
    }
}
