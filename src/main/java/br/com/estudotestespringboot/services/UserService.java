package br.com.estudotestespringboot.services;

import br.com.estudotestespringboot.domain.User;

import java.util.List;

public interface UserService {
	User findById(Integer id);
	List<User> findAll();
}
