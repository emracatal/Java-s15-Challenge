package com.wit.library;

import com.wit.library.books.Book;
import com.wit.library.people.Librarian;
import com.wit.library.people.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //initiate a librarian
        Librarian librarianEmra = new Librarian("l1", "emra", "1");

        //initiate library with a librarian
        Library library = new Library(librarianEmra);

        //initiate books in library
        Book book1 = new Book("B1", "To Kill a Mockingbird", "Harper Lee", 25.0);
        Book book2 = new Book("B2", "1984", "George Orwell", 30.0);
        Book book10 = new Book("B2", "Animal Farm", "George Orwell", 30.0);//same ID for trial
        Book book3 = new Book("B3", "Animal Farm", "George Orwell", 30.0);
        Book book4 = new Book("B4", "Fahrenheit 451", "Ray Bradbury", 30.0);
        Book book5 = new Book("B5", "Huckleberry Finn", "Mark Twain", 30.0);

        //add books to library
        library.addBook(book1); //should be OK
        library.addBook(book2); // should be OK
        library.addBook(book10); // not OK because of same ID with book2
        library.addBook(book3); // should be OK
        library.addBook(book4); // should be OK
        library.addBook(book5); // should be OK

        //initiate users
        User user1 = new User("U1", "çağatay");
        User user2 = new User("U2", "metehan");
        User user3 = new User("U3", "kaan");

        //add users to library
        library.addUser(user1);
        library.addUser(user2);
        //Scanner initialized
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        System.out.println("================== WELCOME TO LIBRARY ==================");
        while (isRunning) {
            System.out.println("               PLEASE SELECT AN OPTION: ");
            System.out.println("1 - SEARCH BOOK/LIST ALL BOOKS           4 - ADD NEW BOOK"); //OK
            System.out.println("2 - SEARCH BOOK BY AUTHOR                5 - BORROW BOOK"); //TODO:equalsignorecase usage
            System.out.println("3 - LIST ALL USERS                       6 - RETURN BOOK"); //OK
            System.out.println("               0 - EXIT");

            try {
                String choice = scanner.nextLine();
                switch (choice) {
                    case "0":
                        isRunning = false;
                        scanner.close();
                        break;
                    case "1":
                        System.out.println("press ENTER for full list or enter a KEYWORD like book name or author name");
                        String bookName = scanner.nextLine();
                        System.out.println("=========================================");
                        library.listSearchBooks(bookName);
                        System.out.println("=========================================");
                        break;
                    case "2":
                        System.out.println("Enter author name: ");
                        String authorName = scanner.nextLine();
                        System.out.println("=========================================");
                        library.searchByAuthor(library.getBooks().values(),authorName);
                        System.out.println("=========================================");
                        break;
                    case "3":
                        System.out.println("=========================================");
                        library.listAllUsers();
                        System.out.println("=========================================");
                        break;
                    case "4":
                        System.out.println("Enter books infos: ");
                        System.out.println("ID:");
                        String newBookId = scanner.nextLine();
                        System.out.println("Book name:");
                        String newBookName = scanner.nextLine();
                        System.out.println("Author:");
                        String newBookAuthor = scanner.nextLine();
                        System.out.println("Price:");
                        double newBookPrice = scanner.nextDouble();
                        scanner.nextLine();
                        library.addBook(newBookId, newBookName, newBookAuthor, newBookPrice);
                        System.out.println(GlobalEmojis.checkMark+"Book added");
                        break;
                    case "5":
                        System.out.println("Enter user ID to borrow:");
                        String userIdForBorrow = scanner.nextLine();
                        System.out.println("Enter book ID to borrow:");
                        String borrowBookId = scanner.nextLine();
                        System.out.println("=========================================");
                        library.borrowBook(borrowBookId, userIdForBorrow);
                        System.out.println("=========================================");
                        break;
                    case "6":
                        System.out.println("Enter book ID to return:");
                        String returnBookId = scanner.nextLine();
                        library.returnBook(returnBookId);
                        break;
                    default:
                        System.out.println(GlobalEmojis.alien+" invalid input,try again");
                        break;
                }
            } catch (Exception ex) {
                System.out.println(GlobalEmojis.alien+ " error occured!");
                isRunning = false;
            }
        }

        scanner.close();
    }
}