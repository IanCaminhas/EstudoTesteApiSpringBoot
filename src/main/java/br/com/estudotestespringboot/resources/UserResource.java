package br.com.estudotestespringboot.resources;

import br.com.estudotestespringboot.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.estudotestespringboot.dto.UserDTO;
import br.com.estudotestespringboot.services.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


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

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		return ResponseEntity.ok()
				.body(service.findAll()
						.stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj){
		User newObj = service.create(obj);
		//retorna o endereço do user criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}


}
