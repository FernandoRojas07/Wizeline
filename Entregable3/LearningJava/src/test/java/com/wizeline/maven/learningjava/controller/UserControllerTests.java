package com.wizeline.maven.learningjava.controller;

import com.wizeline.maven.learningjava.model.ResponseDTO;
import com.wizeline.maven.learningjava.model.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserControllerTests {

    private static final Logger LOGGER = Logger.getLogger(UserControllerTests.class.getName());

    int data = 1;
    private int codigoServ = 0;
    private String password = null;
    private String user = null;
    
    @Mock
    ResponseEntity<ResponseDTO> response;
    @Mock
    ResponseEntity<List<ResponseDTO>>  responseList;

    @Mock
    private UserDTO userDTO;

    @Autowired
    private UserController userController;

    @BeforeEach
    void antesPrueba() {
        data = 0;
        LOGGER.info("Antes de cada prueba " + data);

        if (user == null & password == null) {
            user = "fernando.rojas@elektra.com.mx";
            password = "pa$$12";
        }
    }

    @Test
    @DisplayName("Se prueba el happy path")
    public void procesandoCaminoFeliz() {

        LOGGER.info("Inica el servicio del Camino Feliz de login");
        response = userController.loginUser(user, password);
        codigoServ = response.getStatusCodeValue();

        LOGGER.info("Respuesta del servicio: " + response.getStatusCodeValue());
        assertEquals(200, codigoServ);
    }

    @Test
    @DisplayName("Prueba Servicios Login")
    public void pruebaLogin() {
        LOGGER.info("Se iniciando la prueba servicios Rest /login");
        response = userController.loginUser(user, password);
        codigoServ = response.getStatusCodeValue();

        LOGGER.info("Respuesta del servicio: " + response.getStatusCodeValue());
        assertEquals(200,codigoServ);
    }

    @Test
    @DisplayName("Prueba Servicios Login - Error")
    public void pruebaLoginError() {
        LOGGER.info("Se inicia la prueba del servicio Rest /login marcando error");
        response = userController.loginUser(user, "1235214");
        String codResp = response.getBody().getCode();
        LOGGER.info("Respuesta del servicio: " + response.getStatusCodeValue());
        assertEquals("ER001", codResp,"Error al hacer login");
    }

    @Test
    @DisplayName("Prueba Servicios Crear Usuario")
    public void pruebaCrearUsuario() {
        LOGGER.info("Se iniciando la prueba del servicio Rest /createUser");
        userDTO.setUser(user);
        userDTO.setPassword(password);
        response = userController.createUserAccount(userDTO);
        LOGGER.info("Respuesta del servicio: " + response.getStatusCodeValue());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba crear usuario Error")
    public void pruebaCrearUsuarioError() {
        password = null;
        user = null;
        LOGGER.info("Se iniciando la prueba servicios Rest /createUser con error");
        response = userController.createUserAccount(userDTO);
        String codigoResp = response.getBody().getStatus();
        LOGGER.info("Respuesta del servicio: " + response.getStatusCodeValue());
        assertEquals("fail", codigoResp, "No se pudeo crear el usuario");
    }

    @Test
    @DisplayName("Prueba Servicios de Crear Usuarios")
    public void pruebaCrearUsuarios() {
        LOGGER.info("Se iniciando la prueba del servicio Rest /createUsers");
        List<UserDTO> listDto = new ArrayList<>();
        userDTO.setUser("Fernando");
        userDTO.setPassword("pa$$12");
        listDto.add(userDTO);
        userDTO.setUser("Luis");
        userDTO.setPassword("pa$$12");
        listDto.add(userDTO);
        responseList = userController.createUsersAccount(listDto);
        LOGGER.info("Respuesta del servicio: " + response.getStatusCodeValue());
        assertEquals(200, responseList.getStatusCodeValue());
    }
}
