package com.techelevator;

import java.math.BigDecimal;

public abstract class Item {
    //properties
    BigDecimal cost;
    String name;
    String message;

    //constructors

    public Item(String name, BigDecimal cost) {
        this.name = name;
        this.cost = cost;
    }

    //getters/setters

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public String getMessage() {
        return message;
    }










}
