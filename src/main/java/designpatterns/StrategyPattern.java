package designpatterns;

/**
 * Strategy Pattern - defines a family of algorithms, encapsulates each one,
 * and makes them interchangeable
 */
public class StrategyPattern {
    
    // Strategy interface
    interface PaymentStrategy {
        void pay(double amount);
    }
    
    // Concrete strategies
    static class CreditCardPayment implements PaymentStrategy {
        private String cardNumber;
        private String name;
        
        public CreditCardPayment(String cardNumber, String name) {
            this.cardNumber = cardNumber;
            this.name = name;
        }
        
        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using Credit Card");
            System.out.println("Card: " + maskCardNumber(cardNumber) + ", Name: " + name);
        }
        
        private String maskCardNumber(String cardNumber) {
            return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
        }
    }
    
    static class PayPalPayment implements PaymentStrategy {
        private String email;
        
        public PayPalPayment(String email) {
            this.email = email;
        }
        
        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using PayPal");
            System.out.println("PayPal Account: " + email);
        }
    }
    
    static class CashPayment implements PaymentStrategy {
        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " in Cash");
        }
    }
    
    // Context class
    static class ShoppingCart {
        private PaymentStrategy paymentStrategy;
        private double totalAmount;
        
        public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }
        
        public void addItem(String item, double price) {
            totalAmount += price;
            System.out.println("Added " + item + " - $" + price);
        }
        
        public void checkout() {
            if (paymentStrategy == null) {
                System.out.println("Please select a payment method!");
                return;
            }
            System.out.println("\nTotal Amount: $" + totalAmount);
            paymentStrategy.pay(totalAmount);
            totalAmount = 0;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern Example ===\n");
        
        ShoppingCart cart = new ShoppingCart();
        
        System.out.println("Shopping Cart 1:");
        cart.addItem("Laptop", 1200.00);
        cart.addItem("Mouse", 25.00);
        cart.setPaymentStrategy(new CreditCardPayment("1234567812345678", "John Doe"));
        cart.checkout();
        
        System.out.println("\n" + "=".repeat(40) + "\n");
        
        System.out.println("Shopping Cart 2:");
        cart.addItem("Book", 15.99);
        cart.addItem("Pen", 2.50);
        cart.setPaymentStrategy(new PayPalPayment("john.doe@example.com"));
        cart.checkout();
        
        System.out.println("\n" + "=".repeat(40) + "\n");
        
        System.out.println("Shopping Cart 3:");
        cart.addItem("Coffee", 4.50);
        cart.setPaymentStrategy(new CashPayment());
        cart.checkout();
    }
}
