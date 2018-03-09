package com.iiht.workout.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkoutTxRepositoryTest {

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private WorkoutTxRepository workoutTxnsRepo;

	

}
