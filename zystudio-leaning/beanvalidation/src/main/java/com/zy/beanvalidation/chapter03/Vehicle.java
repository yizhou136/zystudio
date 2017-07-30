package com.zy.beanvalidation.chapter03;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 */
public interface Vehicle {

    void drive(@Max(80) int speedInMph);


    @NotNull
    List<Passenger> getPassengers();
}
