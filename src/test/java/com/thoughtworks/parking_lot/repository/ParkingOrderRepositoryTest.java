package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.common.Constant;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ParkingOrderRepositoryTest {

    @Autowired
    ParkingOrderRepository parkingOrderRepository;

    @Autowired
    ParkingLotRepository parkingLotRepository;

    List<ParkingOrder> parkingOrders;

    @Before
    public void setUp() {
        parkingOrders = new ArrayList<>();
        ParkingLot parkingLot = parkingLotRepository.saveAndFlush(new ParkingLot("Lot-Name", 40, "Western"));
        parkingOrders.add(new ParkingOrder("No00001", parkingLot, "C3033", new Date(), new Date(119, 8, 2, 10, 20, 30), Constant.CLOSE_STATUS));
        parkingOrders.add(new ParkingOrder("No00002", parkingLot, "C4011", new Date(), new Date(119, 8, 5, 10, 20, 30), Constant.CLOSE_STATUS));
        parkingOrders.add(new ParkingOrder("No00003", parkingLot, "C5102", new Date(), new Date(119, 8, 6, 10, 20, 30), Constant.CLOSE_STATUS));
        parkingOrderRepository.saveAll(parkingOrders);
    }

    @Test
    @Transactional
    @DirtiesContext
    public void should_return_the_parking_lots_when_find_it_by_name() {
        ParkingOrder parkingOrder = parkingOrderRepository.findByCarNo("C5102");
        Assertions.assertEquals("C5102", parkingOrder.getCarNo());
    }

}