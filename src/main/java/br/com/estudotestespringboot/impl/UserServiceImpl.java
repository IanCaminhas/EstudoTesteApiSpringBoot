package br.com.estudotestespringboot.impl;

import java.util.List;
import java.util.Optional;

import br.com.estudotestespringboot.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudotestespringboot.domain.User;
import br.com.estudotestespringboot.exceptions.ObjectNotFoundException;
import br.com.estudotestespringboot.repositories.UserRepository;
import br.com.estudotestespringboot.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public User findById(Integer id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public List<User> findAll(){
		return repository.findAll();
	}

	public User create(UserDTO obj) {
		return repository.save(mapper.map(obj, User.class));
	}

	
}
