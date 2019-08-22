package b7.savsi.foundation.bank.savsi_bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import b7.savsi.foundation.bank.savsi_bank.bean.CustomerProfile;
import b7.savsi.foundation.bank.savsi_bank.entity.Account;
import b7.savsi.foundation.bank.savsi_bank.entity.Customer;
import b7.savsi.foundation.bank.savsi_bank.repository.AccountRepository;
import b7.savsi.foundation.bank.savsi_bank.repository.CustomerRepository;

@Service
public class CustomerProfileService {

	@Autowired
	AccountRepository accountDao;
	@Autowired
	CustomerRepository customerDao;

	public void createAccount(CustomerProfile customerProfile) {
		
	}

}
