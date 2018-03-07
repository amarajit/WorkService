package com.iiht.workout.controller;

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

import com.iiht.workout.domain.WorkoutTransaction;
import com.iiht.workout.service.WorkoutTransactionService;

@RestController
@RequestMapping("/workouttxs")
public class WorkoutTxController {

	@Autowired
	WorkoutTransactionService workoutTxService;

	@GetMapping("/{workoutid}")
	public WorkoutTransaction getWorkOutTxs(@PathVariable("workoutid") Long workoutid) {
		return workoutTxService.getWorkOutTxs(workoutid);
	}

	@PostMapping("/add")
	public ResponseEntity<?> create(@RequestBody WorkoutTransaction workoutTx) {
		WorkoutTransaction _workoutTx = workoutTxService.create(workoutTx);

		assert _workoutTx != null;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + _workoutTx.getId())
				.buildAndExpand().toUri());

		return new ResponseEntity<>(_workoutTx, httpHeaders, HttpStatus.CREATED);
	}

}
