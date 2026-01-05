package designpatterns;

/**
 * Factory Pattern - creates objects without specifying the exact class
 * of object that will be created
 */
public class FactoryPattern {
    
    // Product interface
    interface Vehicle {
        void drive();
        void stop();
    }
    
    // Concrete products
    static class Car implements Vehicle {
        @Override
        public void drive() {
            System.out.println("Car is driving on the road");
        }
        
        @Override
        public void stop() {
            System.out.println("Car stopped");
        }
    }
    
    static class Bike implements Vehicle {
        @Override
        public void drive() {
            System.out.println("Bike is riding on the road");
        }
        
        @Override
        public void stop() {
            System.out.println("Bike stopped");
        }
    }
    
    static class Truck implements Vehicle {
        @Override
        public void drive() {
            System.out.println("Truck is driving on the highway");
        }
        
        @Override
        public void stop() {
            System.out.println("Truck stopped");
        }
    }
    
    // Factory class
    static class VehicleFactory {
        public static Vehicle createVehicle(String type) {
            if (type == null || type.isEmpty()) {
                return null;
            }
            
            switch (type.toLowerCase()) {
                case "car":
                    return new Car();
                case "bike":
                    return new Bike();
                case "truck":
                    return new Truck();
                default:
                    throw new IllegalArgumentException("Unknown vehicle type: " + type);
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Factory Pattern Example ===\n");
        
        // Create vehicles using factory
        Vehicle car = VehicleFactory.createVehicle("car");
        System.out.println("Created: Car");
        car.drive();
        car.stop();
        
        System.out.println();
        Vehicle bike = VehicleFactory.createVehicle("bike");
        System.out.println("Created: Bike");
        bike.drive();
        bike.stop();
        
        System.out.println();
        Vehicle truck = VehicleFactory.createVehicle("truck");
        System.out.println("Created: Truck");
        truck.drive();
        truck.stop();
    }
}
