package com.example.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Book;
import com.example.model.BorrowRecord;
import com.example.model.User;
import com.example.repository.BookRepository;
import com.example.repository.UserRepository;

@Service
public class BookService {
	  @Autowired
	    private BookRepository bookRepository;

	    @Autowired
	    private UserRepository userRepository;

	    public List<Book> getAllBooks() {
	        return bookRepository.findAll();
	    }

	    public List<Book> getBooksByGenre(String genre) {
	        return bookRepository.findByGenre(genre);
	    }

	    public List<Book> getBooksByAuthor(String author) {
	        return bookRepository.findByAuthor(author);
	    }

	    public List<Book> getBooksByTitle(String title) {
	        return bookRepository.findByTitle(title);
	    }

	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }

	    public void addUser(User user) {
	        userRepository.save(user);
	    }

	    public void addBook(Book book) {
	        bookRepository.save(book);
	    }

	    public void borrowBook(int userId, int bookId, Date dueDate) {
	        User user = userRepository.findById(userId).orElse(null);
	        Book book = bookRepository.findById(bookId).orElse(null);

	        if (user != null && book != null && book.getAvailableCopies() > 0) {
	            BorrowRecord record = new BorrowRecord();
	            record.setUser(user);
	            record.setBook(book);
	            record.setDueDate(dueDate);

	            book.setAvailableCopies(book.getAvailableCopies() - 1);
	            book.inrementBorrowedCount();
	            user.getBorrowedBooks().add(record);

	            userRepository.save(user);
	            bookRepository.save(book);
	        }
	    }

	    public void returnBook(int userId, int bookId) {
	        User user = userRepository.findById(userId).orElse(null);
	        Book book = bookRepository.findById(bookId).orElse(null);

	        if (user != null && book != null) {
	            book.setAvailableCopies(book.getAvailableCopies() + 1);
	            book.decrementBorrowedCount();

	            user.getBorrowedBooks().removeIf(record -> record.getBook().getId() == bookId);

	            userRepository.save(user);
	            bookRepository.save(book);
	        }
	    }

	    public List<User> getUsersByBookId(int bookId) {
	        return userRepository.findByBorrowedBooks_Book_Id(bookId);
	    }

	    public List<User> getUsersByDueDate(Date dueDate) {
	        return userRepository.findByBorrowedBooks_DueDate(dueDate);
	    }
}
