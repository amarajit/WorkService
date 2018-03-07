package com.iiht.workout.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.iiht.workout.domain.UnitTime;
import com.iiht.workout.domain.User;
import com.iiht.workout.domain.Workout;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WorkoutController.class })
@WebAppConfiguration
public class WorkoutControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testCreationOfANewWorkout() throws Exception {
		Workout workout = new Workout();
		workout.setCalBurntPerUnitTime(100);
		workout.setTitle("Running");
		workout.setUnitTime(UnitTime.MINUTE);
		User user = new User();
		user.setId(2);
		workout.setUser(user);
		String json = new Gson().toJson(workout);

		mockMvc.perform(post("/workouts/add").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(status().isCreated());
	}


}
