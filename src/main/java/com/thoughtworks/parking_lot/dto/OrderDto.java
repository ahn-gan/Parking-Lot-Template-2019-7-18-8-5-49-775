package com.thoughtworks.parking_lot.dto;

public class OrderDto {

    private String carNo;

    private String parkingLotName;

    public OrderDto() {
    }

    public OrderDto(String carNo, String parkingLotName) {
        this.carNo = carNo;
        this.parkingLotName = parkingLotName;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }
}
