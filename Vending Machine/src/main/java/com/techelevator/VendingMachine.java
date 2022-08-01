package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachine {
    //properties
    Map<String, Queue<Item>> inventory = new HashMap<>();

    //Keeps track of sales
    Map<String,Integer> sales = new HashMap<>();

    public final int STARTING_INVENTORY_AMOUNT = 5;

    private BigDecimal currentBalance = new BigDecimal(0.00);

    BigDecimal totalSales = new BigDecimal("0.00");


    private final BigDecimal TWENTY_DOLLAR_BILL = new BigDecimal("20.00");
    private final BigDecimal TEN_DOLLAR_BILL = new BigDecimal("10.00");
    private final BigDecimal FIVE_DOLLAR_BILL = new BigDecimal("5.00");
    private final BigDecimal ONE_DOLLAR_BILL = new BigDecimal("1.00");
    private final BigDecimal QUARTER = new BigDecimal("0.25");
    private final BigDecimal DIME = new BigDecimal("0.10");
    private final BigDecimal NICKEL = new BigDecimal("0.05");

    //construcors
    public VendingMachine(String fileName) {
        File inventoryFile = new File(fileName);

        try (Scanner fileScanner = new Scanner(inventoryFile)) {
            while (fileScanner.hasNextLine()) {
                String[] properties = fileScanner.nextLine().split(",");
                String slot = properties[0];
                String name = properties[1];
                BigDecimal cost = new BigDecimal(properties[2]);
                String itemType = properties[3];
                Item itemToAdd = null;

                Queue<Item> itemsQueue = new LinkedList<>();

                for (int i = 0; i < STARTING_INVENTORY_AMOUNT; i++) {

                    if (itemType.equalsIgnoreCase("Chip")) {
                        itemToAdd = new Chips(name, cost);
                    } else if (itemType.equalsIgnoreCase("Candy")) {
                        itemToAdd = new Candy(name, cost);
                    } else if (itemType.equalsIgnoreCase("Drink")) {
                        itemToAdd = new Beverage(name, cost);
                    } else if (itemType.equalsIgnoreCase("Gum")) {
                        itemToAdd = new Gum(name, cost);
                    }

                    itemsQueue.add(itemToAdd);
                }

                inventory.put(slot, itemsQueue);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Inventory not found. ");
        }

        for(String key : inventory.keySet()){
            sales.put(inventory.get(key).peek().getName(), 0);
        }
    }



    //getter/setters
    public Map<String, Queue<Item>> getInventory() {
        return inventory;
    }

    public BigDecimal getCurrentBalance(){
        return currentBalance;
    }

    public Map<String, Integer> getSales() {
        return sales;
    }

    public BigDecimal getTotalSales() {return totalSales;}

    //methods


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

    public void feedMoney(String denomination){
        BigDecimal amountAdded = new BigDecimal(0);
        if(denomination.equals("1")){
            amountAdded = TWENTY_DOLLAR_BILL;
        }
        else if(denomination.equals("2")){
            amountAdded = TEN_DOLLAR_BILL;
        }
        else if(denomination.equals("3")){
            amountAdded = FIVE_DOLLAR_BILL;
        }
        else if(denomination.equals("4")){
            amountAdded = ONE_DOLLAR_BILL;
        }
        else if(denomination.equals("5")){
            amountAdded = QUARTER;
        }
        else if(denomination.equals("6")){
            amountAdded = DIME;
        }
        else if(denomination.equals("7")){
            amountAdded = NICKEL;
        }
        currentBalance = currentBalance.add(amountAdded);

        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        SalesLogger.logFeedMoney(amountAdded, currentBalance, timeStamp);
    }



    //Calculates how many quarters, dimes and nickels to give as change,
    //deducts them from current balance, and returns String that tells
    //how many of each was dispensed as change

    public String giveChange(){

        //Save current balance to a variable before making deductions in order to display
        //total change later
        BigDecimal totalChange = currentBalance;

        //Count number of each coin to dispense
        int numberOfQuarters = 0;
        int numberOfDimes = 0;
        int numberOfNickels = 0;

        //Repeatedly subtract a quarter from current balance and add a quarter to change given
        //as long as current balance is >= 0.25
        while(currentBalance.compareTo(QUARTER) != -1){
            currentBalance = currentBalance.subtract(QUARTER);
            numberOfQuarters++;
        }

        //Repeatedly subtract a dime from current balance and add a dime to change given
        //as long as current balance is >= 0.10
        while(currentBalance.compareTo(DIME) != -1){
            currentBalance = currentBalance.subtract(DIME);
            numberOfDimes++;
        }

        //Repeatedly subtract a dime from current balance and add a nickel to change given
        //as long as current balance is >= 0.05
        while(currentBalance.compareTo(NICKEL) != -1){
            currentBalance = currentBalance.subtract(NICKEL);
            numberOfNickels++;
        }

        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        SalesLogger.logChange(totalChange, currentBalance, timeStamp);

        //Return String showing total change and the number of quarters, dimes and nickels dispensed
        return "Total Change: $" + totalChange + ". Dispensed: " + numberOfQuarters + " quarter(s), "
                + numberOfDimes + " dime(s) and " + numberOfNickels + " nickel(s)";

    }


//TODO dispenseItem Test
    public String dispenseItem(String slot){
        slot = slot.toUpperCase(Locale.ROOT);
        if(inventory.get(slot).size() == 0){
            return "Item is SOLD OUT";
        }
        else {
            if(currentBalance.compareTo(inventory.get(slot).peek().getCost()) == -1){
                return "Not enough money for purchase";
            }
            else {
                currentBalance = currentBalance.subtract(inventory.get(slot).peek().getCost());
                String name = inventory.get(slot).peek().getName();
                BigDecimal cost = inventory.get(slot).peek().getCost();

                // Update number of sales of product on 'sales' map
                sales.put(name, sales.get(name) + 1);
                // Update dollar amount of total sales
                totalSales = totalSales.add(cost);

                String message = "Dispensed " + name + ", $" + cost + ", " + inventory.get(slot).peek().getMessage();

                String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                SalesLogger.logPurchase(slot, name, cost, currentBalance, timeStamp);

                inventory.get(slot).poll();
                return message;
            }
        }
    }

//TODO displayItems Test
    public void displayItems () {

        System.out.println();

        List<String> slots = new ArrayList<>();
        for (String key : inventory.keySet()) {
            slots.add(key);
        }
        Collections.sort(slots);
        for (String key : slots) {

            if (inventory.get(key).peek() == null) {
                System.out.println(key + ": SOLD OUT");
            }
            else{

                String slot = key;
                String name = inventory.get(key).peek().getName();
                BigDecimal cost = inventory.get(key).peek().getCost();
                System.out.println(slot + ": " + name + ", $" + cost);
            }
        }

    }

}
