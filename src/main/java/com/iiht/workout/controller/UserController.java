package com.iiht.workout.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.gson.Gson;
import com.iiht.workout.domain.User;
import com.iiht.workout.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/add")
	public ResponseEntity<?> create(@RequestBody User user) {
		Map<String, Object> message = new HashMap<String, Object>();
		Long userId = userService.getUserId(user.getUserName());
		ResponseEntity<String> response = null;
		if (null != userId && 0 != userId) {
			message.put("message", "User already registered.");
			message.put("Status", "Error");
			response = new ResponseEntity<String>(new Gson().toJson(message), HttpStatus.FORBIDDEN);
		} else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User _user = userService.create(user);
			assert _user != null;
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + _user.getId())
					.buildAndExpand().toUri());
			message.put("message", "User registered !");
			message.put("Status", "Success");
			response = new ResponseEntity<String>(new Gson().toJson(message), HttpStatus.OK);
		}

		return response;
	}

	@GetMapping(value = "/login")
	public ResponseEntity<String> authenticateUser(@RequestHeader("userName") String userName,
			@RequestHeader("password") String password) {

		ResponseEntity<String> response = null;
		User user = new User(0, userName, password);
		Long userId = userService.authenticateUser(user);
		Map<String, Object> message = new HashMap<String, Object>();
		if (userId != null) {
			message.put("message", "User Authenticated Successfully.");
			message.put("Status", "Sucess");
			response = new ResponseEntity<String>(new Gson().toJson(message), HttpStatus.OK);
		} else {
			message.put("message", "Invalid User.");
			message.put("Status", "Error");
			response = new ResponseEntity<String>(new Gson().toJson(message), HttpStatus.FORBIDDEN);
		}

		return response;
	}

}
