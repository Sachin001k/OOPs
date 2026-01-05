package systemdesign;

import java.util.HashMap;
import java.util.Map;

/**
 * ATM System - A comprehensive system design example
 * Demonstrates: State pattern, Strategy pattern, and secure transaction handling
 * 
 * SECURITY NOTE: This is an educational example. In production systems:
 * - PINs should be hashed with salt using bcrypt or similar
 * - Use constant-time comparison for PIN validation
 * - Implement rate limiting and account lockout after failed attempts
 * - Add audit logging for all transactions
 * - Use encryption for data transmission
 */
public class ATMSystem {
    
    // Account class
    static class Account {
        private String accountNumber;
        private String pin;
        private double balance;
        private String accountHolderName;
        
        public Account(String accountNumber, String pin, double balance, String accountHolderName) {
            this.accountNumber = accountNumber;
            this.pin = pin;
            this.balance = balance;
            this.accountHolderName = accountHolderName;
        }
        
        public boolean validatePin(String inputPin) {
            return this.pin.equals(inputPin);
        }
        
        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                return true;
            }
            return false;
        }
        
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
            }
        }
        
        public String getAccountNumber() { return accountNumber; }
        public double getBalance() { return balance; }
        public String getAccountHolderName() { return accountHolderName; }
    }
    
    // Transaction interface
    interface Transaction {
        boolean execute(Account account, double amount);
        String getTransactionType();
    }
    
    // Concrete Transaction types
    static class WithdrawTransaction implements Transaction {
        @Override
        public boolean execute(Account account, double amount) {
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful: $" + amount);
                System.out.println("Remaining balance: $" + account.getBalance());
                return true;
            } else {
                System.out.println("Insufficient funds!");
                return false;
            }
        }
        
        @Override
        public String getTransactionType() {
            return "Withdrawal";
        }
    }
    
    static class DepositTransaction implements Transaction {
        @Override
        public boolean execute(Account account, double amount) {
            account.deposit(amount);
            System.out.println("Deposit successful: $" + amount);
            System.out.println("New balance: $" + account.getBalance());
            return true;
        }
        
        @Override
        public String getTransactionType() {
            return "Deposit";
        }
    }
    
    static class BalanceInquiryTransaction implements Transaction {
        @Override
        public boolean execute(Account account, double amount) {
            System.out.println("Current balance: $" + account.getBalance());
            return true;
        }
        
        @Override
        public String getTransactionType() {
            return "Balance Inquiry";
        }
    }
    
    // ATM State interface
    interface ATMState {
        void insertCard(ATM atm);
        void ejectCard(ATM atm);
        void enterPin(ATM atm, String pin);
        void selectTransaction(ATM atm, Transaction transaction, double amount);
    }
    
    // Concrete States
    static class IdleState implements ATMState {
        @Override
        public void insertCard(ATM atm) {
            System.out.println("Card inserted. Please enter PIN.");
            atm.setState(atm.getCardInsertedState());
        }
        
        @Override
        public void ejectCard(ATM atm) {
            System.out.println("No card to eject.");
        }
        
        @Override
        public void enterPin(ATM atm, String pin) {
            System.out.println("Please insert card first.");
        }
        
        @Override
        public void selectTransaction(ATM atm, Transaction transaction, double amount) {
            System.out.println("Please insert card first.");
        }
    }
    
    static class CardInsertedState implements ATMState {
        @Override
        public void insertCard(ATM atm) {
            System.out.println("Card already inserted.");
        }
        
        @Override
        public void ejectCard(ATM atm) {
            System.out.println("Card ejected.");
            atm.setState(atm.getIdleState());
        }
        
        @Override
        public void enterPin(ATM atm, String pin) {
            Account account = atm.getCurrentAccount();
            if (account != null && account.validatePin(pin)) {
                System.out.println("PIN accepted. Welcome " + account.getAccountHolderName() + "!");
                atm.setState(atm.getAuthenticatedState());
            } else {
                System.out.println("Invalid PIN. Card ejected.");
                atm.setState(atm.getIdleState());
            }
        }
        
        @Override
        public void selectTransaction(ATM atm, Transaction transaction, double amount) {
            System.out.println("Please enter PIN first.");
        }
    }
    
    static class AuthenticatedState implements ATMState {
        @Override
        public void insertCard(ATM atm) {
            System.out.println("Card already inserted and authenticated.");
        }
        
        @Override
        public void ejectCard(ATM atm) {
            System.out.println("Transaction session ended. Card ejected.");
            atm.setState(atm.getIdleState());
        }
        
        @Override
        public void enterPin(ATM atm, String pin) {
            System.out.println("Already authenticated.");
        }
        
        @Override
        public void selectTransaction(ATM atm, Transaction transaction, double amount) {
            System.out.println("\nProcessing " + transaction.getTransactionType() + "...");
            Account account = atm.getCurrentAccount();
            if (account != null) {
                transaction.execute(account, amount);
            }
        }
    }
    
    // ATM class
    static class ATM {
        private ATMState idleState;
        private ATMState cardInsertedState;
        private ATMState authenticatedState;
        
        private ATMState currentState;
        private Account currentAccount;
        private Map<String, Account> accounts;
        
        public ATM() {
            idleState = new IdleState();
            cardInsertedState = new CardInsertedState();
            authenticatedState = new AuthenticatedState();
            
            currentState = idleState;
            accounts = new HashMap<>();
        }
        
        public void addAccount(Account account) {
            accounts.put(account.getAccountNumber(), account);
        }
        
        public void insertCard(String accountNumber) {
            currentAccount = accounts.get(accountNumber);
            if (currentAccount == null) {
                System.out.println("Invalid account number. Card ejected.");
                return;
            }
            currentState.insertCard(this);
        }
        
        public void ejectCard() {
            currentState.ejectCard(this);
            currentAccount = null;
        }
        
        public void enterPin(String pin) {
            currentState.enterPin(this, pin);
        }
        
        public void performTransaction(Transaction transaction, double amount) {
            currentState.selectTransaction(this, transaction, amount);
        }
        
        public void setState(ATMState state) {
            this.currentState = state;
        }
        
        public ATMState getIdleState() { return idleState; }
        public ATMState getCardInsertedState() { return cardInsertedState; }
        public ATMState getAuthenticatedState() { return authenticatedState; }
        public Account getCurrentAccount() { return currentAccount; }
    }
    
    public static void main(String[] args) {
        System.out.println("=== ATM System ===\n");
        
        // Create ATM and add accounts
        ATM atm = new ATM();
        atm.addAccount(new Account("123456", "1234", 5000.0, "John Doe"));
        atm.addAccount(new Account("789012", "5678", 3000.0, "Jane Smith"));
        
        System.out.println("ATM System initialized.");
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Simulate ATM usage
        System.out.println("Customer 1: John Doe");
        atm.insertCard("123456");
        atm.enterPin("1234");
        
        // Check balance
        atm.performTransaction(new BalanceInquiryTransaction(), 0);
        
        // Withdraw money
        System.out.println();
        atm.performTransaction(new WithdrawTransaction(), 1000.0);
        
        // Deposit money
        System.out.println();
        atm.performTransaction(new DepositTransaction(), 500.0);
        
        // Check balance again
        System.out.println();
        atm.performTransaction(new BalanceInquiryTransaction(), 0);
        
        // Eject card
        System.out.println();
        atm.ejectCard();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Another customer
        System.out.println("Customer 2: Jane Smith");
        atm.insertCard("789012");
        atm.enterPin("5678");
        
        // Try to withdraw more than balance
        atm.performTransaction(new WithdrawTransaction(), 5000.0);
        
        // Valid withdrawal
        System.out.println();
        atm.performTransaction(new WithdrawTransaction(), 1500.0);
        
        System.out.println();
        atm.ejectCard();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test wrong PIN
        System.out.println("Testing wrong PIN:");
        atm.insertCard("123456");
        atm.enterPin("0000");
    }
}
