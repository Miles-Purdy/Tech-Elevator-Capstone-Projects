package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class GiveChangeTest {


    @Test
    public void GiveChange_Dollars_And_Cents_Added_No_Purchase() {
        //Confirms that correct change is given even without any purchases

        // Arrange
        String filePathTest = "C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv";
        VendingMachine testVendingMachine = new VendingMachine(filePathTest);
        testVendingMachine.feedMoney("1"); //Adding 20 dollars to the funds
        testVendingMachine.feedMoney("6"); //Adding 0.10 dollars to the funds
        testVendingMachine.feedMoney("7"); //Adding 0.5 dollars to the funds

        // Act

        String actual =  testVendingMachine.giveChange();
        String expected= "Total Change: $20.15. Dispensed: 80 quarter(s), 1 dime(s) and 1 nickel(s)";
        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void GiveChange_Dollars_And_Cents_Added_Multiple_Purchases() {
        // Confirms that multiple purchases still gives the correct change

        // Arrange
        String filePathTest = "C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv";
        VendingMachine testVendingMachine = new VendingMachine(filePathTest);
        testVendingMachine.feedMoney("2"); //Adding 5 dollars to the funds

        testVendingMachine.dispenseItem("C4");  //Purchasing multiple items
        testVendingMachine.dispenseItem("A1");
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("B2");

        // Act
        String actual =  testVendingMachine.giveChange();
        String expected= "Total Change: $2.45. Dispensed: 9 quarter(s), 2 dime(s) and 0 nickel(s)";
        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void Current_Balance_Returned_Test(){
        // Confirms that all excess change is emptied from currentBalance once GiveChange is finished

        //Arrange
        String filePathTest = "C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv";
        VendingMachine testVendingMachine = new VendingMachine(filePathTest);
        testVendingMachine.feedMoney("7"); //Adding 20 dollars to the funds
        testVendingMachine.dispenseItem("C4");  //Getting heavy out here man.
        testVendingMachine.giveChange();

        //Act
        BigDecimal actual = testVendingMachine.getCurrentBalance();
        BigDecimal expected = new BigDecimal ("0.00");

        //Assert
        Assert.assertEquals(expected, actual);
    }


}
