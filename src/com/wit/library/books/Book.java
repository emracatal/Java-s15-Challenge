package com.wit.library.books;

import com.wit.library.enums.Edition;
import com.wit.library.interfaces.Borrowable;
import com.wit.library.people.User;

import java.util.Objects;

public class Book implements Borrowable {
    private String id;
    private String name;
    private String author;
    private double price;
    private boolean isBorrowed;
    private User borrowedBy;
    private Edition edition;


    public Book(String id, String name, String author, double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.isBorrowed = false;
        this.borrowedBy = null;
    }

    // Getter and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public User getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(User borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    @Override
    public void borrowBook(User user) {
        if (this.isBorrowed) {
            System.out.println("\uD83D\uDC7D"+" Book is already borrowed by another user");
        } else if (user.getBorrowedBooks().size() >= 5) {
            System.out.println("\uD83D\uDC7D"+" User has already 5 borrowed books");
        } else {
            this.isBorrowed = true;
            this.borrowedBy = user;
            user.getBorrowedBooks().add(this);
            System.out.println("Book borrowed by user successfully");
        }
    }


    @Override
    public String toString() {
        return "com.wit.books.Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", isBorrowed=" + isBorrowed +
                ", borrowedBy=" + borrowedBy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


