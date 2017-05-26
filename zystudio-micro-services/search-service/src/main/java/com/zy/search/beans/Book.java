package com.zy.search.beans;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author by zy.
 */
@Document(indexName = "book")
public class Book {
    private String id;
    private String name;
    private double price;
    private long   ctime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
}
