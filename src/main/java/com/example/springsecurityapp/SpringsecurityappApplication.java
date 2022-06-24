package com.example.springsecurityapp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.springsecurityapp.entity.AppUser;
import com.example.springsecurityapp.entity.Role;
import com.example.springsecurityapp.repository.RoleRepository;
import com.example.springsecurityapp.repository.UserRepository;

@SpringBootApplication
public class SpringsecurityappApplication {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;


	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityappApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			AppUser a = new AppUser("akash", "pass");
			AppUser r = new AppUser("rod", "pass");
			
			Role r1 = new Role("ADMIN");
			Role r2 = new Role("USER");
			roleRepository.save(r1);
			roleRepository.save(r2);

			a.setRoles(Arrays.asList(r1, r2));
			r.setRoles(Arrays.asList(r2));

			userRepository.save(a);
			userRepository.save(r);

		};
	}
}
