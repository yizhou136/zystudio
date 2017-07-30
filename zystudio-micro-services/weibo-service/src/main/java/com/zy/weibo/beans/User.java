package com.zy.weibo.beans;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author by zy.
 */
public class User implements Serializable{

    @NotNull
    private Long uid;
    private String name;
    private String city;


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
