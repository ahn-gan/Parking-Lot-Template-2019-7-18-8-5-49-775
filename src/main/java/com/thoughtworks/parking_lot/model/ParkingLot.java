package com.thoughtworks.parking_lot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
public class ParkingLot {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", unique = true, length = 30)
    private String name;

    @Column(name = "capacity", length = 6)
    @Min(0)
    private int capacity;

    @Column(name = "position")
    private String position;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
