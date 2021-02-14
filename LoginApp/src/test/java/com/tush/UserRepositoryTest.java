package com.tush;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	public void testCreateUser() {
		
		User user = new User();
		
		user.setEmail("tushassr@gmail.com");
		user.setFirstName("tushar");
		user.setLastName("chemate");
		user.setPassword("abvsgv");

		User saveduser = repo.save(user);
		
		
		 User exitUser = em.find(User.class, saveduser.getId());
		 
		 assertThat(exitUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	@Test
	public void testUserByEmail() {
		
		String email = "tushar@gmail.com";
		
		User user = repo.findByEmail(email);
		assertThat(user).isNotNull();
		
	}
	

}
