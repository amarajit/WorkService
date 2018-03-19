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

import com.iiht.workout.domain.WorkoutTransaction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkoutTxRepositoryTest {

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private WorkoutTxRepository workoutTxnsRepo;
	
	
	@Test
	public void testFindByWorkId() {
		Cache workoutTxns = this.cacheManager.getCache("txnCache");
		assertThat(workoutTxns).isNotNull();
		workoutTxns.clear();
		assertThat(workoutTxns.get("Jithendra")).isNull();
		List<WorkoutTransaction> WorkoutTxnsRes = this.workoutTxnsRepo.findByWorkoutId((long)2);
		assertNotNull(WorkoutTxnsRes);
	}

	

}
