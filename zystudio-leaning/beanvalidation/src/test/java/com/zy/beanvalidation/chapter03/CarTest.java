package com.zy.beanvalidation.chapter03;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Administrator on 2017/7/29.
 */
public class CarTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void manufacturerIsNull() {
        RentalStation rentalStation = new RentalStation("2");
        Set<ConstraintViolation<RentalStation>> constraintViolations = validator.validate(rentalStation);
        assertEquals(0, constraintViolations.size());

        rentalStation.rentCar(null, new Date(), 3);
        constraintViolations = validator.validate(rentalStation);
        assertEquals(1, constraintViolations.size());
    }

}
