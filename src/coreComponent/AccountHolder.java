
package coreComponent;

import java.math.BigDecimal;

public class AccountHolder {

    private final String holderName;
    private final String accountNumber;
    private final String holderEmail;
    private final String holderAddress;
    private final String holderMobile;
    private final BigDecimal minimumBalance;

    private final String pin;
    private BigDecimal balance;

    private boolean locked;
    private int failedAttempts;

    private static final int MAX_FAILED_ATTEMPTS = 3;

    public AccountHolder(
            String holderName,
            String accountNumber,
            String pin,
            String holderEmail,
            String holderAddress,
            String holderMobile,
            BigDecimal initialBalance,
            BigDecimal minimumBalance) {

        if (initialBalance == null || minimumBalance == null
                || initialBalance.compareTo(BigDecimal.ZERO) < 0
                || minimumBalance.compareTo(BigDecimal.ZERO) < 0
                || initialBalance.compareTo(minimumBalance) < 0) {
            throw new IllegalArgumentException("Invalid account balance.");
        }

        if (accountNumber == null || accountNumber.isBlank()
                || pin == null || !pin.matches("\\d{4}")) {
            throw new IllegalArgumentException(
                    "Account number and a 4-digit PIN are required.");
        }

        this.holderName = holderName;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.holderEmail = holderEmail;
        this.holderAddress = holderAddress;
        this.holderMobile = holderMobile;
        this.balance = initialBalance;
        this.minimumBalance = minimumBalance;
        this.failedAttempts = 0;
        this.locked = false;
    }

    public boolean authenticate(String enteredAccount, String enteredPin) {
        if (locked) {
            return false;
        }

        if (accountNumber.equals(enteredAccount)
                && pin.equals(enteredPin)) {
            failedAttempts = 0;
            return true;
        }

        failedAttempts++;

        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            locked = true;
        }

        return false;
    }

    public int getRemainingAttempts() {
        return MAX_FAILED_ATTEMPTS - failedAttempts;
    }

    public boolean isLocked() {
        return locked;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean deposit(BigDecimal amount) {
        if (!isValidAmount(amount)) {
            return false;
        }

        balance = balance.add(amount);
        return true;
    }

    public boolean withdraw(BigDecimal amount) {
        if (!isValidAmount(amount)) {
            return false;
        }

        BigDecimal remainingBalance = balance.subtract(amount);

        if (remainingBalance.compareTo(minimumBalance) < 0) {
            return false;
        }

        balance = remainingBalance;
        return true;
    }

    private boolean isValidAmount(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public void displayAccountDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Holder : " + holderName);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Mobile Number  : " + holderMobile);
        System.out.println("Email          : " + holderEmail);
        System.out.println("Address        : " + holderAddress);
        System.out.println("Balance        : Rs. " + balance);
    }
}