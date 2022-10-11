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
    @DisplayName("Prueba Happy path")
    public void procesandoCaminoFeliz() {
        LOGGER.info("LearningJava - Inicia prueba Happy path");
        response = userController.loginUser(user, password);
        codigoServ = response.getStatusCodeValue();
        LOGGER.info("Respuesta del servicio: " + response.getStatusCodeValue() + " Happy path");
        assertEquals(200, codigoServ, "Prueba Happy path exitosa");
    }

    @Test
    @DisplayName("Prueba Servicios Login")
    public void pruebaLogin() {
        LOGGER.info("LearningJava - Se inicia la prueba servicios GET /login");
        response = userController.loginUser(user, password);
        codigoServ = response.getStatusCodeValue();
        LOGGER.info("Respuesta del servicio: " + response.getStatusCodeValue() + " del /login");
        assertEquals(200,codigoServ, "Prueba login exitosa");
    }

    @Test
    @DisplayName("Prueba Servicios Login - Error")
    public void pruebaLoginError() {
        LOGGER.info("LearningJava - Se inicia la prueba del servicio GET /login con error");
        response = userController.loginUser(user, "1235214");
        String codResp = response.getBody().getCode();
        LOGGER.info("Respuesta del servicio: " + response.getStatusCodeValue() + " de /login con error");
        assertEquals("ER001", codResp,"Prueba Error /login exitosa");
    }

    @Test
    @DisplayName("Prueba Servicios crear Usuario")
    public void pruebaCrearUsuario() {
        LOGGER.info("LearningJava - Se inicia la prueba del servicio /createUser");
        userDTO.setUser(user);
        userDTO.setPassword(password);
        response = userController.createUserAccount(userDTO);
        LOGGER.info("Respuesta del servicio: " + response.getStatusCodeValue() + " de /createUser.");
        assertEquals(200, response.getStatusCodeValue(), "Prueba /createUser exitosa");
    }

    @Test
    @DisplayName("Prueba crear usuario con Error")
    public void pruebaCrearUsuarioError() {
        password = null;
        user = null;
        LOGGER.info("LearningJava - Se inicia la prueba servicios Rest /createUser con error");
        response = userController.createUserAccount(userDTO);
        String codigoResp = response.getBody().getStatus();
        LOGGER.info("Respuesta del servicio: " + response.getStatusCodeValue() + " del servicio /createUser");
        assertEquals("fail", codigoResp, "Prueba /createUser con error exitosa.");
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
        LOGGER.info("Respuesta del servicio: " + response.getStatusCodeValue() + " del servicio /createUsers");
        assertEquals(200, responseList.getStatusCodeValue(), "Prueba /createUsers con error exitosa");
    }
}
