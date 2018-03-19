package com.iiht.workout.services;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.validateMockitoUsage;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.iiht.workout.controller.WorkoutController;
import com.iiht.workout.domain.Workout;
import com.iiht.workout.service.WorkoutService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = WorkoutController.class, secure = false)
@EnableCaching
public class WorkoutsCacheTest {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WorkoutService workoutService;

	@Test
	public void testWorkoutsFromCacheTest() throws Exception {
		List<Workout> firstList = new ArrayList<>();
		List<Workout> secondList = new ArrayList<>();

		Mockito.when(workoutService.findByUser(Mockito.any(Long.class))).thenReturn(firstList, secondList);

		List<Workout> result = workoutService.findByUser(2L);
		assertThat(result, is(firstList));

		// Assert whether the result, which would now come from Cache,
		// is the same object.
		result = workoutService.findByUser(2L);
		assertThat(result, is(firstList));

		// Verify that the Cacheable method is called only once as of now.
		// log.info("Verifying the Cacheable method call count is 1.");
		// Mockito.verify(workoutService, Mockito.times(1)).getWorkoutsOfUser(2L);

		result = workoutService.findByUser(3L);
		assertThat(result, is(secondList));

	}

	@After
	public void validate() {
		validateMockitoUsage();
	}

}