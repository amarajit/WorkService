package com.iiht.workout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iiht.workout.domain.Workout;
import com.iiht.workout.service.WorkoutService;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {

	@Autowired
	WorkoutService workoutService;

	@GetMapping("/{userid}")
	public List<Workout> workout(@PathVariable("userid") Long userid) {
		return workoutService.findByUser(userid);
	}

	@PostMapping("/add")
	public ResponseEntity<?> create(@RequestBody Workout workout) {
		Workout _workout = workoutService.create(workout);

		assert _workout != null;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(
				ServletUriComponentsBuilder.fromCurrentRequest().path("/" + _workout.getId()).buildAndExpand().toUri());

		return new ResponseEntity<>(_workout, httpHeaders, HttpStatus.CREATED);
	}

}
