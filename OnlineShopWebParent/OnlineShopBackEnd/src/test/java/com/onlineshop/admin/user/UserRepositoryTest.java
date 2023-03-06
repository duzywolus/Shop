package com.onlineshop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.onlineshop.common.entity.Role;
import com.onlineshop.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userTomek = new User("duzywolus@gmail.com", "nowe123", "Tomasz", "Wołek");
		userTomek.addRole(roleAdmin);
		
		User savedUser = userRepository.save(userTomek);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateNewUserWithTwoRoles() {
		User userJohn = new User("john@gmail.com", "john123", "John", "Bambo");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		
		userJohn.addRole(roleAssistant);
		userJohn.addRole(roleEditor);
		
		User savedUser = userRepository.save(userJohn);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testFindAllUsers() {
		Iterable<User> listUser = userRepository.findAll();
		listUser.forEach(user -> System.out.println(user));
		
	}
	
	@Test
	public void testGetUserById() {
		User userTomek = userRepository.findById(1).get();
		
		System.out.println(userTomek);
		assertThat(userTomek).isNotNull();
		
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userTomek = userRepository.findById(1).get();
		userTomek.setEnabled(true);
		userTomek.setPassword("tomek123");
		
		userRepository.save(userTomek);
	}
	
	@Test
	public void testUpdateUserRoles() {
		User userJohn = userRepository.findById(2).get();
		Role roleAssistant = new Role(5);
		Role roleSalesperson = new Role(2);
		
		userJohn.getRoles().remove(roleAssistant);
		userJohn.addRole(roleSalesperson);
		
		userRepository.save(userJohn);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		userRepository.deleteById(userId);
	}

	@Test
	public void testGetUserByEmail() {
		String email = "duzywolus@gmail.com";
		User user = userRepository.getUserByEmail(email);
		
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testCountById() {
		Integer id = 7;
		Long countById = userRepository.countById(id);
		
		assertThat(countById).isNotNull().isGreaterThan(0);
	}
	
	@Test
	public void testDisableUser() {
		Integer id = 1;
		userRepository.updateEnabledStatus(id, false);
		
	}
	
	@Test
	public void testEnableUser() {
		Integer id = 1;
		userRepository.updateEnabledStatus(id, true);
	}
	
}
