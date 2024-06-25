package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findByUsername(String username) {
		return userRepository.findByname(username);
	}

	public void saveUser(User user) {

		userRepository.save(user);
	}

}
