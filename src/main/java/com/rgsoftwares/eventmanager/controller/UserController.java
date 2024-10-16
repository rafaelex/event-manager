package com.rgsoftwares.eventmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgsoftwares.eventmanager.model.SuccessMessage;
import com.rgsoftwares.eventmanager.model.User;
import com.rgsoftwares.eventmanager.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@PostMapping("/create")
	public ResponseEntity<SuccessMessage> createUser(@RequestBody User user) {

		userDetailsService.create(user);
		return ResponseEntity.ok(new SuccessMessage(200, "User created successfully"));
	}
}
