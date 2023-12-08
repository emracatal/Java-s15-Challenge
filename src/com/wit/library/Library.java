package com.wit.library;

import com.wit.library.books.Book;
import com.wit.library.people.Librarian;
import com.wit.library.people.Person;
import com.wit.library.people.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Library {
    private Map<String, Book> books;
    private Map<String, Person> users;
    private Librarian librarian;

    public Library(Librarian librarian) {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.librarian = librarian;
    }

    public Map<String, Book> getBooks() {
        return books;
    }

    public void setBooks(Map<String, Book> books) {
        this.books = books;
    }

    public Map<String, Person> getUsers() {
        return users;
    }

    public void setUsers(Map<String, Person> users) {
        this.users = users;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public void addBook(Book book) {
        if (books.containsKey(book.getId())) {
            System.out.println(GlobalEmojis.alien + " Book is already available");
        } else {
            books.put(book.getId(), book);
            System.out.println(GlobalEmojis.checkMark + " " + book.getName() + " added to library successfully");
        }
    }

    public void removeBook(String bookId) {
        if (books.containsKey(bookId)) {
            Book removedBook = books.get(bookId);
            books.remove(bookId);
        } else {
            System.out.println(GlobalEmojis.alien + " Couldn't find");
        }
    }


    public void addUser(Person person) {
        users.put(person.getPersonId(), person);
    }


    public void borrowBook(String bookId, String personId) {
        if (books.containsKey(bookId) && users.containsKey(personId)) {
            Book book = books.get(bookId);
            User user = (User) users.get(personId);

            book.borrowBook(user);
        } else {
            System.out.println(GlobalEmojis.alien + " No such user or book found!");
        }
    }

    public void returnBook(String bookId) {
        if (books.containsKey(bookId)) {
            Book book = books.get(bookId);
            if (book.isBorrowed()) {
                User borrowedBy = book.getBorrowedBy();
                borrowedBy.getBorrowedBooks().remove(book);
                book.setBorrowedBy(null);
                book.setBorrowed(false);
                System.out.println(GlobalEmojis.checkMark + " Book returned successfully");
            } else {
                System.out.println(GlobalEmojis.alien + " Book is not borrowed!");
            }
        } else {
            System.out.println(GlobalEmojis.alien + " No such book found!");
        }
    }


    public Book getBookById(String bookId) {
        return books.get(bookId);
    }

    public void listAllUsers() {
        for (Person person : users.values()) {
            System.out.println("User ID: " + person.getPersonId() + ", User name: " + person.getName());
            if (person instanceof User) {
                List<Book> borrowedBooks = ((User) person).getBorrowedBooks();
                if (!borrowedBooks.isEmpty()) {
                    System.out.println("Borrowed books: ");
                    for (Book book : borrowedBooks) {
                        System.out.println("  - " + book.getName());
                    }
                } else {
                    System.out.println(GlobalEmojis.alien + " No books borrowed by user");
                }
            }
        }
    }


    public void addBook(String id, String name, String author, double price) {
        Book book = new Book(id, name, author, price);
        addBook(book);
    }

    public void listSearchBooks(String bookName) {
        boolean found = false;
        for (Book book : books.values()) {
            if (book.getName().toLowerCase().contains(bookName.toLowerCase()) || (book.getAuthor().toLowerCase().contains(bookName.toLowerCase()))) {
                if (book.isBorrowed()) {
                    System.out.println("ID: " + book.getId() + ", Book name: " + book.getName() + ", Author: " + book.getAuthor() + ", Borrowed by: " + book.getBorrowedBy().getName());
                } else {
                    System.out.println("ID: " + book.getId() + ", Book name: " + book.getName() + ", Author: " + book.getAuthor() + ", Book is available");
                }
                found = true;
            }
        }
        if (!found) {
            System.out.println(GlobalEmojis.alien + " No such book found!");
        }
    }

    public void searchByAuthor(Collection<Book> books, String authorName) {
        List<Book> booksByAuthor = books.stream().filter(book -> book.getAuthor().contains(authorName)).collect(Collectors.toList());
        if (booksByAuthor.isEmpty()) {
            System.out.println(GlobalEmojis.alien+" No such author's book found!");
        } else {
            for (Book book : booksByAuthor) {
                System.out.println(" - " + book.getName());
            }
        }
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                ", users=" + users +
                ", librarian=" + librarian +
                '}';
    }
}