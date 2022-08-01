package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class VendingMachineTest {

    @Test
    public void Vending_Machine_Correct_Name_C4_Test() {

        // Arrange
        String filePathTest = "C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv";
        VendingMachine testMachine = new VendingMachine(filePathTest);

        // Act
        String actual = testMachine.getInventory().get("C4").peek().getName();
        String expected = "Heavy";

        // Assert

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void Vending_Machine_Correct_Quantity_C4_Test() {

        // Arrange
        String filePathTest = "C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv";
        VendingMachine testMachine = new VendingMachine(filePathTest);

        // Act
        int actual = testMachine.getInventory().get("C4").size();
        int expected = 5;


        // Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void Vending_Machine_Correct_Cost_C4_Test() {
        // Arrange
        String filePathTest = "C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv";
        VendingMachine testMachine = new VendingMachine(filePathTest);

        // Act
        BigDecimal actual = testMachine.getInventory().get("C4").peek().getCost();
        BigDecimal expected = new BigDecimal(1.50);
        int comparison = actual.compareTo(expected);


        // Assert
        Assert.assertEquals(0, comparison);
    }

    @Test
    public void Construct_Creates_Inventory_Map_With_Correct_Number_Of_Entries() {

        // Arrange
        String filePathTest = "C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv";
        VendingMachine testMachine = new VendingMachine(filePathTest);

        // Act
        int actual = testMachine.getInventory().size();
        int expected = 16;


        // Assert
        Assert.assertEquals(expected, actual);
    }

    //Adds money to current balance based on customer's selection on menu displayed in main method
    /*
                (1) Insert $20 bill
                (2) Insert $10 bill
                (3) Insert $5 bill
                (4) Insert $1 bill
                (5) Insert Quarter
                (6) Insert Dime
                (7) Insert Nickel
                (8) Done adding money

     */
    @Test
    public void Feed_Me_Your_Money_20_Bill() {

        // Arrange
        String filePathTest = "C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv";
        VendingMachine testMachine = new VendingMachine(filePathTest);
        testMachine.feedMoney("1");

        // Act
        BigDecimal actual = testMachine.getCurrentBalance();
        BigDecimal expected = new BigDecimal(20.00);
        int comparison = actual.compareTo(expected);

        // Assert
        Assert.assertEquals(0, comparison);

    }
    @Test
    public void Feed_Me_Your_Money_Quarter() {

        // Arrange
        String filePathTest = "C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv";
        VendingMachine testMachine = new VendingMachine(filePathTest);
        testMachine.feedMoney("5");

        // Act
        BigDecimal actual = testMachine.getCurrentBalance();
        BigDecimal expected = new BigDecimal(.25);
        int comparison = actual.compareTo(expected);

        // Assert
        Assert.assertEquals(0, comparison);

    }




}
