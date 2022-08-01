package com.techelevator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Program {


	public static void main(String[] args) {

		//Set file to initialize Vending machine inventory
		String filePath = "C:\\Users\\Student\\workspace\\java-mini-capstone-module-1-team-1\\vendingmachine.csv";

		//Instantiate VendingMachine object
		VendingMachine vendingMachine = new VendingMachine(filePath);

		//Create array of Strings of all the slot numbers (ex. A1,A2,B1,B2,etc.)
		//Used later to validate that a valid slot number is inputted
		String[] slots = new String[vendingMachine.getInventory().keySet().size()];
		int i = 0;
		for (String key : vendingMachine.getInventory().keySet()) {
			slots[i] = key;
			i++;
		}

		//Variable used to store user input
		String answer;

		//Instantiate scanner for user to type in input
		Scanner userInput = new Scanner(System.in);

		System.out.println("**********************************");
		System.out.println("****Welcome to Vending Machine****");
		System.out.println("**********************************");
		System.out.println();


		// ***MAIN MENU***
		//Loops through enter program until option 3 is selected to exit program
		do {

			//Loops until valid input received
			do {
				System.out.println();
				System.out.println("MAIN MENU");
				System.out.println();
				System.out.println("(1) Display Vending Machine Items");
				System.out.println("(2) Purchase");
				System.out.println("(3) Exit");
				System.out.println();
				System.out.print("Please enter selection: ");

				answer = userInput.nextLine();

				//Checks if volid choice was entered from menu. If input is invalid, will prompt user and
				//loop back to MAIN MENU
				if (!isValidInput(new String[]{"1", "2", "3", "4"}, answer)) {
					System.out.println("Invalid choice: please enter '1', '2', or '3'.");
				}
			} while (!isValidInput(new String[]{"1", "2", "3", "4"}, answer));

			// ***DISPLAY ITEMS***
			//Display Menu if option 1 selected from MAIN MENU then loops back to MAIN MENU
			if (answer.equalsIgnoreCase("1")) {
				vendingMachine.displayItems();
			}

			// ***PURCHASE MENU***
			//Display Purchase Menu if option 2 is selected from MAIN MENU
			else if (answer.equals("2")) {

				//Loops though purchase menu until choice 3 is selected to finish transaction
				//Will loop back to main menu when transaction is finished
				do {

					//Keeps looping until valid input is entered.
					while (true) {

						System.out.println();
						System.out.println("Current Money Provided: $" + vendingMachine.getCurrentBalance());
						System.out.println();
						System.out.println("(1) Feed Money");
						System.out.println("(2) Select Product");
						System.out.println("(3) Finish Transaction");
						System.out.println();
						System.out.print("Enter selection: ");
						answer = userInput.nextLine();

						//Check that valid input is entered by user from menu. Will break out of loop if answer is valid
						if (isValidInput(new String[]{"1", "2", "3"}, answer)) {
							break;
						}
						System.out.println();

						//Prompt user if input is invalid and will loop back to PURCHASE MENU
						System.out.println("Invalid input!! Please select: '1', '2' or '3'. ");
					}

					// ***FEED MONEY MENU***
					//Allows user to feed money if option 1 selected from PURCHASE MENU
					if (answer.equals("1")) {

						while (true) {

							//Continues looping until valid input is entered
							do {
								System.out.println();
								System.out.println("Current balance: $" + vendingMachine.getCurrentBalance());
								System.out.println();
								System.out.println("(1) Insert $20 bill");
								System.out.println("(2) Insert $10 bill");
								System.out.println("(3) Insert $5 bill");
								System.out.println("(4) Insert $1 bill");
								System.out.println("(5) Insert Quarter");
								System.out.println("(6) Insert Dime");
								System.out.println("(7) Insert Nickel");
								System.out.println("(8) Done adding money");
								System.out.println();
								System.out.print("Please enter selection: ");

								answer = userInput.nextLine();

								//Checks if user inputted a valid choice from menu and if not will prompt them
								//for a valid input and loop back to FEED MONEY MENU
								if (!isValidInput(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"}, answer)) {
									System.out.println("Invalid selection. Please select a choice '1' through '8'.");
								}
							} while (!isValidInput(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"}, answer));

							//Exit Feed Money loop if choice 8 is selected and go back to PURCHASE MENU
							if (answer.equals("8")) {
								break;
							}
							//If valid choice other than 8 is selected, feed money into machine
							else {
								//Will update current balance based on user's selction from FEED MONEY MENU
								vendingMachine.feedMoney(answer);
							}
						}
					}

					// ***SELECT ITEM***
					//Allows user to select item by slot number (ex. A1, A2, B1, B2, etc.) if option 2 selected from PURCHASE MENU
					else if (answer.equals("2")) {

						//Keeps looping until user enters valid slot number
						while (true) {
							System.out.println();
							System.out.print("Enter slot number (Ex, 'A1', 'B2', 'C4', etc.): ");
							answer = userInput.nextLine();
							System.out.println();

							//Checks user's input for slot number against array of valid slot numbers
							//Breaks out of validation loop if user input is valid
							if (isValidInput(slots, answer)) {
								break;
							}

							//Prompt user if they did not input a valid slot number and will loop back to prompt for product selection again.
							System.out.println("Invalid Input!! Please enter slot number. (Ex, 'A1', 'B2', 'C4', etc.)");
						}

						// ***DISPENSE PRODUCT***
						//Calls method to dispense item which updates inventory and current balance
						//and returns String to print out that says name of product, price and message (Ex. "Slurp, Slurp, Yum!)
						//Will inform user if item is sold out and will not adjust inventory or current balance in that case.
						System.out.println(vendingMachine.dispenseItem(answer));


					}

					// ***GIVE CHANGE***
					//Calculates change and prints out String describing change given if option 3 (Finish Transaction) selected
					//from PURCHASE MENU
					else if (answer.equals("3")) {
						System.out.println(vendingMachine.giveChange());
						break;
					}
				} while (true);


			}
			// Exits program if option 3 (Exit) is selected from MAIN MENU
			else if (answer.equals("3")) {
				System.exit(0);

			}

			// Secretly creates sales report file if hidden option 4 is selected from MAIN MENU
			else if (answer.equals("4")) {

				String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME).replace(':', '-');
				SalesLogger.salesReport(vendingMachine.getSales(), vendingMachine.getTotalSales(), timeStamp);
			}

		} while (true);
	}

	// Method used to validate user input
	// Takes an array of Strings of valid inputs and the user's input as arguments
	// Returns true if user's input is found in array of valid inputs. Otherwise returns false.
	public static boolean isValidInput(String[] validInputs, String userInput) {
		for (String input : validInputs) {
			if (userInput.equalsIgnoreCase(input)) {
				return true;
			}
		}
		return false;
	}
}
