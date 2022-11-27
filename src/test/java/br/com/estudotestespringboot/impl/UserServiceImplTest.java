package br.com.estudotestespringboot.impl;

import br.com.estudotestespringboot.domain.User;
import br.com.estudotestespringboot.dto.UserDTO;
import br.com.estudotestespringboot.exceptions.DataIntegratyViolationException;
import br.com.estudotestespringboot.exceptions.ObjectNotFoundException;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {
    public static final String EMAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";
    private static final String NAME = "IAN";
    private static final Integer ID  = 1;
    private static final String EMAIL = "caminhasian@gmail.com";
    private static final String PASSWORD ="123";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final int INDEX = 0;

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

    //quando não achar o objeto de acordo com o id passado, retorne uma exceção de objeto não encontrado
    @Test
    void whenFindBuyIdThenReturnAnObjectNotFoundException(){
        //Mocando uma resposta para a busca por id
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            service.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            //Garanta que a mensagem da exceção seja igual a informada
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        //Quando o repository.findAll() for chamado, retorne a lista de apenas um usuário
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();
        //assegure que a lista não veio vazia
        assertNotNull(response);
        //Assegure que  lista possua 1 usuário
        assertEquals(1,response.size());
        //Quero assegurar que o primeiro elemento seja da classe User
        assertEquals(User.class, response.get(0).getClass());
        //Quero assegurar que o ID retornado é igual ao ID constante
        assertEquals(ID, response.get(INDEX).getId());

        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnSucess() {
        when(repository.save(any())).thenReturn(user);
        User response = service.create(userDTO);

        //assegurando que a resposta não é nula
        assertNotNull(response);
        //O response retornado seja do tipo User
        assertEquals(User.class, response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());
        assertEquals(EMAIL,response.getEmail());
        assertEquals(PASSWORD,response.getPassword());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.create(userDTO);
        }catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(EMAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSucess() {
        when(repository.save(any())).thenReturn(user);
        User response = service.update(userDTO);

        //assegurando que a resposta não é nula
        assertNotNull(response);
        //O response retornado seja do tipo User
        assertEquals(User.class, response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());
        assertEquals(EMAIL,response.getEmail());
        assertEquals(PASSWORD,response.getPassword());
    }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.update(userDTO);
        }catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(EMAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        //Não faça nada quando o meu repository.delete() for invocado
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
        //estou vericando quantas vezes o repository foi chamado no método deleteById
        //Se for mais de 1 vez, tem coisa errada no código
        verify(repository,times(1)).deleteById(anyInt());
    }

    @Test
    void deteleWithObjectNotFoundException(){
        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try{
            service.delete(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());

        }
    }

    private void startUser(){
        user = new User(ID,NAME,EMAIL,PASSWORD);
        userDTO = new UserDTO(ID,NAME,EMAIL,PASSWORD);
        optionalUser =  Optional.of(new User(ID,NAME,EMAIL,PASSWORD));
    }
}