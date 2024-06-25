package com.example.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name=" Book")
public class Book {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private String genre;
    
    private int numberOfCopies;
    private int availableCopies;
    private boolean isAvailable = true;
    private int borrowedCount = 0;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BorrowRecord> borrowRecords = new ArrayList<>();
    
    
    
	public int getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	public int getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getBorrowedCount() {
		return borrowedCount;
	}

	public void setBorrowedCount(int borrowedCount) {
		this.borrowedCount = borrowedCount;
	}

	public List<BorrowRecord> getBorrowRecords() {
		return borrowRecords;
	}

	public void setBorrowRecords(List<BorrowRecord> borrowRecords) {
		this.borrowRecords = borrowRecords;
	}
	public void decrementBorrowedCount() {
		this.borrowedCount=borrowedCount-1;
	}
	public void inrementBorrowedCount() {
		this.borrowedCount=borrowedCount+11;
	}



    
}
