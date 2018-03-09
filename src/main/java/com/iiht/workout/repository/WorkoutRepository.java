package com.iiht.workout.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iiht.workout.domain.Workout;

@Repository
public interface WorkoutRepository extends CrudRepository<Workout, Long> {
	
	@Query(value = "select * FROM Workout WHERE userid= ?1",  nativeQuery = true)
	List<Workout> findByUserId(@Param("userid") Long userid);
	
	@Query(value = "select * FROM Workout WHERE userid= (select userid from USER where user_name= ?1)",  nativeQuery = true)
	List<Workout> findByUserName(String userName);

}
