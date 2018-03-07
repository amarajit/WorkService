package com.iiht.workout.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.iiht.workout.WorkoutApplication;
import com.iiht.workout.controller.UserController;
import com.iiht.workout.domain.User;
import com.iiht.workout.service.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WorkoutApplication.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	UserController userController;

	@MockBean
	UserService userService;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateUser() throws Exception {
		User user = new User((long) 1, "pwdthree", "JitAmara");
		given(userService.create(Mockito.any(User.class))).willReturn(user);
		String content = new Gson().toJson(user);
		ResultActions result = mockMvc.perform(post("/users/").content(content).contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().is2xxSuccessful());
		assertThat(result.andReturn().equals(user));
	}

}
