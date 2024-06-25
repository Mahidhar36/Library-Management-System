package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
	List<Book> findByGenre(String genre);
	List<Book> findByAuthor(String author);
	List<Book> findByTitle(String title);
}
