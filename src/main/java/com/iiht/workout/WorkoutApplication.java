package com.iiht.workout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class WorkoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkoutApplication.class, args);
	}

	@Scheduled(fixedDelay = 300000)
	public void clearCache() {
		this.clearUserCache();
		this.clearWorkoutTransactionCache();
		this.clearUserWorkoutsCache();
	}

	@CacheEvict(value = "userCache", allEntries = true)
	private void clearUserCache() {
	}

	@CacheEvict(value = "txnCache", allEntries = true)
	private void clearWorkoutTransactionCache() {
	}

	@CacheEvict(value = "userWorkoutsCache", allEntries = true)
	private void clearUserWorkoutsCache() {
	}
}
