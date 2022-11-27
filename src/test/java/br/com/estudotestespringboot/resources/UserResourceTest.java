package br.com.estudotestespringboot.resources;

import br.com.estudotestespringboot.domain.User;
import br.com.estudotestespringboot.dto.UserDTO;
import br.com.estudotestespringboot.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserResourceTest {

    public static final String EMAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";
    private static final String NAME = "IAN";
    private static final Integer ID  = 1;
    private static final String EMAIL = "caminhasian@gmail.com";
    private static final String PASSWORD ="123";

    private User user;
    private UserDTO userDTO;

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserServiceImpl service;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
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
    }







}