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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        bankAccountDTO.setAccountActive(true);
        bankAccountDTO.setAccountBalance(5800.6223565952);
        bankAccountDTO.setAccountName("Dumy account Fer");
        bankAccountDTO.setAccountNumber(12364);
    }

    @Test
    @DisplayName("Prueba servicio GET /getUserAccount")
    public void pruebaUserAccount() {
        LOGGER.info("LearningJava - iniciando prueba getUserAccount");
        responseDTO = commonServices.login(user, password);
        LOGGER.info("Recibiendo respuesta de login - " + responseDTO.getCode());
        responseEntity = bankingAccountController.getUserAccount(user, password, fecha);
        LOGGER.info("Se obtiene el codigo: " + responseEntity.getStatusCodeValue() + " de la prueba GET /getUserAccount");
        //assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertAll(
                () -> Assertions.assertEquals("OK000", responseDTO.getCode()),
                () -> Assertions.assertEquals(200, responseEntity.getStatusCodeValue())
        );
    }

    @Test
    @DisplayName("Se prueba servicio getUserAccount con error")
    public void getUserAccountTestError() {
        LOGGER.info("LearningJava - iniciando prueba getUserAccount con error");
        String result = null;
        boolean concuerda;
        result = (String) bankingAccountController.getUserAccount(user, password, fecha).getBody();
        concuerda = result.contains("Incorrecto");
        LOGGER.info("Resultado: " + result);
        assertEquals(true, concuerda, "Prueba /getUserAccount con error exitosa");
    }

    @Test
    @DisplayName("Prueba servicio GET /getAccounts")
    public void pruebaGetAccounts() {
        LOGGER.info("LearningJava - iniciando prueba getAccounts");
        responseList = bankingAccountController.getAccounts();
        LOGGER.info("Se obtiene el codigo: " + responseList.getStatusCodeValue() + " de la prueba GET /getAccounts");
        assertEquals(200, responseList.getStatusCodeValue());
    }

    @Test
    @DisplayName("Se prueba servicio getAccounts con error")
    public void getAccountsTestError() {
        LOGGER.info("LearningJava - iniciando prueba getAccounts con error");
        responseList = bankingAccountController.getAccounts();
        LOGGER.info("Se obtiene el codigo: " + responseList.getStatusCodeValue() + " de la prueba GET /getAccounts con error");
        assertNotEquals(null, responseList.getBody());
    }

    @Test
    @DisplayName("Prueba servicio GET /getAccountByUser")
    public void pruebaGetAccountByUser() {
        LOGGER.info("LearningJava - iniciando prueba getAccountByUser");
        responseList = bankingAccountController.getAccountByUser(user);
        LOGGER.info("Se obtiene el codigo: " + responseList.getStatusCodeValue() + " de la prueba GET /getAccountByUser");
        assertEquals(200, responseList.getStatusCodeValue());
    }

    @Test
    @DisplayName("Se prueba servicio getAccountByUser con error")
    public void getAccountByUserTestError() {
        LOGGER.info("LearningJava - iniciando prueba getAccountByUser con error");
        List<BankAccountDTO> accounts = new ArrayList<>();
        responseList = bankingAccountController.getAccountByUser(user);
        LOGGER.info("Se obtiene el codigo: " + responseList.getStatusCodeValue() + " de la prueba GET /getAccountByUser con error");
        assertEquals(accounts, responseList.getBody());
    }
    @Test
    @DisplayName("Prueba servicio GET /getAccountsByType")
    public void pruebaGetAccountsByType() {
        LOGGER.info("LearningJava - iniciando prueba getAccountsByType");
        responseByType = bankingAccountController.getAccountsGroupByType();
        LOGGER.info("Se obtiene el codigo: " + responseByType.getStatusCodeValue() + " de la prueba GET getAccountsByType");
        assertEquals(200, responseByType.getStatusCodeValue());
        LOGGER.info("Se obtiene el codigo: " + responseByType.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba servicio PUT /editaCuenta/tipo/{tipo}")
    public void pruebaSendUserID() {
        LOGGER.info("LearningJava - iniciando prueba de la prueba PUT /editaCuenta/tipo/{tipo}");
        responseEntity = bankingAccountController.editaCuentas("APERTURA");
        LOGGER.info("Se obtiene el codigo: " + responseEntity.getStatusCodeValue() + " de la prueba PUT /editaCuenta/tipo/{tipo}");
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba servicio POST /inserta/cuenta")
    public void pruebaInsertaCuenta() {
        LOGGER.info("LearningJava - iniciando prueba Insert /inserta/cuenta");
        responseEntity = bankingAccountController.insertaCuenta(bankAccountDTO);
        LOGGER.info("Se obtiene el codigo: " + responseEntity.getStatusCodeValue() + " de la prueba Insert /inserta/cuenta");
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}
