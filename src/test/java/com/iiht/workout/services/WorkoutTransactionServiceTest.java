package com.iiht.workout.services;

import java.time.LocalDateTime;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iiht.workout.domain.UnitTime;
import com.iiht.workout.domain.User;
import com.iiht.workout.domain.Workout;
import com.iiht.workout.domain.WorkoutTransaction;
import com.iiht.workout.repository.WorkoutTxRepository;
import com.iiht.workout.service.WorkoutTransactionService;

public class WorkoutTransactionServiceTest {

	@InjectMocks
	private WorkoutTransactionService service;

	@Mock
	private WorkoutTxRepository repository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	User user = new User(2, "pwdFour", "JeetuAmar");
	Workout workout = new Workout(1, "Walking", 100.5, UnitTime.HOUR, user);

	WorkoutTransaction workoutTxn = new WorkoutTransaction((long) 1, LocalDateTime.now(), LocalDateTime.now(), null, 0,
			workout);

}
