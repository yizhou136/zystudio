package com.zy.beanvalidation.chapter03;

import com.zy.beanvalidation.constraints.MaxAllowedFuelConsumption;
import com.zy.beanvalidation.constraints.ValidPart;
import com.zy.beanvalidation.constraints.ValidPassengerCount;
import com.zy.beanvalidation.constraints.ValidRacingCar;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Created by Administrator on 2017/7/29.
 */
@ValidPassengerCount
public class Car implements Vehicle{//}, Vehicle2{
    /*public enum FuelConsumption {
        CITY,
        HIGHWAY
    }*/

    @NotNull
    private String manufacturer;

    @NotNull
    @Size(min = 2, max = 14)
    private String licensePlate;

    @Min(2)
    private int seatCount;

    //@AssertTrue
    /*private boolean isRegistered;

    @Valid
    private List<@ValidPart String> parts;
    @Valid
    private Map<FuelConsumption, @MaxAllowedFuelConsumption Integer> fuelConsumption;
    */


    public Car(@NotNull String manufacturer) {
        this.manufacturer = manufacturer;
        System.out.println("manufacturer:");
    }

    @ValidRacingCar
    public Car(String manufacturer, String team) {
        this.manufacturer = manufacturer;
        System.out.println("team:"+team);
    }


    public void drive(@Max(80) int speedInMph) {
        System.out.println("drive speedInMph:"+speedInMph);
    }


    @Size(min = 2)
    public List<Passenger> getPassengers() {
        System.out.println("getPassengers:");
        return Collections.emptyList();
    }


    public Car(String manufacturer, String licencePlate, int seatCount) {
        this.manufacturer = manufacturer;
        this.licensePlate = licencePlate;
        this.seatCount = seatCount;
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