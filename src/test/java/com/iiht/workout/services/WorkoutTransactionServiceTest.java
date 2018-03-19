package com.iiht.workout.services;

import java.time.LocalDateTime;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.iiht.workout.domain.UnitTime;
import com.iiht.workout.domain.User;
import com.iiht.workout.domain.Workout;
import com.iiht.workout.domain.WorkoutTransaction;
import com.iiht.workout.repository.WorkoutTxRepository;
import com.iiht.workout.service.WorkoutTransactionService;
import com.iiht.workout.service.WorkoutTransactionServiceImpl;
@RunWith(SpringRunner.class)
public class WorkoutTransactionServiceTest {

	@Autowired
	private WorkoutTransactionService service;

	@MockBean
	private WorkoutTxRepository repository;
	
	@TestConfiguration
	static class Config {

		@Bean
		public WorkoutTransactionService getService() {
			return new WorkoutTransactionServiceImpl();
		}

	}
/*
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}*/

	User user = new User(2, "pwdFour", "JeetuAmar");
	Workout workout = new Workout(1, "Walking", 100.5, UnitTime.HOUR, user);

	WorkoutTransaction workoutTxn = new WorkoutTransaction((long) 1, LocalDateTime.now(), LocalDateTime.now(), null, 0,
			workout);

}
