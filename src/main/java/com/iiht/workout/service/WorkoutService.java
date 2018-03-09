package com.iiht.workout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.workout.domain.Workout;
import com.iiht.workout.repository.WorkoutRepository;

@Service
public class WorkoutService {

	@Autowired
	WorkoutRepository workoutRepository;

	public Workout create(Workout workout) {
		Workout _workout = workoutRepository.save(workout);
		return _workout;
	}

	public List<Workout> findByUser(Long userid) {
		List<Workout> workouts = workoutRepository.findByUserId(userid);
		return workouts;
	}

	public List<Workout> findByUserName(String userName) {
		List<Workout> workouts = workoutRepository.findByUserName(userName);
		return workouts;
	}

}
