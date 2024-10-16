package com.rgsoftwares.eventmanager.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rgsoftwares.eventmanager.model.User;
import com.rgsoftwares.eventmanager.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	private final PasswordEncoder passwordEncoder;
	
	public UserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
	
	public User findUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		return user;
	}
	
	public User create(User user) {
        // Check if username already exists
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        return repository.save(user);
    }
	
	public boolean existesById(Long id) {
		return repository.existsById(id);
	}
	
}
