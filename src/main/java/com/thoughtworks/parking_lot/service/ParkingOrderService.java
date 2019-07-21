package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.common.Constant;
import com.thoughtworks.parking_lot.common.exception.CustomException;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class ParkingOrderService {

    @Autowired
    ParkingOrderRepository parkingOrderRepository;

    @Transactional
    public ParkingOrder updateOrder(String carNo) throws CustomException {
        if (!carNo.isEmpty()) {
            ParkingOrder parkingOrder = parkingOrderRepository.findByCarNo(carNo);
            if (parkingOrder != null && parkingOrder.getStatus() == Constant.OPEN_STATUS) {
                parkingOrder.setStatus(Constant.CLOSE_STATUS);
                parkingOrder.setClosedTime(new Date());
                parkingOrderRepository.save(parkingOrder);
                return parkingOrder;
            } else {
                throw new CustomException("Invalid CarNo.");
            }
        } else
            throw new CustomException("Empty CarNo, please check and try again.");
    }
}
