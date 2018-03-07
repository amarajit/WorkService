package com.iiht.workout.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iiht.workout.domain.User;


@Repository
public interface UserRepository extends CrudRepository<User, String>{

}
