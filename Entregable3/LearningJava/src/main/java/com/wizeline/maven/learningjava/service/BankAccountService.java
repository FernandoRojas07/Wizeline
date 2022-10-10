/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.wizeline.maven.learningjava.service;

import com.wizeline.maven.learningjava.model.BankAccountDTO;
import com.wizeline.maven.learningjava.model.ResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankAccountService {

    List<BankAccountDTO> getAccounts();

    BankAccountDTO getAccountDetails(String user, String lastUsage);

    void deleteAccounts();

    List<BankAccountDTO> getAccountByUser(String user);

    ResponseEntity<ResponseDTO> editacuentas(String tipo);

    ResponseEntity<ResponseDTO> insertaCuenta(BankAccountDTO bankAccountDTO);

}
