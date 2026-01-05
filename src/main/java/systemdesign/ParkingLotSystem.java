package systemdesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Parking Lot System - A real-world system design example
 * Demonstrates: Multi-level design, Strategy pattern, and complex object relationships
 */
public class ParkingLotSystem {
    
    // Enum for vehicle types
    enum VehicleType {
        MOTORCYCLE, CAR, TRUCK
    }
    
    // Enum for parking spot types
    enum SpotType {
        COMPACT, REGULAR, LARGE
    }
    
    // Vehicle abstract class
    static abstract class Vehicle {
        protected String licensePlate;
        protected VehicleType type;
        
        public Vehicle(String licensePlate, VehicleType type) {
            this.licensePlate = licensePlate;
            this.type = type;
        }
        
        public String getLicensePlate() { return licensePlate; }
        public VehicleType getType() { return type; }
    }
    
    static class Motorcycle extends Vehicle {
        public Motorcycle(String licensePlate) {
            super(licensePlate, VehicleType.MOTORCYCLE);
        }
    }
    
    static class Car extends Vehicle {
        public Car(String licensePlate) {
            super(licensePlate, VehicleType.CAR);
        }
    }
    
    static class Truck extends Vehicle {
        public Truck(String licensePlate) {
            super(licensePlate, VehicleType.TRUCK);
        }
    }
    
    // ParkingSpot class
    static class ParkingSpot {
        private String spotId;
        private SpotType type;
        private boolean isOccupied;
        private Vehicle vehicle;
        
        public ParkingSpot(String spotId, SpotType type) {
            this.spotId = spotId;
            this.type = type;
            this.isOccupied = false;
        }
        
        public boolean canFitVehicle(Vehicle vehicle) {
            if (isOccupied) return false;
            
            switch (type) {
                case COMPACT:
                    return vehicle.getType() == VehicleType.MOTORCYCLE;
                case REGULAR:
                    return vehicle.getType() == VehicleType.MOTORCYCLE || 
                           vehicle.getType() == VehicleType.CAR;
                case LARGE:
                    return true;
                default:
                    return false;
            }
        }
        
        public boolean parkVehicle(Vehicle vehicle) {
            if (canFitVehicle(vehicle)) {
                this.vehicle = vehicle;
                this.isOccupied = true;
                return true;
            }
            return false;
        }
        
        public Vehicle removeVehicle() {
            Vehicle temp = this.vehicle;
            this.vehicle = null;
            this.isOccupied = false;
            return temp;
        }
        
        public String getSpotId() { return spotId; }
        public SpotType getType() { return type; }
        public boolean isOccupied() { return isOccupied; }
        public Vehicle getVehicle() { return vehicle; }
    }
    
    // ParkingLevel class
    static class ParkingLevel {
        private int levelNumber;
        private List<ParkingSpot> spots;
        
        public ParkingLevel(int levelNumber) {
            this.levelNumber = levelNumber;
            this.spots = new ArrayList<>();
        }
        
        public void addSpot(ParkingSpot spot) {
            spots.add(spot);
        }
        
        public ParkingSpot findAvailableSpot(Vehicle vehicle) {
            for (ParkingSpot spot : spots) {
                if (spot.canFitVehicle(vehicle)) {
                    return spot;
                }
            }
            return null;
        }
        
        public int getAvailableSpots() {
            int count = 0;
            for (ParkingSpot spot : spots) {
                if (!spot.isOccupied()) count++;
            }
            return count;
        }
        
        public int getLevelNumber() { return levelNumber; }
    }
    
    // Ticket class
    static class Ticket {
        private static AtomicInteger ticketCounter = new AtomicInteger(1);
        private String ticketId;
        private Vehicle vehicle;
        private ParkingSpot spot;
        private long entryTime;
        
        public Ticket(Vehicle vehicle, ParkingSpot spot) {
            this.ticketId = "TICKET" + String.format("%04d", ticketCounter.getAndIncrement());
            this.vehicle = vehicle;
            this.spot = spot;
            this.entryTime = System.currentTimeMillis();
        }
        
        public double calculateFee() {
            long duration = System.currentTimeMillis() - entryTime;
            long hours = duration / (1000 * 60 * 60);
            if (hours == 0) hours = 1; // Minimum 1 hour charge
            
            double rate;
            switch (vehicle.getType()) {
                case MOTORCYCLE:
                    rate = 2.0;
                    break;
                case CAR:
                    rate = 5.0;
                    break;
                case TRUCK:
                    rate = 10.0;
                    break;
                default:
                    rate = 5.0;
            }
            
            return hours * rate;
        }
        
        public String getTicketId() { return ticketId; }
        public Vehicle getVehicle() { return vehicle; }
        public ParkingSpot getSpot() { return spot; }
    }
    
    // ParkingLot class (Singleton - Bill Pugh implementation)
    static class ParkingLot {
        private List<ParkingLevel> levels;
        private Map<String, Ticket> activeTickets;
        
        private ParkingLot() {
            levels = new ArrayList<>();
            activeTickets = new HashMap<>();
        }
        
        private static class ParkingLotHelper {
            private static final ParkingLot INSTANCE = new ParkingLot();
        }
        
        public static ParkingLot getInstance() {
            return ParkingLotHelper.INSTANCE;
        }
        
        public void addLevel(ParkingLevel level) {
            levels.add(level);
        }
        
        public Ticket parkVehicle(Vehicle vehicle) {
            for (ParkingLevel level : levels) {
                ParkingSpot spot = level.findAvailableSpot(vehicle);
                if (spot != null) {
                    if (spot.parkVehicle(vehicle)) {
                        Ticket ticket = new Ticket(vehicle, spot);
                        activeTickets.put(ticket.getTicketId(), ticket);
                        System.out.println("Vehicle parked successfully!");
                        System.out.println("Ticket ID: " + ticket.getTicketId());
                        System.out.println("Spot: " + spot.getSpotId());
                        System.out.println("Level: " + level.getLevelNumber());
                        return ticket;
                    }
                }
            }
            System.out.println("No available parking spot for vehicle: " + vehicle.getLicensePlate());
            return null;
        }
        
        public boolean unparkVehicle(String ticketId) {
            Ticket ticket = activeTickets.get(ticketId);
            if (ticket == null) {
                System.out.println("Invalid ticket!");
                return false;
            }
            
            double fee = ticket.calculateFee();
            ParkingSpot spot = ticket.getSpot();
            Vehicle vehicle = spot.removeVehicle();
            
            System.out.println("Vehicle unparked successfully!");
            System.out.println("License Plate: " + vehicle.getLicensePlate());
            System.out.println("Parking Fee: $" + String.format("%.2f", fee));
            
            activeTickets.remove(ticketId);
            return true;
        }
        
        public void displayAvailability() {
            System.out.println("\nParking Lot Status:");
            for (ParkingLevel level : levels) {
                System.out.println("Level " + level.getLevelNumber() + 
                                   " - Available spots: " + level.getAvailableSpots());
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Parking Lot System ===\n");
        
        ParkingLot parkingLot = ParkingLot.getInstance();
        
        // Create levels and spots
        ParkingLevel level1 = new ParkingLevel(1);
        level1.addSpot(new ParkingSpot("L1-C1", SpotType.COMPACT));
        level1.addSpot(new ParkingSpot("L1-C2", SpotType.COMPACT));
        level1.addSpot(new ParkingSpot("L1-R1", SpotType.REGULAR));
        level1.addSpot(new ParkingSpot("L1-R2", SpotType.REGULAR));
        level1.addSpot(new ParkingSpot("L1-L1", SpotType.LARGE));
        
        ParkingLevel level2 = new ParkingLevel(2);
        level2.addSpot(new ParkingSpot("L2-R1", SpotType.REGULAR));
        level2.addSpot(new ParkingSpot("L2-R2", SpotType.REGULAR));
        level2.addSpot(new ParkingSpot("L2-L1", SpotType.LARGE));
        
        parkingLot.addLevel(level1);
        parkingLot.addLevel(level2);
        
        parkingLot.displayAvailability();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Park vehicles
        Vehicle bike1 = new Motorcycle("BIKE-001");
        Ticket ticket1 = parkingLot.parkVehicle(bike1);
        
        System.out.println();
        Vehicle car1 = new Car("CAR-001");
        Ticket ticket2 = parkingLot.parkVehicle(car1);
        
        System.out.println();
        Vehicle truck1 = new Truck("TRUCK-001");
        Ticket ticket3 = parkingLot.parkVehicle(truck1);
        
        System.out.println("\n" + "=".repeat(50));
        parkingLot.displayAvailability();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Unpark a vehicle
        if (ticket2 != null) {
            parkingLot.unparkVehicle(ticket2.getTicketId());
        }
        
        System.out.println("\n" + "=".repeat(50));
        parkingLot.displayAvailability();
    }
}
