package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Queue;

public class SalesLogger {
    private static PrintWriter pw = null;

    public static void logPurchase(String slot, String name, BigDecimal cost, BigDecimal currentBalance, String timeStamp){
        if(pw == null){
            try{
                //pw = new PrintWriter("SalesLog.txt");

                pw = new PrintWriter((System.getProperty("java.io.tmpdir") + "\\SalesLog.txt"));

            }catch(FileNotFoundException e){

            }
        }

        pw.println(timeStamp + " "+name + " " +slot+ ": $"+cost+" $"+currentBalance);
        pw.flush();
    }

    public static void logChange(BigDecimal totalChange, BigDecimal currentBalance, String timeStamp){
        if(pw == null){
            try{
                //pw = new PrintWriter("SalesLog.txt");

                pw = new PrintWriter((System.getProperty("java.io.tmpdir") + "\\SalesLog.txt"));

            }catch(FileNotFoundException e){

            }
        }
        pw.println(timeStamp + " GIVE CHANGE: $"+totalChange+" $"+currentBalance);
        pw.flush();
    }

    public static void logFeedMoney(BigDecimal amountAdded, BigDecimal currentBalance, String timeStamp){
        if(pw == null){
            try{
                //pw = new PrintWriter("SalesLog.txt");

                pw = new PrintWriter((System.getProperty("java.io.tmpdir") + "\\SalesLog.txt"));

            }catch(FileNotFoundException e){

            }
        }
        pw.println(timeStamp + " FEED MONEY: $"+amountAdded+" $"+currentBalance);
        pw.flush();

    }
    public static void salesReport(Map <String, Integer> sales, BigDecimal totalSales, String timeStamp) {

        String logFile = (System.getProperty("java.io.tmpdir")) + "\\SalesReport" + timeStamp + ".txt";

        File file = new File(logFile);

        try (PrintWriter logWriter = new PrintWriter(file)) {


            for (String name : sales.keySet()){
                logWriter.println(name + "," + sales.get(name));
            }

            logWriter.println();
            logWriter.println("**TOTAL SALES** $" + totalSales);

        } catch (FileNotFoundException e) {


        }


    }


}
