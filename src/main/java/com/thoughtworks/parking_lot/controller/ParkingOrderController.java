package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.common.exception.CustomException;
import com.thoughtworks.parking_lot.dto.OrderDto;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-orders")
public class ParkingOrderController {

    @Autowired
    ParkingOrderService parkingOrderService;

    @PutMapping("/{carNo}")
    public ResponseEntity updateOrder(@PathVariable String carNo) throws CustomException {
        return ResponseEntity.ok(parkingOrderService.updateOrder(carNo));
    }

    @PostMapping
    public ResponseEntity addOrder(@RequestBody OrderDto orderDto) throws CustomException {
        return ResponseEntity.ok(parkingOrderService.addOrder(orderDto));
    }
}
