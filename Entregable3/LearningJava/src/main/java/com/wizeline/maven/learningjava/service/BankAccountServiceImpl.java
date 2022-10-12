/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.wizeline.maven.learningjava.service;

import static com.wizeline.maven.learningjava.utils.Utils.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import com.wizeline.maven.learningjava.model.BankAccountDTO;
import com.wizeline.maven.learningjava.model.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wizeline.maven.learningjava.LearningJavaApplication;
import com.wizeline.maven.learningjava.enums.Country;
import com.wizeline.maven.learningjava.repository.BankingAccountRepository;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private static final Logger LOGGER = Logger.getLogger(LearningJavaApplication.class.getName());

    @Autowired
    BankingAccountRepository bankAccountRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<BankAccountDTO> getAccounts() {
        List<BankAccountDTO> accountDTOList = new ArrayList<>();
        BankAccountDTO bankAccountOne = buildBankAccount("user3@wizeline.com", true, Country.MX, LocalDateTime.now().minusDays(7));
        accountDTOList.add(bankAccountOne);
        mongoTemplate.save(bankAccountOne);
        BankAccountDTO bankAccountTwo = buildBankAccount("user1@wizeline.com", false, Country.FR, LocalDateTime.now().minusMonths(2));
        accountDTOList.add(bankAccountTwo);
        mongoTemplate.save(bankAccountTwo);
        BankAccountDTO bankAccountThree = buildBankAccount("user2@wizeline.com" ,false, Country.US, LocalDateTime.now().minusYears(4));
        accountDTOList.add(bankAccountThree);
        mongoTemplate.save(bankAccountThree);
        mongoTemplate.findAll(BankAccountDTO.class).stream().map(bankAccountDTO -> bankAccountDTO.getUserName()).forEach(
                (user) -> {
                        LOGGER.info("User stored in bankAccountCollection " + user );
                });
        return accountDTOList;
    }

    @Override
    public ResponseEntity deleteAccounts(BankAccountDTO bankAccountDTO) {
        ResponseEntity<ResponseDTO> response;
        Query query = new Query();
        if(!bankAccountDTO.isAccountActive()) {
            query.addCriteria(Criteria.where("userName").is(bankAccountDTO.getUserName()));
            return new ResponseEntity<>(mongoTemplate.remove(query,BankAccountDTO.class),null,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,null,HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Override
    public List<BankAccountDTO> getAccountByUser(String user) {
        //Buscamos todos aquellos registros de tipo BankAccountDTO
        //que cumplen con la criteria de que el userName haga match
        //con la variable user
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(user));
        return mongoTemplate.find(query, BankAccountDTO.class);
    }

    @Override
    public BankAccountDTO getAccountDetails(String user, String lastUsage) {
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate usage = LocalDate.parse(lastUsage, dateformatter);
        return buildBankAccount(user, true, Country.MX, usage.atStartOfDay());
    }

    // Creación de tipo de dato BankAccount
    private BankAccountDTO buildBankAccount(String user, boolean isActive, Country country, LocalDateTime lastUsage) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setAccountNumber(randomAcountNumber());
        bankAccountDTO.setAccountName("Dummy Account ".concat(randomInt()));
        bankAccountDTO.setUserName(user);
        bankAccountDTO.setAccountBalance(randomBalance());
        bankAccountDTO.setAccountType(pickRandomAccountType());
        bankAccountDTO.setCountry(getCountry(country));
        bankAccountDTO.getLastUsage();
        bankAccountDTO.setCreationDate(lastUsage);
        bankAccountDTO.setAccountActive(isActive);
        return bankAccountDTO;
    }

    @Override
    public ResponseEntity editacuentas(String tipo){
        if(tipo.equals("")) {
            Query query = new Query();
            query.addCriteria(Criteria.where("accountActive").is(true));
            Update update = Update.update("title", "MongoTemplate").set("SecondType", tipo);
            return new ResponseEntity<>(mongoTemplate.updateMulti(query, update, BankAccountDTO.class), null, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity insertaCuenta(BankAccountDTO bankAccountDTO){
        ResponseEntity<ResponseDTO> response;
        if(bankAccountDTO.isAccountActive()) {
            return new ResponseEntity<>(mongoTemplate.save(bankAccountDTO,"bankAccountCollection"),null,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(mongoTemplate.save(bankAccountDTO),null,HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
