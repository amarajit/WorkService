package com.iiht.workout.controller;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.workout.domain.WorkoutTransaction;
import com.iiht.workout.service.WorkoutTransactionService;

@RestController
@RequestMapping("/workouttxs")
public class WorkoutTxController {

	@Autowired
	WorkoutTransactionService workoutTxService;

	@GetMapping("/{workoutid}")
	public List<WorkoutTransaction> getWorkOutTxs(@PathVariable("workoutid") Long workoutid) {
		return workoutTxService.getWorkOutTxs(workoutid);
	}

/*	@PostMapping("/add")
	public ResponseEntity<?> create(@RequestBody WorkoutTransaction workoutTx) {
		WorkoutTransaction _workoutTx = workoutTxService.create(workoutTx);

		assert _workoutTx != null;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + _workoutTx.getId())
				.buildAndExpand().toUri());

		return new ResponseEntity<>(_workoutTx, httpHeaders, HttpStatus.CREATED);
	}*/
	
	
	@PostMapping("/add")
	public ResponseEntity<WorkoutTransaction> create(@RequestBody WorkoutTransaction workoutTx) {
		workoutTx.setDuration(Duration.between(workoutTx.getStartTime(), workoutTx.getStopTime()));
		workoutTx.setCalsBurnt(calBurnt(workoutTx.getDuration(), workoutTx.getWorkout().getCalBurntPerUnitTime()));
		WorkoutTransaction workoutTxns = workoutTxService.create(workoutTx);
		return new ResponseEntity<WorkoutTransaction>(workoutTxns, HttpStatus.OK);
	}

	public Double calBurnt(Duration duration, Double calBurntPerUnitTime) {
		long nanos = (long) duration.toNanos();
		Double calBurnt = (calBurntPerUnitTime) * (NANOSECONDS.toSeconds(nanos));
		return calBurnt;

	}

}
