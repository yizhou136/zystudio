package com.zy.beanvalidation.chapter01;

import com.zy.beanvalidation.constraints.MaxAllowedFuelConsumption;
import com.zy.beanvalidation.constraints.ValidPart;
import com.zy.beanvalidation.constraints.ValidPassengerCount;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
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

    @Size(
        min = 2,
        max = 14,
        message = "The license plate '${validatedValue}' must be between {min} and {max} characters long"
    )
    private String licensePlate;

    @Min(
        value = 2,
        message = "There must be at least {value} seat${value > 1 ? 's' : ''}"
    )
    private int seatCount;

    @DecimalMax(
        value = "350",
        message = "The top speed ${formatter.format('%1$.2f', validatedValue)} is higher " +
                "than {value}"
    )
    private double topSpeed;

    @DecimalMax(value = "100000", message = "Price must not be higher than ${value}")
    private BigDecimal price;






    public Car(){
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

    public double getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(double topSpeed) {
        this.topSpeed = topSpeed;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
