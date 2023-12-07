package com.wit.library.people;

import com.wit.library.books.Book;

import java.util.ArrayList;
import java.util.List;

public class User extends Person {
    private List<Book> borrowedBooks;

    public User(String userId, String userName) {
        super(userId, userName);
        this.borrowedBooks = new ArrayList<>();
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public String toString() {
        return "User{" +
                "borrowedBooks=" + borrowedBooks +
                '}';
    }
}
