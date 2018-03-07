package com.iiht.workout.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iiht.workout.domain.User;
import com.iiht.workout.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	

	@Autowired
	UserService userService;

	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody User user) {
		User _user = userService.create(user);
		assert _user != null;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setLocation(
				ServletUriComponentsBuilder.fromCurrentRequest().path("/" + _user.getId()).buildAndExpand().toUri());

		return new ResponseEntity<>(_user, httpHeaders, HttpStatus.CREATED);
	}

}
