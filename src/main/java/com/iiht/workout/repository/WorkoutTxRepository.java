package com.iiht.workout.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iiht.workout.domain.WorkoutTransaction;

public interface WorkoutTxRepository extends CrudRepository<WorkoutTransaction, Long> {
	
	@Query(value = " SELECT * FROM workout_transaction t WHERE t.workoutid = ?1", nativeQuery = true)
	List<WorkoutTransaction> findByWorkoutId(@Param("workoutid") Long workoutid);

}
