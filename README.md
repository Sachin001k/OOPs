# OOPs (Object-Oriented Programming) in Java

## Table of Contents
- [Introduction](#introduction)
- [Core Principles of OOPs](#core-principles-of-oops)
  - [1. Encapsulation](#1-encapsulation)
  - [2. Inheritance](#2-inheritance)
  - [3. Polymorphism](#3-polymorphism)
  - [4. Abstraction](#4-abstraction)
- [Benefits of OOPs](#benefits-of-oops)
- [OOPs in System Design](#oops-in-system-design)
- [Design Patterns and OOPs](#design-patterns-and-oops)
- [Real-World Applications](#real-world-applications)

## Introduction

Object-Oriented Programming (OOPs) is a programming paradigm that organizes software design around data, or objects, rather than functions and logic. Java is a pure object-oriented programming language that implements all OOPs concepts effectively.

OOPs helps developers create modular, reusable, and maintainable code by modeling real-world entities as objects with attributes (data) and behaviors (methods).

## Core Principles of OOPs

### 1. Encapsulation

**Definition**: Encapsulation is the mechanism of wrapping data (variables) and code (methods) together as a single unit. It hides the internal state of an object from the outside world.

**Key Features**:
- Data hiding using access modifiers (private, protected, public)
- Provides controlled access through getter and setter methods
- Protects object integrity by preventing unauthorized access

**Example**:
```java
public class BankAccount {
    private double balance;  // Private data
    private String accountNumber;
    
    // Constructor
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    
    // Getter method
    public double getBalance() {
        return balance;
    }
    
    // Setter method with validation
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
}
```

### 2. Inheritance

**Definition**: Inheritance is a mechanism where one class acquires the properties and behaviors of another class. It establishes an "IS-A" relationship between classes.

**Key Features**:
- Code reusability
- Method overriding
- Hierarchical classification
- Supports polymorphism

**Example**:
```java
// Parent class
public class Vehicle {
    protected String brand;
    protected int year;
    
    public void start() {
        System.out.println("Vehicle is starting...");
    }
    
    public void stop() {
        System.out.println("Vehicle is stopping...");
    }
}

// Child class
public class Car extends Vehicle {
    private int numberOfDoors;
    
    public Car(String brand, int year, int doors) {
        this.brand = brand;
        this.year = year;
        this.numberOfDoors = doors;
    }
    
    // Method overriding
    @Override
    public void start() {
        System.out.println("Car engine is starting...");
    }
    
    public void honk() {
        System.out.println("Car is honking!");
    }
}
```

### 3. Polymorphism

**Definition**: Polymorphism means "many forms". It allows objects of different classes to be treated as objects of a common parent class. The same method can behave differently based on the object that invokes it.

**Types**:
- **Compile-time Polymorphism** (Method Overloading)
- **Runtime Polymorphism** (Method Overriding)

**Example**:
```java
// Method Overloading (Compile-time Polymorphism)
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
}

// Method Overriding (Runtime Polymorphism)
public class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }
}

public class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Cat meows");
    }
}

// Usage
Animal myDog = new Dog();
Animal myCat = new Cat();
myDog.makeSound();  // Output: Dog barks
myCat.makeSound();  // Output: Cat meows
```

### 4. Abstraction

**Definition**: Abstraction is the process of hiding implementation details and showing only the essential features of an object. It focuses on what an object does rather than how it does it.

**Implementation**:
- Abstract classes
- Interfaces

**Example**:
```java
// Abstract class
public abstract class Shape {
    protected String color;
    
    // Abstract method
    public abstract double calculateArea();
    
    // Concrete method
    public void setColor(String color) {
        this.color = color;
    }
}

public class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// Interface
public interface Drawable {
    void draw();
    void resize(double factor);
}

public class Rectangle extends Shape implements Drawable {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing rectangle");
    }
    
    @Override
    public void resize(double factor) {
        width *= factor;
        height *= factor;
    }
}
```

## Benefits of OOPs

1. **Modularity**: Code is organized into discrete objects with specific responsibilities
2. **Reusability**: Classes can be reused across projects through inheritance and composition
3. **Maintainability**: Encapsulation makes it easier to modify and maintain code
4. **Scalability**: Easy to add new features without affecting existing code
5. **Security**: Data hiding protects sensitive information
6. **Flexibility**: Polymorphism allows for flexible and extensible code
7. **Problem Solving**: Models real-world problems naturally

## OOPs in System Design

### How OOPs Principles Apply to System Design

#### 1. **Encapsulation in System Design**
- **Microservices Architecture**: Each service encapsulates its own data and business logic
- **API Design**: Internal implementation details are hidden; only interfaces are exposed
- **Database Abstraction**: Data access layers encapsulate database operations
- **Example**: A Payment Service encapsulates all payment processing logic, exposing only essential APIs

```java
public class PaymentService {
    private PaymentGateway gateway;
    private TransactionLogger logger;
    private FraudDetection fraudDetector;
    
    // Public interface
    public PaymentResult processPayment(PaymentRequest request) {
        // Internal implementation hidden
        if (fraudDetector.isSuspicious(request)) {
            return PaymentResult.rejected();
        }
        PaymentResult result = gateway.charge(request);
        logger.log(result);
        return result;
    }
}
```

#### 2. **Inheritance in System Design**
- **Component Hierarchies**: Base components extended for specific use cases
- **Template Method Pattern**: Define algorithm structure in base class, let subclasses implement steps
- **Framework Design**: Provide base classes that applications can extend
- **Example**: Different types of notifications (Email, SMS, Push) inherit from base Notification class

```java
public abstract class Notification {
    public final void send(User user, String message) {
        validate(user, message);
        format(message);
        deliver(user, message);
        logNotification(user);
    }
    
    protected abstract void deliver(User user, String message);
    
    private void validate(User user, String message) { /* ... */ }
    private void format(String message) { /* ... */ }
    private void logNotification(User user) { /* ... */ }
}

public class EmailNotification extends Notification {
    @Override
    protected void deliver(User user, String message) {
        // Email-specific delivery logic
    }
}
```

#### 3. **Polymorphism in System Design**
- **Strategy Pattern**: Swap algorithms at runtime
- **Plugin Architecture**: Different implementations of same interface
- **Database Drivers**: Multiple database implementations behind common interface
- **Example**: Multiple payment providers (Stripe, PayPal, Square) implementing same interface

```java
public interface PaymentProcessor {
    PaymentResult process(PaymentDetails details);
}

public class StripeProcessor implements PaymentProcessor {
    public PaymentResult process(PaymentDetails details) {
        // Stripe-specific implementation
    }
}

public class PayPalProcessor implements PaymentProcessor {
    public PaymentResult process(PaymentDetails details) {
        // PayPal-specific implementation
    }
}

// System can switch processors dynamically
public class PaymentOrchestrator {
    private PaymentProcessor processor;
    
    public void setProcessor(PaymentProcessor processor) {
        this.processor = processor;
    }
    
    public PaymentResult executePayment(PaymentDetails details) {
        return processor.process(details);
    }
}
```

#### 4. **Abstraction in System Design**
- **Service Layer**: Abstract business logic from presentation and data layers
- **Repository Pattern**: Abstract data source details
- **Message Queues**: Abstract communication between services
- **Example**: Repository pattern abstracts database operations

```java
public interface UserRepository {
    User findById(String id);
    void save(User user);
    List<User> findAll();
    void delete(String id);
}

public class MySQLUserRepository implements UserRepository {
    // MySQL-specific implementation
}

public class MongoDBUserRepository implements UserRepository {
    // MongoDB-specific implementation
}

// Business logic doesn't need to know about database details
public class UserService {
    private UserRepository repository;
    
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    
    public User getUser(String id) {
        return repository.findById(id);
    }
}
```

## Design Patterns and OOPs

### Creational Patterns
- **Singleton**: Encapsulates instance creation, ensures single instance
- **Factory**: Uses polymorphism to create objects without specifying exact class
- **Builder**: Encapsulates complex object construction

### Structural Patterns
- **Adapter**: Uses inheritance/composition to make incompatible interfaces work together
- **Decorator**: Uses composition to add responsibilities dynamically
- **Facade**: Provides simplified interface to complex subsystems (abstraction)

### Behavioral Patterns
- **Strategy**: Uses polymorphism to encapsulate algorithms
- **Observer**: Defines one-to-many dependency between objects
- **Template Method**: Uses inheritance to define algorithm skeleton

## Real-World Applications

### 1. **E-Commerce System**
- **Product Catalog**: Inheritance hierarchy (Product → ElectronicProduct, ClothingProduct)
- **Shopping Cart**: Encapsulates cart operations
- **Payment Processing**: Polymorphic payment methods
- **Order Management**: Abstraction of order lifecycle

### 2. **Banking System**
- **Account Types**: Inheritance (Account → SavingsAccount, CheckingAccount)
- **Transaction Processing**: Encapsulated in service classes
- **Authentication**: Abstracted security layer
- **Interest Calculation**: Polymorphic strategies for different account types

### 3. **Social Media Platform**
- **User Profiles**: Encapsulation of user data
- **Content Types**: Polymorphic content (Post, Video, Story)
- **Notification System**: Abstract notification delivery
- **Privacy Settings**: Encapsulated access control

### 4. **Ride-Sharing System**
- **Vehicle Types**: Inheritance hierarchy
- **Pricing Strategy**: Polymorphic pricing algorithms
- **Driver Matching**: Abstracted matching service
- **Trip Management**: Encapsulated trip lifecycle

## Scalability and Maintainability Benefits

### Scalability
- **Horizontal Scaling**: OOPs enables independent service scaling
- **Load Distribution**: Polymorphic implementations can be load-balanced
- **Feature Addition**: New features added through inheritance/composition without modifying existing code

### Maintainability
- **Separation of Concerns**: Each class has single responsibility
- **Loose Coupling**: Objects interact through interfaces
- **High Cohesion**: Related functionality grouped together
- **Testing**: Encapsulation makes unit testing easier
- **Refactoring**: Well-structured OOPs code is easier to refactor

## Best Practices

1. **Favor Composition Over Inheritance**: Use composition for flexibility
2. **Program to Interfaces**: Depend on abstractions, not concrete implementations
3. **SOLID Principles**:
   - **S**ingle Responsibility Principle
   - **O**pen/Closed Principle
   - **L**iskov Substitution Principle
   - **I**nterface Segregation Principle
   - **D**ependency Inversion Principle
4. **Keep Classes Focused**: Each class should have one clear purpose
5. **Use Design Patterns Appropriately**: Apply patterns where they add value
6. **Document Public APIs**: Clear documentation of interfaces and contracts

## Conclusion

Object-Oriented Programming in Java provides a robust foundation for building scalable, maintainable, and flexible software systems. By understanding and applying OOPs principles effectively in system design, developers can create systems that are:

- Easy to understand and modify
- Resilient to change
- Testable and maintainable
- Scalable to meet growing demands
- Aligned with real-world problem domains

The combination of OOPs principles with proper system design creates software architectures that stand the test of time and adapt to evolving business requirements.