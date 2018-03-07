package com.iiht.workout.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iiht.workout.domain.UnitTime;
import com.iiht.workout.domain.User;
import com.iiht.workout.domain.Workout;
import com.iiht.workout.repository.WorkoutRepository;
import com.iiht.workout.service.WorkoutService;

public class WorkoutServiceTest {

	@InjectMocks
	private WorkoutService service;

	@Mock
	private WorkoutRepository repository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	User user = new User(2, "pwdFour", "JeetuAmar");
	Workout workout = new Workout(1, "Walking", 100.5, UnitTime.HOUR, user);

	@Test
	public void testCreateWorkout() {
		when(repository.save(workout)).thenReturn(workout);
		Workout respose = service.create(workout);
		assertThat(respose.equals(workout));
	}

	@Test
	public void testGetWorkouts() {
		List<Workout> workouts = new ArrayList<Workout>();
		workouts.add(workout);
		when(repository.findByUserId(workout.getUser().getId())).thenReturn(workouts);
		Workout respose = service.create(workout);
		assertThat(respose.equals(workout));
	}

}
