package oops.basics;

/**
 * Demonstrates Inheritance - mechanism where a new class inherits properties
 * and behaviors from an existing class
 */
public class Inheritance {
    
    // Base class (Parent)
    static class Vehicle {
        protected String brand;
        protected String model;
        protected int year;
        
        public Vehicle(String brand, String model, int year) {
            this.brand = brand;
            this.model = model;
            this.year = year;
        }
        
        public void start() {
            System.out.println(brand + " " + model + " is starting...");
        }
        
        public void stop() {
            System.out.println(brand + " " + model + " is stopping...");
        }
        
        public void displayInfo() {
            System.out.println("Brand: " + brand + ", Model: " + model + ", Year: " + year);
        }
    }
    
    // Derived class (Child) - inherits from Vehicle
    static class Car extends Vehicle {
        private int numberOfDoors;
        
        public Car(String brand, String model, int year, int numberOfDoors) {
            super(brand, model, year); // Call parent constructor
            this.numberOfDoors = numberOfDoors;
        }
        
        // Additional method specific to Car
        public void openTrunk() {
            System.out.println("Trunk is opening...");
        }
        
        @Override
        public void displayInfo() {
            super.displayInfo();
            System.out.println("Number of Doors: " + numberOfDoors);
        }
    }
    
    // Another derived class
    static class Motorcycle extends Vehicle {
        private boolean hasSidecar;
        
        public Motorcycle(String brand, String model, int year, boolean hasSidecar) {
            super(brand, model, year);
            this.hasSidecar = hasSidecar;
        }
        
        public void wheelie() {
            System.out.println("Performing a wheelie!");
        }
        
        @Override
        public void displayInfo() {
            super.displayInfo();
            System.out.println("Has Sidecar: " + hasSidecar);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Inheritance Example ===\n");
        
        System.out.println("Car:");
        Car car = new Car("Toyota", "Camry", 2023, 4);
        car.displayInfo();
        car.start();
        car.openTrunk();
        car.stop();
        
        System.out.println("\nMotorcycle:");
        Motorcycle bike = new Motorcycle("Harley-Davidson", "Sportster", 2023, false);
        bike.displayInfo();
        bike.start();
        bike.wheelie();
        bike.stop();
    }
}
