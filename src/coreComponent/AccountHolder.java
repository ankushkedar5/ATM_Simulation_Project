package coreComponent;
import java.math.BigDecimal;

public class AccountHolder {

    private final String holderName;
    private final String accountNumber;
    private final String holderEmail;
    private final String holderAddress;
    private final String holderMobile;

    private final String pin;
    private BigDecimal balance;
    private final BigDecimal minimumBalance;

    private boolean locked;
    private int failedAttempts;

    private static final int MAX_FAILED_ATTEMPTS = 3;

    public AccountHolder(String holderName, String accountNumber, String holderEmail, String holderAddress, String holderMobile,
        String pin, BigDecimal minimumBalance, BigDecimal initialBalance) {

            if(initialBalance == null || minimumBalance == null || initialBalance.compareTo(BigDecimal.ZERO) < 0 || 
            minimumBalance.compareTo(BigDecimal.ZERO) < 0 || initialBalance.compareTo(minimumBalance) < 0 ) {
                throw new IllegalArgumentException("Invalid account balance.");
            }

            if(accountNumber == null || accountNumber.isBlank() || pin == null || !pin.matches("\\d{4}")) {
                throw new IllegalArgumentException("Account Number and a 4-digit PIN are required.");
            }

            this.holderName = holderName;
            this.accountNumber = accountNumber;
            this.holderEmail = holderEmail;
            this.holderAddress = holderAddress;
            this.holderMobile = holderMobile;
            this.pin = pin;
            this.minimumBalance = minimumBalance;
            this.balance = initialBalance;
            this.failedAttempts = 0;
            this.locked  = false;
        }

        public boolean authenticate(String enteredAccount, String enteredPin) {
            if(locked) {
                return false;
            }

            if(accountNumber.equals(enteredAccount) && pin.equals(enteredPin)) {
                failedAttempts = 0;
                return true;
            }

            failedAttempts++;
            if(failedAttempts >= MAX_FAILED_ATTEMPTS) {
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

        private boolean isValidAmount(BigDecimal amount) {
            return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
        }

        public boolean withdraw(BigDecimal amount) {
            if(!isValidAmount(amount)) {
                return false;
            }

            BigDecimal remainingBalance = balance.subtract(amount);
            if(remainingBalance.compareTo(minimumBalance) < 0) {
                return false;
            }
            balance = remainingBalance;
            return true;
        }

        public boolean deposit(BigDecimal amount) {
            if(!isValidAmount(amount)) {
                return false;
            }

            balance = balance.add(amount);
            return true;
        }

        public void displayAccountDetails() {
            System.out.println("\n__________ ACCOUNT DETAILS __________");
            System.out.println("Account Holder's Name: " + holderName);
            System.out.println("Account Number       : " + accountNumber);
            System.out.println("Mobile Number        : " + holderMobile);
            System.out.println("Email Address        : " + holderEmail);
            System.out.println("Address              : " + holderAddress);
            System.out.println("current Balance      : " + balance);
        }
}