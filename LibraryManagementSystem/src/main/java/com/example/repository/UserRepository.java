package com.example.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByname(String username);

	List<User> findByBorrowedBooks_Book_Id(int bookId);

	List<User> findByBorrowedBooks_DueDate(Date dueDate);

	@Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.borrowedBooks bb WHERE bb IS NOT NULL")
	List<User> findAllUsersWithBorrowedBooks();

}