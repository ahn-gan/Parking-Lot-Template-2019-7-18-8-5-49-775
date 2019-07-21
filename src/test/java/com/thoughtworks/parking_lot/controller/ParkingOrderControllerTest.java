package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.common.Constant;
import com.thoughtworks.parking_lot.common.exception.CustomException;
import com.thoughtworks.parking_lot.dto.OrderDto;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.service.ParkingOrderService;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ParkingOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ParkingOrderService parkingOrderService;

    ParkingLot parkingLot;

    @Before
    public void setUp() {
        parkingLot = new ParkingLot("Lot-Name", 40, "Western");
    }

    @Test
    public void should_return_order_information_with_close_status_when_request_to_update() throws Exception {
        ParkingOrder parkingOrder = new ParkingOrder("No00001", parkingLot, "C3033", new Date(), new Date(119, 8, 2, 10, 20, 30), Constant.CLOSE_STATUS);
        parkingOrder.setId(1);

        Mockito.when(parkingOrderService.updateOrder(Mockito.anyString())).thenReturn(parkingOrder);

        mockMvc.perform(put("/parking-orders/{carNo}", "C3033")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(0));
    }

    @Test
    public void should_create_a_new_order_when_the_parking_lot_has_position () throws Exception {
        ParkingOrder parkingOrder = new ParkingOrder("No00001", parkingLot, "C5230", new Date(), new Date(119, 8, 2, 10, 20, 30), Constant.OPEN_STATUS);

        Mockito.when(parkingOrderService.addOrder(Mockito.any())).thenReturn(parkingOrder);

        mockMvc.perform(post("/parking-orders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "\t\"carNo\": \"C5230\",\n" +
                        "\t\"parkingLotName\": \"Lot-Name\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(1));
    }

    @Ignore
    @Test(expected = CustomException.class)
    public void should_return_exception_when_the_parking_lot_no_position_to_create_parking_order () throws Exception {
        Mockito.when(parkingOrderService.addOrder(Mockito.any())).thenThrow(new CustomException("Can't create the ParkingOrder information as the capacity has full."));
        mockMvc.perform(post("/parking-orders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "\t\"carNo\": \"C5230\",\n" +
                        "\t\"parkingLotName\": \"Lot-Name\"\n" +
                        "}"))
                .andReturn();
//                .andExpect((ResultMatcher) new CustomException("Can't create the ParkingOrder information as the capacity has full."));

//        ExpectedException expectedException = ExpectedException.none();
//        expectedException.expect(CustomException.class);
//        expectedException.expectMessage("Can't create the ParkingOrder information as the capacity has full.");
//        given(parkingOrderService.addOrder(Mockito.any())).willThrow(new CustomException("Can't create the ParkingOrder information as the capacity has full."));

    }
}