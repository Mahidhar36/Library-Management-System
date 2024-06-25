package com.example.model;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<BorrowRecord> borrowedBooks = new ArrayList<>();

	public User() {

	}

	public User(String name, String password, Role role) {
		this.name = name;
		this.password = password;
		this.role = role;

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User(int id, String name) {
		this.id = id;
		this.name = name;
		this.borrowedBooks = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BorrowRecord> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(List<BorrowRecord> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}

}
