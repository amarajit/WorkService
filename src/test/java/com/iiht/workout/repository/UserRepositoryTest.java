package com.iiht.workout.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
    private UserRepository repository;
	
	@Autowired
	private CacheManager cacheManager;
	
	
	@Autowired
	private UserRepository repo;
	
	@Test
	public void testFindByEmailId() {
		Cache users = this.cacheManager.getCache("userCache");
		assertThat(users).isNotNull();
		users.clear();
		assertThat(users.get("Jithendra")).isNull();
		Long be = this.repository.findByName("Jithendra");
		assertNotNull(be);
	}


	
}
