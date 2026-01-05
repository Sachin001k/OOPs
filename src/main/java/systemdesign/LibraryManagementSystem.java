package systemdesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Library Management System - A complete system design example
 * Demonstrates: Encapsulation, Inheritance, Polymorphism, and practical OOP design
 */
public class LibraryManagementSystem {
    
    // Book class
    static class Book {
        private String ISBN;
        private String title;
        private String author;
        private String category;
        private boolean isAvailable;
        
        public Book(String ISBN, String title, String author, String category) {
            this.ISBN = ISBN;
            this.title = title;
            this.author = author;
            this.category = category;
            this.isAvailable = true;
        }
        
        public String getISBN() { return ISBN; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public String getCategory() { return category; }
        public boolean isAvailable() { return isAvailable; }
        public void setAvailable(boolean available) { isAvailable = available; }
        
        @Override
        public String toString() {
            return "Book{" +
                   "ISBN='" + ISBN + '\'' +
                   ", title='" + title + '\'' +
                   ", author='" + author + '\'' +
                   ", category='" + category + '\'' +
                   ", available=" + isAvailable +
                   '}';
        }
    }
    
    // User abstract class
    static abstract class User {
        protected String userId;
        protected String name;
        protected String email;
        
        public User(String userId, String name, String email) {
            this.userId = userId;
            this.name = name;
            this.email = email;
        }
        
        public String getUserId() { return userId; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        
        public abstract void displayInfo();
    }
    
    // Member class
    static class Member extends User {
        private List<Book> borrowedBooks;
        private static final int MAX_BOOKS = 5;
        
        public Member(String userId, String name, String email) {
            super(userId, name, email);
            this.borrowedBooks = new ArrayList<>();
        }
        
        public boolean canBorrowMore() {
            return borrowedBooks.size() < MAX_BOOKS;
        }
        
        public void borrowBook(Book book) {
            borrowedBooks.add(book);
        }
        
        public void returnBook(Book book) {
            borrowedBooks.remove(book);
        }
        
        public List<Book> getBorrowedBooks() {
            return new ArrayList<>(borrowedBooks);
        }
        
        @Override
        public void displayInfo() {
            System.out.println("Member ID: " + userId);
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Books borrowed: " + borrowedBooks.size() + "/" + MAX_BOOKS);
        }
    }
    
    // Librarian class
    static class Librarian extends User {
        private String employeeId;
        
        public Librarian(String userId, String name, String email, String employeeId) {
            super(userId, name, email);
            this.employeeId = employeeId;
        }
        
        @Override
        public void displayInfo() {
            System.out.println("Librarian ID: " + userId);
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Employee ID: " + employeeId);
        }
    }
    
    // Library class (Singleton pattern - Bill Pugh implementation)
    static class Library {
        private Map<String, Book> books;
        private Map<String, Member> members;
        private List<Librarian> librarians;
        
        private Library() {
            books = new HashMap<>();
            members = new HashMap<>();
            librarians = new ArrayList<>();
        }
        
        private static class LibraryHelper {
            private static final Library INSTANCE = new Library();
        }
        
        public static Library getInstance() {
            return LibraryHelper.INSTANCE;
        }
        
        public void addBook(Book book) {
            books.put(book.getISBN(), book);
            System.out.println("Book added: " + book.getTitle());
        }
        
        public void registerMember(Member member) {
            members.put(member.getUserId(), member);
            System.out.println("Member registered: " + member.getName());
        }
        
        public void addLibrarian(Librarian librarian) {
            librarians.add(librarian);
            System.out.println("Librarian added: " + librarian.getName());
        }
        
        public boolean issueBook(String ISBN, String memberId) {
            Book book = books.get(ISBN);
            Member member = members.get(memberId);
            
            if (book == null) {
                System.out.println("Book not found!");
                return false;
            }
            
            if (member == null) {
                System.out.println("Member not found!");
                return false;
            }
            
            if (!book.isAvailable()) {
                System.out.println("Book is not available!");
                return false;
            }
            
            if (!member.canBorrowMore()) {
                System.out.println("Member has reached the borrowing limit!");
                return false;
            }
            
            book.setAvailable(false);
            member.borrowBook(book);
            System.out.println("Book issued: " + book.getTitle() + " to " + member.getName());
            return true;
        }
        
        public boolean returnBook(String ISBN, String memberId) {
            Book book = books.get(ISBN);
            Member member = members.get(memberId);
            
            if (book == null || member == null) {
                System.out.println("Invalid book or member!");
                return false;
            }
            
            book.setAvailable(true);
            member.returnBook(book);
            System.out.println("Book returned: " + book.getTitle() + " by " + member.getName());
            return true;
        }
        
        public void searchBooksByTitle(String title) {
            System.out.println("\nSearching for books with title containing: " + title);
            boolean found = false;
            for (Book book : books.values()) {
                if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                    System.out.println(book);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No books found!");
            }
        }
        
        public void displayAvailableBooks() {
            System.out.println("\nAvailable Books:");
            for (Book book : books.values()) {
                if (book.isAvailable()) {
                    System.out.println(book);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Library Management System ===\n");
        
        Library library = Library.getInstance();
        
        // Add books
        library.addBook(new Book("ISBN001", "Java Programming", "John Smith", "Programming"));
        library.addBook(new Book("ISBN002", "Design Patterns", "Gang of Four", "Software Engineering"));
        library.addBook(new Book("ISBN003", "Clean Code", "Robert Martin", "Programming"));
        library.addBook(new Book("ISBN004", "The Pragmatic Programmer", "Hunt & Thomas", "Programming"));
        
        System.out.println();
        
        // Register members
        Member member1 = new Member("M001", "Alice Johnson", "alice@example.com");
        Member member2 = new Member("M002", "Bob Williams", "bob@example.com");
        library.registerMember(member1);
        library.registerMember(member2);
        
        System.out.println();
        
        // Add librarian
        Librarian librarian = new Librarian("L001", "Emma Davis", "emma@library.com", "EMP123");
        library.addLibrarian(librarian);
        
        System.out.println("\n" + "=".repeat(50));
        
        // Display available books
        library.displayAvailableBooks();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Issue books
        library.issueBook("ISBN001", "M001");
        library.issueBook("ISBN002", "M001");
        library.issueBook("ISBN003", "M002");
        
        System.out.println("\n" + "=".repeat(50));
        
        // Display member info
        System.out.println();
        member1.displayInfo();
        
        System.out.println("\n" + "=".repeat(50));
        
        // Display available books after issuing
        library.displayAvailableBooks();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Return a book
        library.returnBook("ISBN001", "M001");
        
        System.out.println("\n" + "=".repeat(50));
        
        // Search books
        library.searchBooksByTitle("programming");
    }
}
