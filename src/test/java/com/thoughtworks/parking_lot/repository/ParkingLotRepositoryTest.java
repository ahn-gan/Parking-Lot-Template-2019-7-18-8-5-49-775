package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.ParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ParkingLotRepositoryTest {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    List<ParkingLot> parkingLots;

    @Before
    public void setUp() {
        parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot("parkingLotName-1", 20, "northern"));
        parkingLots.add(new ParkingLot("parkingLotName-2", 30, "western"));
        parkingLots.add(new ParkingLot("parkingLotName-3", 40, "eastern"));
        parkingLotRepository.saveAll(parkingLots);
    }

    @Test
    @DirtiesContext
    public void should_return_the_parking_lots_when_find_it_by_name() {
        ParkingLot parkingLot = parkingLotRepository.findByName("parkingLotName-1");
        Assertions.assertEquals(parkingLot.getCapacity(), 20);
    }
}