package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Item{

    public Candy(String name, BigDecimal cost) {
        super(name, cost);
        message = "Munch Munch, Yum!!";
    }
}
