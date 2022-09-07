package br.com.estudotestespringboot.resources;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudotestespringboot.domain.User;
import br.com.estudotestespringboot.dto.UserDTO;
import br.com.estudotestespringboot.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
		//mapeie do User que veio do banco para o UserDTO
		return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
	}
}
