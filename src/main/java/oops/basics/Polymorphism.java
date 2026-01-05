package oops.basics;

/**
 * Demonstrates Polymorphism - ability of objects to take multiple forms
 * Includes both compile-time (method overloading) and runtime (method overriding) polymorphism
 */
public class Polymorphism {
    
    // Base class
    static abstract class Animal {
        protected String name;
        
        public Animal(String name) {
            this.name = name;
        }
        
        // Method to be overridden (Runtime Polymorphism)
        public abstract void makeSound();
        
        public void sleep() {
            System.out.println(name + " is sleeping...");
        }
    }
    
    static class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }
        
        @Override
        public void makeSound() {
            System.out.println(name + " says: Woof! Woof!");
        }
        
        // Method overloading (Compile-time Polymorphism)
        public void play() {
            System.out.println(name + " is playing");
        }
        
        public void play(String toy) {
            System.out.println(name + " is playing with " + toy);
        }
        
        public void play(String toy, int minutes) {
            System.out.println(name + " is playing with " + toy + " for " + minutes + " minutes");
        }
    }
    
    static class Cat extends Animal {
        public Cat(String name) {
            super(name);
        }
        
        @Override
        public void makeSound() {
            System.out.println(name + " says: Meow! Meow!");
        }
    }
    
    static class Cow extends Animal {
        public Cow(String name) {
            super(name);
        }
        
        @Override
        public void makeSound() {
            System.out.println(name + " says: Moo! Moo!");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Polymorphism Example ===\n");
        
        // Runtime Polymorphism - same reference type, different object types
        System.out.println("Runtime Polymorphism (Method Overriding):");
        Animal animal1 = new Dog("Buddy");
        Animal animal2 = new Cat("Whiskers");
        Animal animal3 = new Cow("Bessie");
        
        animal1.makeSound();
        animal2.makeSound();
        animal3.makeSound();
        
        // Compile-time Polymorphism - method overloading
        System.out.println("\nCompile-time Polymorphism (Method Overloading):");
        Dog dog = new Dog("Max");
        dog.play();
        dog.play("ball");
        dog.play("frisbee", 30);
        
        // Array of animals demonstrating polymorphism
        System.out.println("\nPolymorphic array:");
        Animal[] animals = {
            new Dog("Rex"),
            new Cat("Mittens"),
            new Cow("Daisy")
        };
        
        for (Animal animal : animals) {
            animal.makeSound();
        }
    }
}
