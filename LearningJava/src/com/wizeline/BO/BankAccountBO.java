package com.wizeline.BO;

import java.util.List;

import com.wizeline.DTO.BankAccountDTO;

public interface BankAccountBO {

	BankAccountDTO getAccountDetails(String user, String lastUsage);
	
	List<BankAccountDTO> getAccounts();

}
