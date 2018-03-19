package com.iiht.workout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.workout.domain.User;
import com.iiht.workout.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public User create(User user) {
		User _user = userRepository.save(user);
		return _user;
	}

	@Override
	public Long authenticateUser(User user) {
		Long userId = userRepository.findByName(user.getUserName());
		if (userId != null)
			return userId;
		return null;
	}

	@Override
	@Cacheable("userCache")
	public Long getUserId(String userName) {
		return userRepository.findByName(userName);
	}

}
