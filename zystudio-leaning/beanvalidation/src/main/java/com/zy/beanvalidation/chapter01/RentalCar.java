package com.zy.beanvalidation.chapter01;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/7/29.
 */
public class RentalCar extends Car{

    private String rentalStation;

    @NotNull
    public String getRentalStation() {
        return rentalStation;
    }


    @NotNull
    @Override
    public String getManufacturer() {
        return super.getManufacturer();
    }
}
