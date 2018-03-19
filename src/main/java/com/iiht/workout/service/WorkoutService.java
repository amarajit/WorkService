package com.iiht.workout.service;

import java.util.List;

import com.iiht.workout.domain.Workout;

public interface WorkoutService {

	public Workout create(Workout workout);

	public List<Workout> findByUser(Long userid);

}
