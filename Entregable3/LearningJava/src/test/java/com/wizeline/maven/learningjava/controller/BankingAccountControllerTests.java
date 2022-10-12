package com.wizeline.maven.learningjava.controller;

import com.wizeline.maven.learningjava.enums.AccountType;
import com.wizeline.maven.learningjava.model.BankAccountDTO;
import com.wizeline.maven.learningjava.model.ResponseDTO;
import com.wizeline.maven.learningjava.service.BankAccountService;
import com.wizeline.maven.learningjava.utils.CommonServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BankingAccountControllerTests {

    private static final Logger LOGGER = Logger.getLogger(BankingAccountControllerTests.class.getName());

    int data = 1;
    private int codigoServ = 0;
    private String fecha = null;
    private String password = null;
    private String user = null;

    @Autowired
    private BankAccountService bankAccountService;

    @Mock
    private BankAccountDTO bankAccountDTO;

    @Mock
    ResponseEntity responseEntity;

    @Mock
    ResponseEntity<Map<String, List<BankAccountDTO>>> responseByType;

    @Mock
    ResponseEntity<List<BankAccountDTO>> responseList;

    @Mock
    ResponseDTO responseDTO;

    @Autowired
    private BankingAccountController bankingAccountController;

    @Autowired
    CommonServices commonServices;

    @BeforeEach
    void antesPrueba() {
        data = 0;
        LOGGER.info("Antes de cada prueba " + data);
        if (user == null & password == null & fecha == null) {
            user = "fernando.rojas@elektra.com";
            password = "Pa$$12";
            fecha = "12-07-2022";
        }
        bankAccountDTO.setUserName("fernando.rojas@elektra.com");
        bankAccountDTO.setAccountType(AccountType.NOMINA);
        //bankAccountDTO.setAccountActive(true);
        bankAccountDTO.setAccountBalance(5800.6223565952);
        bankAccountDTO.setAccountName("Dumy account Fer");
        bankAccountDTO.setAccountNumber(12364);
    }

    @Test
    @DisplayName("Prueba servicio Get /getUserAccount")
    public void pruebaUserAccount() {
        LOGGER.info("LearningJava - iniciando prueba getUserAccount");
        responseDTO = commonServices.login(user, password);
        LOGGER.info("Recibiendo respuesta de login - " + responseDTO.getCode());
        responseEntity = bankingAccountController.getUserAccount(user, password, fecha);
        LOGGER.info("Se obtiene el codigo: " + responseEntity.getStatusCodeValue());
        //assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertAll(
                () -> Assertions.assertEquals("OK000", responseDTO.getCode()),
                () -> Assertions.assertEquals(200, responseEntity.getStatusCodeValue())
        );
    }

    @Test
    @DisplayName("Prueba servicio Get /getAccounts")
    public void pruebaGetAccounts() {
        LOGGER.info("LearningJava - iniciando prueba getAccounts");
        responseList = bankingAccountController.getAccounts();
        LOGGER.info("Se obtiene el codigo: " + responseList.getStatusCodeValue());
        assertEquals(200, responseList.getStatusCodeValue());
        LOGGER.info("Se obtiene el codigo: " + responseList.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba servicio Get /getAccountByUser")
    public void pruebaGetAccountByUser() {
        LOGGER.info("LearningJava - iniciando prueba getAccountByUser");
        responseList = bankingAccountController.getAccountByUser(user);
        LOGGER.info("Se obtiene el codigo: " + responseList.getStatusCodeValue());
        assertEquals(200, responseList.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba servicio Get /getAccountsByType")
    public void pruebaGetAccountsByType() {
        LOGGER.info("LearningJava - iniciando prueba getAccountsByType");
        responseByType = bankingAccountController.getAccountsGroupByType();
        LOGGER.info("Se obtiene el codigo: " + responseByType.getStatusCodeValue());
        assertEquals(200, responseByType.getStatusCodeValue());
        LOGGER.info("Se obtiene el codigo: " + responseByType.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba servicio update /editaCuenta/tipo/{tipo}")
    public void pruebaSendUserID() {
        LOGGER.info("LearningJava - iniciando prueba update /editaCuenta/tipo/{tipo}");
        responseEntity = bankingAccountController.editaCuentas("APERTURA");
        LOGGER.info("Se obtiene el codigo: " + responseEntity.getStatusCodeValue());
        assertEquals(200, responseEntity.getStatusCodeValue());
        LOGGER.info("Se obtiene el codigo: " + responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba servicio Insert /inserta/cuenta")
    public void pruebaInsertaCuenta() {
        LOGGER.info("LearningJava - iniciando prueba Insert /inserta/cuenta");
        responseEntity = bankingAccountController.insertaCuenta(bankAccountDTO);
        LOGGER.info("Se obtiene el codigo: " + responseEntity.getStatusCodeValue());
        assertEquals(200, responseEntity.getStatusCodeValue());
        LOGGER.info("Se obtiene el codigo: " + responseEntity.getStatusCodeValue());
    }

}
