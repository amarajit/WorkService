package com.iiht.workout.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.iiht.workout.WorkoutApplication;
import com.iiht.workout.controller.WorkoutController;
import com.iiht.workout.domain.UnitTime;
import com.iiht.workout.domain.User;
import com.iiht.workout.domain.Workout;
import com.iiht.workout.service.WorkoutService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WorkoutApplication.class)
@WebMvcTest(value = WorkoutController.class, secure = false)
public class WorkoutControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	WorkoutController workoutController;

	@MockBean
	WorkoutService workoutService;

	User user = new User(2, "pwdFour", "JeetuAmar");
	Workout workout = new Workout(1, "Walking", 100.5, UnitTime.HOUR, user);

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.workoutController).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testcreateWorkout() throws Exception {
		given(workoutService.create(Mockito.any(Workout.class))).willReturn(workout);
		String content = new Gson().toJson(workout);
		ResultActions result = mockMvc
				.perform(post("/workouts/add").content(content).contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().is2xxSuccessful());
		assertThat(result.andReturn().equals(workout));
	}

	@Test
	public void testGetWorkoutDetails() throws Exception {
		List<Workout> sampleWorkoutResponse = new ArrayList<Workout>();
		sampleWorkoutResponse.add(workout);
		Mockito.when(workoutService.findByUser((long) 1)).thenReturn(sampleWorkoutResponse);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/workout/" + workout.getId())
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertThat(result.getResponse().equals(workout));
	}

}
