package com.iiht.workout.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.iiht.workout.WorkoutApplication;
import com.iiht.workout.controller.WorkoutTxController;
import com.iiht.workout.domain.UnitTime;
import com.iiht.workout.domain.User;
import com.iiht.workout.domain.Workout;
import com.iiht.workout.domain.WorkoutTransaction;
import com.iiht.workout.service.WorkoutTransactionService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WorkoutApplication.class)
@WebMvcTest(value = WorkoutTxController.class, secure = false)
public class WorkoutTxControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	WorkoutTxController workOutTransactionController;

	@MockBean
	WorkoutTransactionService workoutTxnService;

	User user = new User((long) 1, "pwdFour", "JeetuAmara");
	Workout workout= new Workout(1, "Walking", 100.5, UnitTime.HOUR, user);
	WorkoutTransaction workoutTxn = new WorkoutTransaction((long) 1, LocalDateTime.now(), LocalDateTime.now(),
			null, 0, workout);

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.workOutTransactionController).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreate() throws Exception {
		String content = new Gson().toJson(workoutTxn);
		mockMvc.perform(post("/workouttxs/add").content(content).contentType(APPLICATION_JSON_UTF8)).andReturn();
	}

}
