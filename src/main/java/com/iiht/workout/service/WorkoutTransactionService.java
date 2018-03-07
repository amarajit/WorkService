package com.iiht.workout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.iiht.workout.domain.WorkoutTransaction;
import com.iiht.workout.repository.WorkoutTxRepository;

@Service
public class WorkoutTransactionService {

	@Autowired
	WorkoutTxRepository workoutTxRepository;

	public WorkoutTransaction getWorkOutTxs(@PathVariable("workoutid") Long workoutid) {
		return workoutTxRepository.findOne(workoutid);
	}

	public WorkoutTransaction create(@RequestBody WorkoutTransaction workoutTx) {
		WorkoutTransaction _workoutTx = workoutTxRepository.save(workoutTx);
		return _workoutTx;
	}

}
