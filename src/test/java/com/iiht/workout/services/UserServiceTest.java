package com.iiht.workout.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iiht.workout.domain.User;
import com.iiht.workout.repository.UserRepository;
import com.iiht.workout.service.UserService;

public class UserServiceTest {

	@InjectMocks
	private UserService service;

	@Mock
	private UserRepository repository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	User sampleUser = new User(6, "pwdOne", "Jit4");

	@Test
	public void testCreateUser() {
		when(repository.save(sampleUser)).thenReturn(sampleUser);
		User respose = service.create(sampleUser);
		assertThat(respose.equals(sampleUser));
	}

}
