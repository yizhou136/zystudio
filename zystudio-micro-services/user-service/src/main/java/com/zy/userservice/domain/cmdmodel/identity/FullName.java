package com.zy.userservice.domain.cmdmodel.identity;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author by zy.
 */
@Embeddable
public class FullName implements Serializable{
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String secondName;

    public FullName(){}

    public FullName(String firstName, String secondName){
        this.firstName = firstName;
        this.secondName = secondName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
