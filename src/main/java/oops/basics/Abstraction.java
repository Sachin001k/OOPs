package oops.basics;

/**
 * Demonstrates Abstraction - hiding complex implementation details and showing
 * only the necessary features
 */
public class Abstraction {
    
    // Abstract class
    static abstract class Shape {
        protected String color;
        
        public Shape(String color) {
            this.color = color;
        }
        
        // Abstract methods - must be implemented by subclasses
        public abstract double calculateArea();
        public abstract double calculatePerimeter();
        
        // Concrete method
        public void displayColor() {
            System.out.println("Color: " + color);
        }
    }
    
    static class Circle extends Shape {
        private double radius;
        
        public Circle(String color, double radius) {
            super(color);
            this.radius = radius;
        }
        
        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }
        
        @Override
        public double calculatePerimeter() {
            return 2 * Math.PI * radius;
        }
    }
    
    static class Rectangle extends Shape {
        private double length;
        private double width;
        
        public Rectangle(String color, double length, double width) {
            super(color);
            this.length = length;
            this.width = width;
        }
        
        @Override
        public double calculateArea() {
            return length * width;
        }
        
        @Override
        public double calculatePerimeter() {
            return 2 * (length + width);
        }
    }
    
    // Interface for demonstrating abstraction
    interface Drawable {
        void draw();
    }
    
    static class Triangle extends Shape implements Drawable {
        private double side1, side2, side3;
        
        public Triangle(String color, double side1, double side2, double side3) {
            super(color);
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        }
        
        @Override
        public double calculateArea() {
            // Using Heron's formula
            double s = (side1 + side2 + side3) / 2;
            return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
        }
        
        @Override
        public double calculatePerimeter() {
            return side1 + side2 + side3;
        }
        
        @Override
        public void draw() {
            System.out.println("Drawing a triangle");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Abstraction Example ===\n");
        
        Shape circle = new Circle("Red", 5.0);
        System.out.println("Circle:");
        circle.displayColor();
        System.out.println("Area: " + String.format("%.2f", circle.calculateArea()));
        System.out.println("Perimeter: " + String.format("%.2f", circle.calculatePerimeter()));
        
        System.out.println("\nRectangle:");
        Shape rectangle = new Rectangle("Blue", 4.0, 6.0);
        rectangle.displayColor();
        System.out.println("Area: " + String.format("%.2f", rectangle.calculateArea()));
        System.out.println("Perimeter: " + String.format("%.2f", rectangle.calculatePerimeter()));
        
        System.out.println("\nTriangle:");
        Triangle triangle = new Triangle("Green", 3.0, 4.0, 5.0);
        triangle.displayColor();
        System.out.println("Area: " + String.format("%.2f", triangle.calculateArea()));
        System.out.println("Perimeter: " + String.format("%.2f", triangle.calculatePerimeter()));
        triangle.draw();
    }
}
