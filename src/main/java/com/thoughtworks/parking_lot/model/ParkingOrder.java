package com.thoughtworks.parking_lot.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ParkingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "orderNo")
    private String orderNo;

    @ManyToOne
    @JoinColumn(name = "parkingLot_id", referencedColumnName = "id")
    private ParkingLot parkingLot;

    @Column(name = "carNo")
    private String carNo;

    @Column(name = "createdTime")
    private Date createdTime;

    @Column(name = "closedTime")
    private Date closedTime;

    @Column(name = "status", columnDefinition = "int default 1")
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getClosedTime() {
        return closedTime;
    }

    public void setClosedTime(Date closedTime) {
        this.closedTime = closedTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
