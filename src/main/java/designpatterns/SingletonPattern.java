package designpatterns;

/**
 * Singleton Pattern - ensures a class has only one instance and provides
 * a global point of access to it
 */
public class SingletonPattern {
    
    // Eager initialization
    static class EagerSingleton {
        private static final EagerSingleton instance = new EagerSingleton();
        
        private EagerSingleton() {
            System.out.println("EagerSingleton instance created");
        }
        
        public static EagerSingleton getInstance() {
            return instance;
        }
        
        public void showMessage() {
            System.out.println("Hello from EagerSingleton!");
        }
    }
    
    // Lazy initialization with thread safety
    static class LazySingleton {
        private static volatile LazySingleton instance;
        
        private LazySingleton() {
            System.out.println("LazySingleton instance created");
        }
        
        public static LazySingleton getInstance() {
            if (instance == null) {
                synchronized (LazySingleton.class) {
                    if (instance == null) {
                        instance = new LazySingleton();
                    }
                }
            }
            return instance;
        }
        
        public void showMessage() {
            System.out.println("Hello from LazySingleton!");
        }
    }
    
    // Bill Pugh Singleton (best practice)
    static class BillPughSingleton {
        private BillPughSingleton() {
            System.out.println("BillPughSingleton instance created");
        }
        
        private static class SingletonHelper {
            private static final BillPughSingleton INSTANCE = new BillPughSingleton();
        }
        
        public static BillPughSingleton getInstance() {
            return SingletonHelper.INSTANCE;
        }
        
        public void showMessage() {
            System.out.println("Hello from BillPughSingleton!");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Example ===\n");
        
        System.out.println("Testing Eager Singleton:");
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();
        System.out.println("Same instance? " + (eager1 == eager2));
        eager1.showMessage();
        
        System.out.println("\nTesting Lazy Singleton:");
        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();
        System.out.println("Same instance? " + (lazy1 == lazy2));
        lazy1.showMessage();
        
        System.out.println("\nTesting Bill Pugh Singleton:");
        BillPughSingleton bp1 = BillPughSingleton.getInstance();
        BillPughSingleton bp2 = BillPughSingleton.getInstance();
        System.out.println("Same instance? " + (bp1 == bp2));
        bp1.showMessage();
    }
}
