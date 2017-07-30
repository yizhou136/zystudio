package com.zy.beanvalidation.chapter03;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/29.
 */
public class RentalStation {
    public RentalStation(@NotNull String name) {
    }

    public void rentCar(
            @NotNull Customer customer,
            @NotNull @Future Date startDate,
            @Min(1) int durationInDays) {
    }
}
