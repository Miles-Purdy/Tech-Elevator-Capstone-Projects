package com.techelevator;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class FeedMoneyTest {

    @Test
    public void FeedMoney_Single_Input_Test() {
        //Confirms that a single input correctly updates currentBalance

        // Arrange
        String filePathTest = "C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv";
        VendingMachine testVendingMachine = new VendingMachine(filePathTest);
        String denominationChoice = "5";  //Choice 1 is to add $0.25
        // Act

        testVendingMachine.feedMoney(denominationChoice);

        BigDecimal actual = testVendingMachine.getCurrentBalance();
        BigDecimal expected = new BigDecimal("0.25");

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void FeedMoney_Invalid_Input() {
        //Confirms that an invalid input will not affect the currentBalance

        // Arrange
        String filePathTest = "C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv";
        VendingMachine testVendingMachine = new VendingMachine(filePathTest);
        String denominationChoice = "Joe Mama";  //Choice 8 is to exit Feed Money menu
        // Act

        testVendingMachine.feedMoney(denominationChoice);

        BigDecimal actual = testVendingMachine.getCurrentBalance();
        BigDecimal expected = new BigDecimal("0");

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void FeedMoney_Multiple_Inputs() {
        //Confirms that multiple FeedMoney inputs will correctly add to the total in currentBalance

        // Arrange
        String filePathTest = "C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv";
        VendingMachine testVendingMachine = new VendingMachine(filePathTest);
        String denominationChoiceOne = "1"; //Adds 20.00
        String denominationChoiceTwo = "2"; //Adds 10.00
        String denominationChoiceThree = "3"; //Adds 5.00

        // Act

        testVendingMachine.feedMoney(denominationChoiceOne);
        testVendingMachine.feedMoney(denominationChoiceTwo);
        testVendingMachine.feedMoney(denominationChoiceThree);

        BigDecimal actual = testVendingMachine.getCurrentBalance();
        BigDecimal expected = new BigDecimal("35.00");

        // Assert
        Assert.assertEquals(expected, actual);
    }

}


