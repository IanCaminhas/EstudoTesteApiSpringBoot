package br.com.estudotestespringboot.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.estudotestespringboot.domain.User;
import br.com.estudotestespringboot.repositories.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {
	@Autowired
	private UserRepository repository;

	@Bean
	public void startDB() {
		User u1 = new User (null,"Ricardo","r@gmail.com","123");
		User u2 = new User (null,"Luiz","luiz@gmail.com","123");
		
		repository.saveAll(List.of(u1,u2));
	}
	
}
