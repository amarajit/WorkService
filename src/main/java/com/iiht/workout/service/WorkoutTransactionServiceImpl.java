package com.iiht.workout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.workout.domain.WorkoutTransaction;
import com.iiht.workout.repository.WorkoutTxRepository;

@Service
public class WorkoutTransactionServiceImpl implements WorkoutTransactionService{
	
	@Autowired
	WorkoutTxRepository workoutTxRepository;
	
	@Override
	@Cacheable(value = "txnCache", key = "#workoutid", unless = "#result == null")
	public List<WorkoutTransaction> getWorkOutTxs(Long workoutid) {
		return workoutTxRepository.findByWorkoutId(workoutid);
	}
	
	@Override
	@Transactional
	@CachePut(value = "txnCache", /*key = "#result[0].workoutid",*/ unless = "#result == null")
	public WorkoutTransaction create(WorkoutTransaction workoutTx) {
		WorkoutTransaction _workoutTx = workoutTxRepository.save(workoutTx);
		return _workoutTx;
	}


}
