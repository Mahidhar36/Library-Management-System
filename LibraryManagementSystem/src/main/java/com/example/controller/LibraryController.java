package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Book;
import com.example.model.BorrowRecord;
import com.example.model.User;
import com.example.service.LibraryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LibraryController {

	@Autowired
	private LibraryService libraryService;

	@GetMapping("/home")
	public String homePage(HttpSession session, Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/login";
		}
		List<BorrowRecord> takenBooks = libraryService.getBorrowedBooksByUser(userId);
		List<Book> availableBooks = libraryService.getAllBooks();
		model.addAttribute("takenBooks", takenBooks);
		model.addAttribute("availableBooks", availableBooks);
		return "home";
	}

	@GetMapping("/homeAdmin")
	public String showOverview(Model model) {
		List<Book> books = libraryService.getAllBooks();
		List<User> users = libraryService.findAllUsersWithRentedBooks();

		model.addAttribute("books", books);
		model.addAttribute("users", users);

		return "home_admin"; // The name of the Thymeleaf template
	}

	@PostMapping("/rent/{bookId}/")
	public String rentBook(@PathVariable int bookId, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		System.out.println("User ID: " + userId);
		if (userId != null) {
			libraryService.rentBook(userId, bookId);
		}
		return "redirect:/home";
	}

	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

}