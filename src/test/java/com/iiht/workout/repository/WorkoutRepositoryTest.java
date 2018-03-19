package com.iiht.workout.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.iiht.workout.domain.Workout;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkoutRepositoryTest {

	@Autowired
	private WorkoutRepository workoutRepository;
	
	@Autowired
	private CacheManager cacheManager;

	@Test
	public void testFindByUserId() {
		Cache workouts = this.cacheManager.getCache("userWorkoutsCache");
		assertThat(workouts).isNotNull();
		workouts.clear();
		List<Workout> response = this.workoutRepository.findByUserId(8L);
		assertNotNull(response);
	}

	

}
