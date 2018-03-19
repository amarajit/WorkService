package com.iiht.workout.service;

import com.iiht.workout.domain.User;


public interface UserService {

	public User create(User user);

	public Long authenticateUser(User user);

	public Long getUserId(String userName);
}
