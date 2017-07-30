package com.zy.beanvalidation.chapter01;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.*;

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
        Car car = new Car( null, "DD-AB-123", 4 );

        car.addPart(null);
        Set<ConstraintViolation<Car>> constraintViolations =
                validator.validate( car );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "may not be null",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void licensePlateTooShort() {
        Car car = new Car("Morris", "D", 4);

        Set<ConstraintViolation<Car>> constraintViolations =
                validator.validate( car );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "size must be between 2 and 14",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void seatCountTooLow() {
        Car car = new Car( "Morris", "DD-AB-123", 1 );

        Set<ConstraintViolation<Car>> constraintViolations =
                validator.validate( car );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "must be greater than or equal to 2",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void carIsValid() {
        Car car = new Car( "Morris", "DD-AB-123", 2 );

        Set<ConstraintViolation<Car>> constraintViolations =
                validator.validate( car );

        assertEquals( 0, constraintViolations.size() );
    }


    @Test
    public void validateMapValueContainerElementConstraint() {
        //tag::validateMapValueContainerElementConstraint[]
        Car car = new Car();
        //car.setManufacturer("abc");
        //car.setSeatCount(1);
        //car.setFuelConsumption(Car.FuelConsumption.HIGHWAY, 20);

        Set<ConstraintViolation<Car>> constraintViolations = validator.validateProperty(car, "manufacturer");

        constraintViolations = validator.validateValue(Car.class, "manufacturer", "");

        assertEquals(1, constraintViolations.size() );

        ConstraintViolation<Car> constraintViolation =
                constraintViolations.iterator().next();
        System.out.println(constraintViolation.getMessage());


        /*assertEquals(
                "20 is outside the max fuel consumption.",
                constraintViolation.getMessage()
        );
        assertEquals(
                "fuelConsumption[HIGHWAY].<map value>",
                constraintViolation.getPropertyPath().toString()
        );*/
        //end::validateMapValueContainerElementConstraint[]
    }

    @Test
    public void validateSuperClass() {
        Car car = new Car();
        //car.setSeatCount(1);
        //car.setFuelConsumption(Car.FuelConsumption.HIGHWAY, 20);
        Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);
        System.out.println(constraintViolations);
        assertEquals(4, constraintViolations.size() );


        RentalCar rentalCar = new RentalCar();
        //car.setSeatCount(1);
        //car.setFuelConsumption(Car.FuelConsumption.HIGHWAY, 20);
        constraintViolations = validator.validate(rentalCar);
        assertEquals(5, constraintViolations.size() );


        Car superCar = new RentalCar();
        //car.setSeatCount(1);
        //car.setFuelConsumption(Car.FuelConsumption.HIGHWAY, 20);
        constraintViolations = validator.validate(superCar);
        assertEquals(5, constraintViolations.size() );

        //end::validateMapValueContainerElementConstraint[]
    }
}
