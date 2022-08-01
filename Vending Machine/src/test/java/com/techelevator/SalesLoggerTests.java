package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SalesLoggerTests {

    @Test
    public void feed_money_is_recorded_in_log(){

        //Arrange

        VendingMachine testVendingMachine = new VendingMachine("C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv");
        String salesLogFilepath = System.getProperty("java.io.tmpdir") + "\\SalesLog.txt";
        //Feed $20 bill into machine
        testVendingMachine.feedMoney("1");

        File salesLogFile = new File(salesLogFilepath);
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        //Act


        SalesLogger.logFeedMoney(new BigDecimal("20.00"), testVendingMachine.getCurrentBalance(), timeStamp);

        String lastLineInFile = null;

        try(Scanner fileReader = new Scanner(salesLogFile)){

            while (fileReader.hasNextLine()){

                lastLineInFile = fileReader.nextLine();

            }

        }
        catch (FileNotFoundException e){

        }

        String actual = timeStamp + " FEED MONEY: $20.00 $20.00";
        String expected = lastLineInFile;

        //Assert

        Assert.assertEquals(actual, expected);

    }


    @Test
    public void feed_money_multiple_times_is_recorded_in_log(){

        //Arrange

        VendingMachine testVendingMachine = new VendingMachine("C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv");
        String salesLogFilepath = System.getProperty("java.io.tmpdir") + "\\SalesLog.txt";
        //Feed $20 bill into machine
        testVendingMachine.feedMoney("1");
        testVendingMachine.feedMoney("3");

        File salesLogFile = new File(salesLogFilepath);
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        //Act


        SalesLogger.logFeedMoney(new BigDecimal("5.00"), testVendingMachine.getCurrentBalance(), timeStamp);

        String lastLineInFile = null;

        try(Scanner fileReader = new Scanner(salesLogFile)){

            while (fileReader.hasNextLine()){

                lastLineInFile = fileReader.nextLine();

            }

        }
        catch (FileNotFoundException e){

        }

        String actual = timeStamp + " FEED MONEY: $5.00 $25.00";
        String expected = lastLineInFile;

        //Assert

        Assert.assertEquals(actual, expected);

    }

    @Test
    public void puchase_is_recorded_in_log(){

        //Arrange

        VendingMachine testVendingMachine = new VendingMachine("C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv");
        String salesLogFilepath = System.getProperty("java.io.tmpdir") + "\\SalesLog.txt";

        //Feed $20 bill into machine
        testVendingMachine.feedMoney("1");

        //Dispense item at slot "C4"
        String slotSelected = "C4";
        String name = testVendingMachine.inventory.get(slotSelected).peek().getName();
        BigDecimal cost = testVendingMachine.inventory.get(slotSelected).peek().getCost();
        testVendingMachine.dispenseItem(slotSelected);

        File salesLogFile = new File(salesLogFilepath);
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        //Act


        SalesLogger.logPurchase(slotSelected, name, cost, testVendingMachine.getCurrentBalance(), timeStamp);

        String lastLineInFile = null;

        try(Scanner fileReader = new Scanner(salesLogFile)){

            while (fileReader.hasNextLine()){

                lastLineInFile = fileReader.nextLine();

            }

        }
        catch (FileNotFoundException e){

        }

        String actual = timeStamp + " Heavy C4: $1.50 $18.50";
        String expected = lastLineInFile;

        //Assert

        Assert.assertEquals(actual, expected);

    }


    @Test
    public void multiple_puchases_are_recorded_in_log(){

        //Arrange

        VendingMachine testVendingMachine = new VendingMachine("C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv");
        String salesLogFilepath = System.getProperty("java.io.tmpdir") + "\\SalesLog.txt";

        //Feed $20 bill into machine
        testVendingMachine.feedMoney("1");

        //Dispense item at slot "A2"
        String slotSelected = "A2";
        String name = testVendingMachine.inventory.get(slotSelected).peek().getName();
        BigDecimal cost = testVendingMachine.inventory.get(slotSelected).peek().getCost();
        testVendingMachine.dispenseItem(slotSelected);

        //Dispense item at slot "C4"
        slotSelected = "C4";
        name = testVendingMachine.inventory.get(slotSelected).peek().getName();
        cost = testVendingMachine.inventory.get(slotSelected).peek().getCost();
        testVendingMachine.dispenseItem(slotSelected);

        File salesLogFile = new File(salesLogFilepath);
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        //Act


        SalesLogger.logPurchase(slotSelected, name, cost, testVendingMachine.getCurrentBalance(), timeStamp);

        String lastLineInFile = null;

        try(Scanner fileReader = new Scanner(salesLogFile)){

            while (fileReader.hasNextLine()){

                lastLineInFile = fileReader.nextLine();

            }

        }
        catch (FileNotFoundException e){

        }

        String actual = timeStamp + " Heavy C4: $1.50 $17.05";
        String expected = lastLineInFile;

        //Assert

        Assert.assertEquals(actual, expected);

    }


    @Test
    public void give_change_after_purchases_is_recorded_in_log(){

        //Arrange

        VendingMachine testVendingMachine = new VendingMachine("C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv");
        String salesLogFilepath = System.getProperty("java.io.tmpdir") + "\\SalesLog.txt";

        //Feed $20 bill into machine
        testVendingMachine.feedMoney("1");
        BigDecimal moneyPutIn = testVendingMachine.getCurrentBalance();

        //Dispense item at slot "A2"
        String slotSelected = "A2";
        String name = testVendingMachine.inventory.get(slotSelected).peek().getName();
        BigDecimal cost = testVendingMachine.inventory.get(slotSelected).peek().getCost();
        testVendingMachine.dispenseItem(slotSelected);


        //Set change to whatever current balance is after purchase(s)
        BigDecimal change = testVendingMachine.getCurrentBalance();

        File salesLogFile = new File(salesLogFilepath);
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        //Act

        //Give change
        testVendingMachine.giveChange();

        //Log GIVE CHANGE
        //Current balance after giving change should be 0
        SalesLogger.logChange(change, testVendingMachine.getCurrentBalance(), timeStamp);

        String lastLineInFile = null;

        try(Scanner fileReader = new Scanner(salesLogFile)){

            while (fileReader.hasNextLine()){

                lastLineInFile = fileReader.nextLine();

            }

        }
        catch (FileNotFoundException e){

        }

        String actual = timeStamp + " GIVE CHANGE: $18.55 $0.00";
        String expected = lastLineInFile;

        //Assert

        Assert.assertEquals(actual, expected);

    }


    @Test
    public void give_change_after_multiple_puchases_is_recorded_in_log(){

        //Arrange

        VendingMachine testVendingMachine = new VendingMachine("C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv");
        String salesLogFilepath = System.getProperty("java.io.tmpdir") + "\\SalesLog.txt";

        //Feed $20 bill into machine
        testVendingMachine.feedMoney("1");
        BigDecimal moneyPutIn = testVendingMachine.getCurrentBalance();

        //Dispense item at slot "A2"
        String slotSelected = "A2";
        String name = testVendingMachine.inventory.get(slotSelected).peek().getName();
        BigDecimal cost = testVendingMachine.inventory.get(slotSelected).peek().getCost();
        testVendingMachine.dispenseItem(slotSelected);

        //Dispense item at slot "C4"
        slotSelected = "C4";
        name = testVendingMachine.inventory.get(slotSelected).peek().getName();
        cost = testVendingMachine.inventory.get(slotSelected).peek().getCost();
        testVendingMachine.dispenseItem(slotSelected);

        //Set change to whatever current balance is after purchase(s)
        BigDecimal change = testVendingMachine.getCurrentBalance();

        File salesLogFile = new File(salesLogFilepath);
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        //Act

        //Give change
        testVendingMachine.giveChange();

        //Log GIVE CHANGE
        //Current balance after giving change should be 0
        SalesLogger.logChange(change, testVendingMachine.getCurrentBalance(), timeStamp);

        String lastLineInFile = null;

        try(Scanner fileReader = new Scanner(salesLogFile)){

            while (fileReader.hasNextLine()){

                lastLineInFile = fileReader.nextLine();

            }

        }
        catch (FileNotFoundException e){

        }

        String actual = timeStamp + " GIVE CHANGE: $17.05 $0.00";
        String expected = lastLineInFile;

        //Assert

        Assert.assertEquals(actual, expected);

    }


    @Test
    public void sales_report_records_correct_number_of_item_sales(){

        //Arrange

        VendingMachine testVendingMachine = new VendingMachine("C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv");
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME).replace(':', '-');



        //Feed $20 bill into machine
        testVendingMachine.feedMoney("1");

        //Buy up that "Heavy"!
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");


        //Act

        SalesLogger.salesReport(testVendingMachine.getSales(), testVendingMachine.getTotalSales(), timeStamp);


        boolean containsLine = false;
        String expectedLine = "Heavy,3";

        //Create sales report test file
        String salesReportFilePath = System.getProperty("java.io.tmpdir") + "\\SalesReport" + timeStamp + ".txt";
        File salesLogFile = new File(salesReportFilePath);

        try(Scanner fileReader = new Scanner(salesLogFile)){

            while (fileReader.hasNextLine()){

                String currentLine = fileReader.nextLine();

                if(currentLine.equals(expectedLine)){
                    containsLine = true;
                    break;
                }
            }

        }
        catch (FileNotFoundException e){

        }

        //Assert

        Assert.assertEquals(true,containsLine);
    }


    @Test
    public void sales_report_shows_0_sales_for_item_that_was_not_sold(){

        //Arrange

        VendingMachine testVendingMachine = new VendingMachine("C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv");
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME).replace(':', '-');



        //Feed $20 bill into machine
        testVendingMachine.feedMoney("1");

        //Buy up that "Heavy"!
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");


        //Act

        SalesLogger.salesReport(testVendingMachine.getSales(), testVendingMachine.getTotalSales(), timeStamp);


        boolean containsLine = false;
        String expectedLine = "Dr. Salt,0";

        //Create sales report test file
        String salesReportFilePath = System.getProperty("java.io.tmpdir") + "\\SalesReport" + timeStamp + ".txt";
        File salesLogFile = new File(salesReportFilePath);

        try(Scanner fileReader = new Scanner(salesLogFile)){

            while (fileReader.hasNextLine()){

                String currentLine = fileReader.nextLine();

                if(currentLine.equals(expectedLine)){
                    containsLine = true;
                    break;
                }
            }

        }
        catch (FileNotFoundException e){

        }

        //Assert

        Assert.assertEquals(true,containsLine);
    }


    @Test
    public void sales_report_shows_5_sales_for_item_that_was_sold_out(){

        //Arrange

        VendingMachine testVendingMachine = new VendingMachine("C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv");
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME).replace(':', '-');



        //Feed $20 bill into machine
        testVendingMachine.feedMoney("1");

        //Buy up that "Heavy"!
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");


        //Act

        SalesLogger.salesReport(testVendingMachine.getSales(), testVendingMachine.getTotalSales(), timeStamp);


        boolean containsLine = false;
        String expectedLine = "Heavy,5";

        //Create sales report test file
        String salesReportFilePath = System.getProperty("java.io.tmpdir") + "\\SalesReport" + timeStamp + ".txt";
        File salesLogFile = new File(salesReportFilePath);

        try(Scanner fileReader = new Scanner(salesLogFile)){

            while (fileReader.hasNextLine()){

                String currentLine = fileReader.nextLine();

                if(currentLine.equals(expectedLine)){
                    containsLine = true;
                    break;
                }
            }

        }
        catch (FileNotFoundException e){

        }

        //Assert

        Assert.assertEquals(true,containsLine);
    }


    @Test
    public void sales_report_shows_total_sales_amount_on_bottom_line(){

        //Arrange

        VendingMachine testVendingMachine = new VendingMachine("C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv");
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME).replace(':', '-');



        //Feed $20 bill into machine
        testVendingMachine.feedMoney("1");

        //Buy up that items
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("A1");
        testVendingMachine.dispenseItem("C1");


        //Act

        SalesLogger.salesReport(testVendingMachine.getSales(), testVendingMachine.getTotalSales(), timeStamp);


        //Create sales report test file
        String salesReportFilePath = System.getProperty("java.io.tmpdir") + "\\SalesReport" + timeStamp + ".txt";
        File salesLogFile = new File(salesReportFilePath);

        String lastLineInFile = "";

        try(Scanner fileReader = new Scanner(salesLogFile)){

            while (fileReader.hasNextLine()){

                lastLineInFile = fileReader.nextLine();

            }

        }
        catch (FileNotFoundException e){

        }

        String actual = lastLineInFile;
        String expected = "**TOTAL SALES** $8.80";

        //Assert

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void sales_report_shows_total_sales_of_zero_if_no_items_sold(){

        //Arrange

        VendingMachine testVendingMachine = new VendingMachine("C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv");
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME).replace(':', '-');


        //Act

        SalesLogger.salesReport(testVendingMachine.getSales(), testVendingMachine.getTotalSales(), timeStamp);


        //Create sales report test file
        String salesReportFilePath = System.getProperty("java.io.tmpdir") + "\\SalesReport" + timeStamp + ".txt";
        File salesLogFile = new File(salesReportFilePath);

        String lastLineInFile = "";

        try(Scanner fileReader = new Scanner(salesLogFile)){

            while (fileReader.hasNextLine()){

                lastLineInFile = fileReader.nextLine();

            }

        }
        catch (FileNotFoundException e){

        }

        String actual = lastLineInFile;
        String expected = "**TOTAL SALES** $0.00";

        //Assert

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void total_sales_on_sales_report_are_not_increased_if_purchase_is_attempted_on_sold_out_item(){

        //Arrange

        VendingMachine testVendingMachine = new VendingMachine("C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv");
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME).replace(':', '-');



        //Feed $20 bill into machine
        testVendingMachine.feedMoney("1");

        //Buy up that that "Heavy"
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");

        //Heavy is now sold out :(. Further attempts to purchase should not add to total sales
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");
        testVendingMachine.dispenseItem("C4");


        //Act

        SalesLogger.salesReport(testVendingMachine.getSales(), testVendingMachine.getTotalSales(), timeStamp);


        //Create sales report test file
        String salesReportFilePath = System.getProperty("java.io.tmpdir") + "\\SalesReport" + timeStamp + ".txt";
        File salesLogFile = new File(salesReportFilePath);

        String lastLineInFile = "";

        try(Scanner fileReader = new Scanner(salesLogFile)){

            while (fileReader.hasNextLine()){

                lastLineInFile = fileReader.nextLine();

            }

        }
        catch (FileNotFoundException e){

        }

        String actual = lastLineInFile;
        String expected = "**TOTAL SALES** $7.50";

        //Assert

        Assert.assertEquals(expected, actual);
    }
}
