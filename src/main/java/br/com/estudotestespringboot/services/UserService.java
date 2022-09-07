package br.com.estudotestespringboot.services;

import br.com.estudotestespringboot.domain.User;

public interface UserService {

	User findById(Integer id);
}
