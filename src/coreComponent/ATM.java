package coreComponent;
import java.math.BigDecimal;
import java.util.Scanner;

public class ATM {
    private final Scanner scanner;
    private final AccountHolder account;
    private boolean running;

    public ATM(Scanner scanner, AccountHolder account) {
        this.scanner = scanner;
        this.account = account;
        this.running = true;
    }

    public void start() {
        System.out.println("\n__________ WELCOME TO ATM SIMULATION__________");

        while(running) {
            if(account.isLocked()) {
                System.out.println("\nAccount locked due to failed login attempts.");
                break;
            }

            if(!login()) {
                continue;
            }

            showMenu();
        }
        System.out.println("\nThank you for using ATM Simulation.");
    }

    private boolean login() {
        //System.out.println("\n__________Login__________");

        while(running && !account.isLocked()) {
            System.out.print("\nEnter account number: ");
            String enteredAccount = scanner.nextLine().trim();

            System.out.print("Enter 4-digit PIN: ");
            String enteredPin = scanner.nextLine().trim();
            
            if(account.authenticate(enteredAccount, enteredPin)) {
                System.out.println("\nLogin successful...");
                return true;
            }

            if(account.isLocked()) {
                System.out.println("\nAccount locked after 3 failed login attempts");
                running = false;
                return false;
            }

            System.out.println("Invalid account number or PIN");
            System.out.println("Only " + account.getRemainingAttempts() +" Attempts Remaining...");
        }
        return false;
    }

    private void showMenu() {
        boolean loggedIn = true;

        while(running && loggedIn) {
            System.out.println("\n__________ATM Menu__________");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit"); 
            System.out.println("3. Check Balance"); 
            System.out.println("4. View Account"); 
            System.out.println("5. Logout"); 
            System.out.println("6. Exit");

            int choice = readMenuChoice();

            switch(choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    account.displayAccountDetails();
                    break;
                case 5:
                    loggedIn = false;
                    System.out.println("\nLogged out successfully...");
                    break;
                case 6:
                    loggedIn = false;
                    running = false;
                    System.out.println("\nExiting ATM Simulation...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again with a number from 1 to 6.");

            }
        }
    }

    private int readMenuChoice() {
        while(true) {
            System.out.print("\nEnter your choice (1-6): ");
            String input = scanner.nextLine().trim();

            try{
                return Integer.parseInt(input);
            } catch(NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number");
            }
        }
    }

    private BigDecimal readAmount(String prompt) {
        while(true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try{
                BigDecimal amount = new BigDecimal(input);

                if(amount.scale() > 2) {
                    System.out.println("\nEnter an amount with at most 2 decimal places");
                    continue;
                }
                return amount;
            } catch(NumberFormatException e) {
                System.out.println("Invalid amount. Please try again");
            }
        }
    }

    private void withdraw() {
        BigDecimal amount = readAmount("\nEnter withdrawal amount: RS. ");

        if(account.withdraw(amount)) {
            System.out.println("Withdrawal successful: Rs. " + amount);
            System.out.println("Remaining balance: Rs. " + account.getBalance());
        }
        else {
            System.out.println("Withdrawal failed. Minimum balance must be maintained");
        }
    }

    private void deposit() {
        BigDecimal amount = readAmount("\nEnter deposit amount: Rs. ");

        if(account.deposit(amount)) {
            System.out.println("Deposit successful: Rs. " + amount);
            System.out.println("Updated balance: Rs. " + account.getBalance());
        }
        else {
            System.out.println("Deposit failed");
        }
    }

    private void checkBalance() {
        System.out.println("\nCurrent balance: Rs. " + account.getBalance());
    }
}