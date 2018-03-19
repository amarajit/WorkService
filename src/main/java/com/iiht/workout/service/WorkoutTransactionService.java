package com.iiht.workout.service;

import java.util.List;

import com.iiht.workout.domain.WorkoutTransaction;

public interface WorkoutTransactionService {

	public List<WorkoutTransaction> getWorkOutTxs(Long workoutid);

	public WorkoutTransaction create(WorkoutTransaction workoutTx);

}
