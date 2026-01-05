package oops.basics;

/**
 * Demonstrates Encapsulation - hiding internal state and requiring all interaction
 * to be performed through object's methods
 */
public class Encapsulation {
    
    // Encapsulation Example: BankAccount
    static class BankAccount {
        private String accountNumber;
        private String accountHolder;
        private double balance;
        
        public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
            this.accountNumber = accountNumber;
            this.accountHolder = accountHolder;
            this.balance = initialBalance;
        }
        
        // Getter methods provide controlled access to private data
        public String getAccountNumber() {
            return accountNumber;
        }
        
        public String getAccountHolder() {
            return accountHolder;
        }
        
        public double getBalance() {
            return balance;
        }
        
        // Business logic with validation
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: $" + amount + ", New Balance: $" + balance);
            } else {
                System.out.println("Invalid deposit amount");
            }
        }
        
        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Withdrew: $" + amount + ", New Balance: $" + balance);
                return true;
            } else {
                System.out.println("Insufficient funds or invalid amount");
                return false;
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Encapsulation Example ===");
        
        BankAccount account = new BankAccount("ACC001", "John Doe", 1000.0);
        System.out.println("Account Holder: " + account.getAccountHolder());
        System.out.println("Initial Balance: $" + account.getBalance());
        
        account.deposit(500.0);
        account.withdraw(300.0);
        account.withdraw(2000.0); // Should fail
    }
}
