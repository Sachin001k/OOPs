package designpatterns;

/**
 * Builder Pattern - constructs complex objects step by step
 * Allows different representations using the same construction process
 */
public class BuilderPattern {
    
    // Product class
    static class Computer {
        // Required parameters
        private String CPU;
        private String RAM;
        
        // Optional parameters
        private String storage;
        private String GPU;
        private String motherboard;
        private boolean hasWifi;
        private boolean hasBluetooth;
        
        private Computer(ComputerBuilder builder) {
            this.CPU = builder.CPU;
            this.RAM = builder.RAM;
            this.storage = builder.storage;
            this.GPU = builder.GPU;
            this.motherboard = builder.motherboard;
            this.hasWifi = builder.hasWifi;
            this.hasBluetooth = builder.hasBluetooth;
        }
        
        @Override
        public String toString() {
            return "Computer Specifications:\n" +
                   "  CPU: " + CPU + "\n" +
                   "  RAM: " + RAM + "\n" +
                   "  Storage: " + (storage != null ? storage : "Not specified") + "\n" +
                   "  GPU: " + (GPU != null ? GPU : "Integrated") + "\n" +
                   "  Motherboard: " + (motherboard != null ? motherboard : "Standard") + "\n" +
                   "  WiFi: " + (hasWifi ? "Yes" : "No") + "\n" +
                   "  Bluetooth: " + (hasBluetooth ? "Yes" : "No");
        }
        
        // Builder class
        static class ComputerBuilder {
            // Required parameters
            private String CPU;
            private String RAM;
            
            // Optional parameters
            private String storage;
            private String GPU;
            private String motherboard;
            private boolean hasWifi;
            private boolean hasBluetooth;
            
            public ComputerBuilder(String CPU, String RAM) {
                this.CPU = CPU;
                this.RAM = RAM;
            }
            
            public ComputerBuilder setStorage(String storage) {
                this.storage = storage;
                return this;
            }
            
            public ComputerBuilder setGPU(String GPU) {
                this.GPU = GPU;
                return this;
            }
            
            public ComputerBuilder setMotherboard(String motherboard) {
                this.motherboard = motherboard;
                return this;
            }
            
            public ComputerBuilder setWifi(boolean hasWifi) {
                this.hasWifi = hasWifi;
                return this;
            }
            
            public ComputerBuilder setBluetooth(boolean hasBluetooth) {
                this.hasBluetooth = hasBluetooth;
                return this;
            }
            
            public Computer build() {
                return new Computer(this);
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Example ===\n");
        
        // Building a basic computer
        Computer basicComputer = new Computer.ComputerBuilder("Intel i5", "8GB")
                .setStorage("256GB SSD")
                .setWifi(true)
                .build();
        
        System.out.println("Basic Computer:");
        System.out.println(basicComputer);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Building a gaming computer
        Computer gamingComputer = new Computer.ComputerBuilder("AMD Ryzen 9", "32GB")
                .setStorage("1TB NVMe SSD")
                .setGPU("NVIDIA RTX 4090")
                .setMotherboard("ASUS ROG")
                .setWifi(true)
                .setBluetooth(true)
                .build();
        
        System.out.println("Gaming Computer:");
        System.out.println(gamingComputer);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Building a minimal computer
        Computer minimalComputer = new Computer.ComputerBuilder("Intel i3", "4GB")
                .build();
        
        System.out.println("Minimal Computer:");
        System.out.println(minimalComputer);
    }
}
