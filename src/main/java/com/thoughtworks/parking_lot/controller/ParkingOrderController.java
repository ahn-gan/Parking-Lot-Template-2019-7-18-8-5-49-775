package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.common.exception.CustomException;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking-orders")
public class ParkingOrderController {

    @Autowired
    ParkingOrderService parkingOrderService;

    @PutMapping("/{carNo}")
    public ResponseEntity updateOrder(@PathVariable String carNo) throws CustomException {
        return ResponseEntity.ok(parkingOrderService.updateOrder(carNo));
    }
}
