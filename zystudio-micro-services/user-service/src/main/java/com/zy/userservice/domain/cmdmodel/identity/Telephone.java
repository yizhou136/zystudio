package com.zy.userservice.domain.cmdmodel.identity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author by zy.
 */
@Embeddable
public class Telephone implements Serializable {

    private static final long serialVersionUID = 1L;

    private String number;

    public Telephone() {
        super();
    }

    public Telephone(String aNumber) {
        this();

        this.setNumber(aNumber);
    }

    public Telephone(Telephone aTelephone) {
        this(aTelephone.number());
    }

    public String number() {
        return this.number;
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            Telephone typedObject = (Telephone) anObject;
            equalObjects = this.number().equals(typedObject.number());
        }

        return equalObjects;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
            + (35137 * 239)
            + this.number().hashCode();

        return hashCodeValue;
    }

    @Override
    public String toString() {
        return "Telephone [number=" + number + "]";
    }



    private void setNumber(String aNumber) {
        Objects.requireNonNull(aNumber, "Telephone number is required.");
        //this.assertArgumentLength(aNumber, 5, 20, "Telephone number may not be more than 20 characters.");
        Objects.requireNonNull(
                Pattern.matches("((\\(\\d{3}\\))|(\\d{3}-))\\d{3}-\\d{4}", aNumber),
                "Telephone number or its format is invalid.");

        this.number = aNumber;
    }
}
