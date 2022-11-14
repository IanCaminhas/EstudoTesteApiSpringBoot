package br.com.estudotestespringboot.services;

import br.com.estudotestespringboot.domain.User;
import br.com.estudotestespringboot.dto.UserDTO;

import java.util.List;

public interface UserService {
	User findById(Integer id);
	List<User> findAll();
	User create(UserDTO obj);
	User update(UserDTO obj);
	void delete(Integer id);


}
