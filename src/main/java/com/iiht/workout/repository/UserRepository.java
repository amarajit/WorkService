package com.iiht.workout.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iiht.workout.domain.User;


@Repository
public interface UserRepository extends CrudRepository<User, String>{
	
	@Query(value = "select userid FROM USER WHERE user_name=?1", nativeQuery=true)
	Long findByName(String userName);

}
