package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.common.Constant;
import com.thoughtworks.parking_lot.common.exception.CustomException;
import com.thoughtworks.parking_lot.dto.OrderDto;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class ParkingOrderService {

    @Autowired
    ParkingOrderRepository parkingOrderRepository;

    @Autowired
    ParkingLotRepository parkingLotRepository;

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

    @Transactional
    public ParkingOrder addOrder(OrderDto orderDto) throws CustomException {
        if (!orderDto.getCarNo().isEmpty() && !orderDto.getParkingLotName().isEmpty()) {
            ParkingLot parkingLot = parkingLotRepository.findByName(orderDto.getParkingLotName());
            if (null != parkingLot && parkingLot.getCapacity() > 0) {
                ParkingOrder parkingOrder = new ParkingOrder();
                parkingOrder.setCarNo(orderDto.getCarNo());
                parkingOrder.setStatus(Constant.OPEN_STATUS);
                parkingOrder.setCreatedTime(new Date());
                parkingOrder.setParkingLot(parkingLot);
                parkingLot.setCapacity(parkingLot.getCapacity() - 1);
                parkingLotRepository.save(parkingLot);
                return parkingOrderRepository.saveAndFlush(parkingOrder);
            } else {
                throw new CustomException("Can't create the ParkingOrder information as the capacity has full.");
            }
        } else
            throw new CustomException("Empty CarNo / parkingLot name, please check and try again.");
    }
}
