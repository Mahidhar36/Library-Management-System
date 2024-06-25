package com.example.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Book;
import com.example.model.BorrowRecord;
import com.example.model.User;
import com.example.repository.BookRepository;
import com.example.repository.BorrowRecordRepository;
import com.example.repository.UserRepository;

@Service
public class LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<BorrowRecord> getBorrowedBooksByUser(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(User::getBorrowedBooks).orElse(null);
    }

    public void rentBook(int userId, int bookId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Book> book = bookRepository.findById(bookId);

        if (user.isPresent() && book.isPresent() && book.get().getAvailableCopies() > 0) {
            Book bookToUpdate = book.get();
            bookToUpdate.setAvailableCopies(bookToUpdate.getAvailableCopies() - 1);
            if (bookToUpdate.getAvailableCopies() == 0) {
                bookToUpdate.setAvailable(false);
            }
            bookRepository.save(bookToUpdate);
            System.out.println(bookToUpdate);

            BorrowRecord borrowRecord = new BorrowRecord();
            borrowRecord.setUser(user.get());
            borrowRecord.setBook(bookToUpdate);
            borrowRecord.setDueDate(new Date(System.currentTimeMillis() + (14 * 24 * 60 * 60 * 1000))); // Due in 14 days
            borrowRecordRepository.save(borrowRecord);
        }
        System.out.println("No");
    }

	public List<User> findAllUsersWithRentedBooks() {
		return userRepository.findAllUsersWithBorrowedBooks();
		
	}

	
}