package com.techelevator;

import java.math.BigDecimal;

public class Chips extends Item{

    public Chips(String name, BigDecimal cost) {
        super(name, cost);
        message = "Crunch Crunch, Yum!!";
    }
}
