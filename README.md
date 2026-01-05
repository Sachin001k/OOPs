# Object-Oriented Programming (OOP) System Design in Java

A comprehensive collection of Object-Oriented Programming concepts, design patterns, and real-world system design examples implemented in Java.

## 📚 Table of Contents

- [Overview](#overview)
- [Project Structure](#project-structure)
- [OOP Concepts](#oop-concepts)
- [Design Patterns](#design-patterns)
- [System Design Examples](#system-design-examples)
- [How to Run](#how-to-run)
- [Learning Resources](#learning-resources)

## 🎯 Overview

This repository demonstrates the four fundamental pillars of Object-Oriented Programming along with common design patterns and real-world system design implementations. Each example is self-contained and can be run independently.

## 📁 Project Structure

```
OOPs/
├── src/
│   └── main/
│       └── java/
│           ├── oops/
│           │   └── basics/
│           │       ├── Encapsulation.java
│           │       ├── Inheritance.java
│           │       ├── Polymorphism.java
│           │       └── Abstraction.java
│           ├── designpatterns/
│           │   ├── SingletonPattern.java
│           │   ├── FactoryPattern.java
│           │   ├── ObserverPattern.java
│           │   ├── StrategyPattern.java
│           │   └── BuilderPattern.java
│           └── systemdesign/
│               ├── LibraryManagementSystem.java
│               ├── ParkingLotSystem.java
│               └── ATMSystem.java
└── README.md
```

## 🔑 OOP Concepts

### 1. Encapsulation
**File:** `src/main/java/oops/basics/Encapsulation.java`

Demonstrates data hiding and controlled access through:
- Private fields with public getter/setter methods
- Business logic validation
- BankAccount example with deposit/withdraw operations

### 2. Inheritance
**File:** `src/main/java/oops/basics/Inheritance.java`

Shows code reuse through class hierarchies:
- Vehicle base class with Car and Motorcycle subclasses
- Method inheritance and overriding
- Use of `super` keyword

### 3. Polymorphism
**File:** `src/main/java/oops/basics/Polymorphism.java`

Illustrates objects taking multiple forms:
- **Runtime Polymorphism:** Method overriding with Animal hierarchy
- **Compile-time Polymorphism:** Method overloading
- Polymorphic arrays and behavior

### 4. Abstraction
**File:** `src/main/java/oops/basics/Abstraction.java`

Demonstrates hiding implementation details:
- Abstract classes and methods
- Shape hierarchy with Circle, Rectangle, Triangle
- Interface implementation
- Heron's formula for triangle area calculation

## 🎨 Design Patterns

### 1. Singleton Pattern
**File:** `src/main/java/designpatterns/SingletonPattern.java`

Ensures single instance creation:
- **Eager Initialization:** Instance created at class loading
- **Lazy Initialization:** Thread-safe double-check locking
- **Bill Pugh Singleton:** Best practice using inner static class

### 2. Factory Pattern
**File:** `src/main/java/designpatterns/FactoryPattern.java`

Creates objects without specifying exact classes:
- Vehicle factory creating Cars, Bikes, and Trucks
- Centralized object creation logic
- Easy to extend with new vehicle types

### 3. Observer Pattern
**File:** `src/main/java/designpatterns/ObserverPattern.java`

Implements publish-subscribe mechanism:
- NewsAgency as subject
- Multiple observers (NewsChannels, MobileApps)
- Automatic notification on state changes

### 4. Strategy Pattern
**File:** `src/main/java/designpatterns/StrategyPattern.java`

Encapsulates interchangeable algorithms:
- Multiple payment strategies (Credit Card, PayPal, Cash)
- ShoppingCart context switching between strategies
- Runtime algorithm selection

### 5. Builder Pattern
**File:** `src/main/java/designpatterns/BuilderPattern.java`

Constructs complex objects step by step:
- Computer builder with required and optional parameters
- Fluent interface design
- Method chaining for readable code

## 🏗️ System Design Examples

### 1. Library Management System
**File:** `src/main/java/systemdesign/LibraryManagementSystem.java`

**Features:**
- Book management (add, search, availability)
- User management (Members and Librarians)
- Book issuing and returning
- Borrowing limits enforcement
- Singleton pattern for Library instance

**Classes:**
- `Book`: Represents library books
- `User`: Abstract base for all users
- `Member`: Can borrow books (max 5)
- `Librarian`: Library staff
- `Library`: Singleton managing all operations

### 2. Parking Lot System
**File:** `src/main/java/systemdesign/ParkingLotSystem.java`

**Features:**
- Multi-level parking structure
- Different spot sizes (Compact, Regular, Large)
- Vehicle type handling (Motorcycle, Car, Truck)
- Automatic spot allocation
- Ticket generation and fee calculation
- Dynamic pricing based on vehicle type

**Classes:**
- `Vehicle`: Abstract base with type hierarchy
- `ParkingSpot`: Represents individual parking spaces
- `ParkingLevel`: Groups spots by floor
- `Ticket`: Tracks parking duration and calculates fees
- `ParkingLot`: Singleton managing the entire system

### 3. ATM System
**File:** `src/main/java/systemdesign/ATMSystem.java`

**Features:**
- State machine implementation (Idle, CardInserted, Authenticated)
- PIN validation
- Multiple transaction types (Withdraw, Deposit, Balance Inquiry)
- Strategy pattern for transactions
- State pattern for ATM states
- Secure account management

**Classes:**
- `Account`: User bank account with PIN validation
- `Transaction`: Interface for different transaction types
- `ATMState`: Interface for different ATM states
- `ATM`: Main controller managing states and operations

## 🚀 How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line terminal

### Compilation

Navigate to the project root directory and compile the Java files:

```bash
# Compile OOP Basics
javac -d bin src/main/java/oops/basics/*.java

# Compile Design Patterns
javac -d bin src/main/java/designpatterns/*.java

# Compile System Design Examples
javac -d bin src/main/java/systemdesign/*.java
```

Or compile all at once:

```bash
javac -d bin src/main/java/oops/basics/*.java src/main/java/designpatterns/*.java src/main/java/systemdesign/*.java
```

### Execution

Run individual examples:

```bash
# OOP Concepts
java -cp bin oops.basics.Encapsulation
java -cp bin oops.basics.Inheritance
java -cp bin oops.basics.Polymorphism
java -cp bin oops.basics.Abstraction

# Design Patterns
java -cp bin designpatterns.SingletonPattern
java -cp bin designpatterns.FactoryPattern
java -cp bin designpatterns.ObserverPattern
java -cp bin designpatterns.StrategyPattern
java -cp bin designpatterns.BuilderPattern

# System Design Examples
java -cp bin systemdesign.LibraryManagementSystem
java -cp bin systemdesign.ParkingLotSystem
java -cp bin systemdesign.ATMSystem
```

### Alternative: Run without pre-compiling

You can also compile and run in one step (Java 11+):

```bash
# From project root
cd src/main/java

# Run any example (Java 11+ required)
java oops/basics/Encapsulation.java
java designpatterns/SingletonPattern.java
java systemdesign/LibraryManagementSystem.java
```

**Note:** The above single-command execution requires Java 11 or higher. For older Java versions, use the compilation and execution steps separately as shown in the previous sections.

## 📖 Learning Resources

### Key Concepts Demonstrated

1. **Encapsulation**: Private data with public accessors
2. **Inheritance**: Code reuse through class hierarchies
3. **Polymorphism**: Same interface, different implementations
4. **Abstraction**: Hiding complexity, showing essentials
5. **SOLID Principles**: Applied throughout system designs
6. **Design Patterns**: Reusable solutions to common problems

### Best Practices Shown

- ✅ Use of access modifiers (private, protected, public)
- ✅ Interface-based programming
- ✅ Single Responsibility Principle
- ✅ Open/Closed Principle
- ✅ Composition over inheritance
- ✅ Immutability where appropriate
- ✅ Meaningful naming conventions
- ✅ Proper documentation

## 🤝 Contributing

Feel free to contribute by:
- Adding new OOP examples
- Implementing additional design patterns
- Creating more system design examples
- Improving documentation
- Fixing bugs or suggesting improvements

## 📝 License

This project is open source and available for educational purposes.

## 👨‍💻 Author

Created as a comprehensive resource for learning Object-Oriented Programming and System Design in Java.

---

**Happy Learning! 🎓**