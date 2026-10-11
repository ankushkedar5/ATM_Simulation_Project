# Java ATM Simulation

A console-based ATM Simulation System developed using Java. This project simulates basic ATM operations while demonstrating object-oriented programming, data encapsulation, authentication, input validation, and transaction management.

## Features
* **User Authentication:** Login using an account number and PIN.
* **Account Locking:** Locks the account after three consecutive failed login attempts.
* **Withdraw Money:** Withdraw funds while maintaining the minimum balance.
* **Deposit Money:** Deposit a valid amount into the account.
* **Check Balance:** View the current account balance.
* **View Account Details:** Display account information without revealing the PIN.
* **Logout and Exit:** Manage the login session and application lifecycle.
* **Input Validation:** Handle invalid menu choices and transaction amounts.
* **Financial Precision:** Use `BigDecimal` for monetary calculations.

## Technologies Used

* **Language:** Java
* **Input Handling:** `Scanner`
* **Financial Calculations:** `BigDecimal`
* **Version Control:** Git and GitHub

## Project Structure

```text
ATM_Simulation/
└── src/
    └── coreComponent/
        ├── ATMSimulation.java
        ├── ATM.java
        └── AccountHolder.java
```

* `ATMSimulation.java` — Entry point of the application.
* `ATM.java` — Handles authentication, menu navigation, and ATM operations.
* `AccountHolder.java` — Manages account information, authentication attempts, balance, deposits, and withdrawals.

## Getting Started

### Prerequisites

* Java Development Kit (JDK) installed.
* A terminal or Java-compatible IDE.
* Basic knowledge of Java.

### Installation

1. Clone this repository:

   ```bash
   git clone https://github.com/ankushkedar5/ATM_Simulation_Project.git
   ```

2. Navigate to the directory containing the `coreComponent` package.

3. Compile the Java files:

   ```bash
   javac coreComponent/*.java
   ```

4. Run the application:

   ```bash
   java coreComponent.ATMSimulation
   ```

Replace the repository URL with your actual GitHub repository URL.

## Demo Credentials

The application currently uses a predefined demo account.

| Field                   | Value        |
| ----------------------- | ------------ |
| Account Number          | `1234567890` |
| PIN                     | `1234`       |
| Initial Balance         | ₹10,000      |
| Minimum Balance         | ₹2,000       |
| Maximum Failed Attempts | 3            |

**Note:** These are test credentials for the demonstration version only.

## Available Operations

| Option | Operation     | Description                                           |
| ------ | ------------- | ----------------------------------------------------- |
| 1      | Withdraw      | Withdraw money while maintaining the minimum balance. |
| 2      | Deposit       | Add money to the account.                             |
| 3      | Check Balance | Display the current balance.                          |
| 4      | View Account  | Display account details.                              |
| 5      | Logout        | End the current login session.                        |
| 6      | Exit          | Close the application.                                |

## Object-Oriented Programming Concepts

This project demonstrates the following Java concepts:

* **Classes and Objects:** Organize account data and ATM operations.
* **Encapsulation:** Protect account information using private fields and controlled methods.
* **Constructors:** Initialize account objects with predefined details.
* **Access Modifiers:** Control access to sensitive data and internal methods.
* **Composition:** The ATM uses an `AccountHolder` object instead of inheriting from it.
* **Methods:** Separate authentication, deposits, withdrawals, and menu operations.
* **Loops and Switch Statements:** Manage repeated menu interactions and user choices.
* **Exception Handling:** Handle invalid numeric input.
* **`final` Keyword:** Prevent reassignment of selected account fields.
* **`BigDecimal`:** Perform monetary calculations with appropriate decimal precision.

## Security and Validation

The application includes basic educational security measures:

* Limited login attempts.
* Account locking after repeated failed authentication.
* Private account fields.
* PIN omission from displayed account details.
* Validation of transaction amounts.
* Minimum-balance enforcement.
* Handling of invalid menu inputs.

**Security limitation:** This is an educational simulation, not a production banking application. The demo PIN is stored as plaintext in the source code and memory. Account data is not persisted in a database, and the account lock resets when the program restarts. No real financial transactions are performed.

## Future Improvements

* Multiple user accounts.
* Database integration using JDBC and MySQL.
* Secure PIN storage and authentication.
* Persistent account locking.
* Transaction history and receipts.
* PIN change functionality.
* Fund transfers between accounts.
* Unit testing and improved error handling.

## Learning Objectives

This project was created to strengthen practical Java programming skills, understand object-oriented design, implement basic security controls, and build a structured console application.

## Author

**Ankush Kedar**

GitHub: [@ankushkedar5](https://github.com/ankushkedar5)

## License

This project is intended for educational and learning purposes. A formal open-source license can be added if you want others to reuse, modify, and distribute the code under specified terms.
