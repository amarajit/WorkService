package com.iiht.workout.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.iiht.workout.domain.User;
import com.iiht.workout.repository.UserRepository;
import com.iiht.workout.service.UserService;
import com.iiht.workout.service.UserServiceImpl;



@RunWith(SpringRunner.class)
public class UserServiceTest {

	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepository repository;

	@TestConfiguration
	static class Config {

		@Bean
		public UserService getService() {
			return new UserServiceImpl();
		}

	}

	/*
	 * @Before public void setup() { MockitoAnnotations.initMocks(this); }
	 */

	User sampleUser = new User(6, "pwdOne", "Jit4");

	@Test
	public void testCreateUser() {
		when(repository.save(sampleUser)).thenReturn(sampleUser);
		User respose = service.create(sampleUser);
		assertThat(respose.equals(sampleUser));
	}

}
