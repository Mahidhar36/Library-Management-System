package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Role;
import com.example.model.User;
import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam("name") String name, @RequestParam("password") String password,
			RedirectAttributes attributes, HttpSession session) {

		List<User> users = userService.findByUsername(name);
		for (User u : users) {
			if (password.equals(u.getPassword())) {
				session.setAttribute("userName", name);
				session.setAttribute("userId", u.getId());
				if (u.getRole() == Role.ADMIN) {
					return "redirect:/homeAdmin";
				}
				return "redirect:/home";
			}
		}
		attributes.addFlashAttribute("error", "Invalid username or password");
		return "redirect:/login";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@RequestParam("name") String name, @RequestParam("password") String password,
			@RequestParam("role") Role role, HttpSession session) {
		if (role == Role.ADMIN || role == Role.USER) {
			User user = new User(name, password, role);
			userService.saveUser(user);
			session.setAttribute("userName", name);
			session.setAttribute("userId", user.getId());
		}
		if (role == Role.ADMIN) {
			return "redirect:/homeAdmin";
		}
		return "redirect:/home";

	}
}
