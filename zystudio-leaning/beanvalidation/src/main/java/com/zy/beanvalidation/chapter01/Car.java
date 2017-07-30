package com.zy.beanvalidation.chapter01;

import com.zy.beanvalidation.constraints.MaxAllowedFuelConsumption;
import com.zy.beanvalidation.constraints.ValidPart;
import com.zy.beanvalidation.constraints.ValidPassengerCount;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/29.
 */
@ValidPassengerCount
public class Car {
    public enum FuelConsumption {
        CITY,
        HIGHWAY
    }

    @NotNull
    private String manufacturer;

    @NotNull
    @Size(min = 2, max = 14)
    private String licensePlate;

    @Min(2)
    private int seatCount;

    //@AssertTrue
    private boolean isRegistered;

    @Valid
    private List<@ValidPart String> parts;
    @Valid
    private Map<FuelConsumption, @MaxAllowedFuelConsumption Integer> fuelConsumption;


    public Car(){
        this.parts = new ArrayList<>();
        this.fuelConsumption = new HashMap<>();
    }

    public Car(String manufacturer) {
        this(manufacturer, "", 2);
    }

    public Car(String manufacturer, String licencePlate, int seatCount) {
        this();
        this.manufacturer = manufacturer;
        this.licensePlate = licencePlate;
        this.seatCount = seatCount;
    }

    public List<String> getParts() {
        return parts;
    }

    public void setParts(List<String> parts) {
        this.parts = parts;
    }

    public void setFuelConsumption(FuelConsumption consumption, int value) {
        fuelConsumption.put(consumption, value);
    }

    public void addPart(String part){
        parts.add(part);
    }

    //@NotNull
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
}
