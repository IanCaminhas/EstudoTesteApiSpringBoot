package br.com.estudotestespringboot.impl;

import br.com.estudotestespringboot.domain.User;
import br.com.estudotestespringboot.dto.UserDTO;
import br.com.estudotestespringboot.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {
    private static final String NAME = "IAN";
    private static final Integer ID  = 1;
    private static final String EMAIL = "caminhasian@gmail.com";
    private static final String PASSWORD ="123";

    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;
    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    //o que ser executado antes dos testes
    @BeforeEach
    void setUp(){
        //vai iniciar os mocks da classes passadas como parâmetros. No caso: repository e mapper
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        //Quando qualquer id for passado para retornar o User, retorne o optionalUser iniciado em setUp
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        User response = service.findById(ID);

        //garanta pra mim que o retorno não vai ser nulo
        assertNotNull(response);
        //Com o assertion, quero garantir que o objeto retornado é do tipo User
        assertEquals(User.class, response.getClass());
        //garanta pra mim que o id retornado é o mesmo que o passado(ID)
        assertEquals(ID,response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());

    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID,NAME,EMAIL,PASSWORD);
        userDTO = new UserDTO(ID,NAME,EMAIL,PASSWORD);
        optionalUser =  Optional.of(new User(ID,NAME,EMAIL,PASSWORD));
    }
}