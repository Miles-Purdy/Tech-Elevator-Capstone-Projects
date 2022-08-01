package com.techelevator;

import java.math.BigDecimal;

public class Beverage extends Item{

    public Beverage(String name, BigDecimal cost) {
        super(name, cost);
        message = "Slurp Slurp, Yum!!";
    }
}
