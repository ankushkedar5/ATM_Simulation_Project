
package coreComponent;
import java.math.BigDecimal;
import java.util.Scanner;

public class ATMSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        AccountHolder account = new AccountHolder("Ankush Kedar", "1234567890", "ankushkedar@gmail.com", "Pune, Maharashtra, India", "9856745862", "5555", new BigDecimal(2000.00), new BigDecimal(500000.00));

        ATM atm = new ATM(scanner, account);
        atm.start();
        scanner.close();
    }
}