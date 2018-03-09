package com.iiht.workout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.workout.domain.User;
import com.iiht.workout.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User create(User user) {
		User _user = userRepository.save(user);
		return _user;
	}

	public Long authenticateUser(User user) {
		Long userId = userRepository.findByName(user.getUserName());
		if (userId != null)
			return userId;
		return null;
	}

	public Long getUserId(String userName) {

		return userRepository.findByName(userName);

	}

}
