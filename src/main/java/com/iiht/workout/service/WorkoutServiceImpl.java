package com.iiht.workout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.workout.domain.Workout;
import com.iiht.workout.repository.WorkoutRepository;

@Service
public class WorkoutServiceImpl implements WorkoutService {

	@Autowired
	WorkoutRepository workoutRepository;

	@Override
	@Transactional
	@CachePut(value = "userWorkoutsCache", /*key = "#result[0].userid",*/ unless = "#result == null")
	public Workout create(Workout workout) {
		Workout _workout = workoutRepository.save(workout);
		return _workout;
	}

	@Override
	@Cacheable(value = "userWorkoutsCache", key = "#userid")
	public List<Workout> findByUser(Long userid) {
		List<Workout> workouts = workoutRepository.findByUserId(userid);
		return workouts;
	}

}
