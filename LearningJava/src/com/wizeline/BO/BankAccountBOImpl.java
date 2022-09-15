package com.wizeline.BO;

import java.util.ArrayList;
import java.util.List;

import com.wizeline.DTO.BankAccountDTO;
import com.wizeline.enums.AccountType;
import com.wizeline.enums.Country;
import com.wizeline.utils.Utils;

public class BankAccountBOImpl implements BankAccountBO {
	
	Utils utils = new Utils();

	@Override
	public BankAccountDTO getAccountDetails(String user, String lastUsage) {
		return buildBankAccount(user, true, Country.MX, lastUsage);
	}
	
	private BankAccountDTO buildBankAccount(String user, boolean isActive, Country country, String lastUsage) {
		BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setAccountNumber(utils.randomAcountNumber());
        bankAccountDTO.setAccountName("Dummy Account".concat(utils.randomInt()));
        bankAccountDTO.setUser(user);
        bankAccountDTO.setAccountBalance(utils.randomBalance());
        bankAccountDTO.setAccountType(utils.pickRandomAccountType());
        bankAccountDTO.setCountry(utils.getCountry(country));
        bankAccountDTO.setAccountActive(isActive);
        bankAccountDTO.setLastUsage(lastUsage);
        return bankAccountDTO;
	}

	@Override
	public List<BankAccountDTO> getAccounts() {
		List<BankAccountDTO> accountDTOList = new ArrayList<>();
		accountDTOList.add(buildBankAccount("user3@wizeline.com", true, Country.MX, "08-09-2021"));
		accountDTOList.add(buildBankAccount("user2@wizeline.com", false, Country.FR, "07-09-2021"));
		accountDTOList.add(buildBankAccount("user1@wizeline.com", false, Country.US, "06-09-2021"));
		return accountDTOList;
	}

}
