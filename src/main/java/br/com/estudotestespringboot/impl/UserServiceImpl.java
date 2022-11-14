package br.com.estudotestespringboot.impl;

import java.util.List;
import java.util.Optional;

import br.com.estudotestespringboot.dto.UserDTO;
import br.com.estudotestespringboot.exceptions.DataIntegratyViolationException;
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
		findByEmail(obj);
		return repository.save(mapper.map(obj, User.class));
	}
	private void findByEmail(UserDTO obj){
		Optional<User> user = repository.findByEmail(obj.getEmail());
		if(user.isPresent() && !user.get().getId().equals(obj.getId())){
			throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");
		}

	}
	public User update(UserDTO obj){
		findByEmail(obj);
		return repository.save(mapper.map(obj, User.class));
	}
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);

	}
	
}
