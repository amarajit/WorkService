package com.iiht.workout.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.iiht.workout.domain.UnitTime;
import com.iiht.workout.domain.User;
import com.iiht.workout.domain.Workout;
import com.iiht.workout.repository.WorkoutRepository;
import com.iiht.workout.service.WorkoutService;
import com.iiht.workout.service.WorkoutServiceImpl;

@RunWith(SpringRunner.class)
public class WorkoutServiceTest {

	@Autowired
	private WorkoutService service;

	@MockBean
	private WorkoutRepository repository;

	@TestConfiguration
	static class Config {

		@Bean
		public WorkoutService getService() {
			return new WorkoutServiceImpl();
		}

	}

	/*
	 * @Before public void setup() { MockitoAnnotations.initMocks(this); }
	 */
	User user = new User(5, "pwdFour", "JeetuAmar");
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
		List<Workout> workouts1 = service.findByUser(workout.getUser().getId());
		assertNotNull(workouts1);
	}

}
