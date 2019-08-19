package b7.savsi.foundation.bank.savsi_bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import b7.savsi.foundation.bank.savsi_bank.bean.CustomerProfile;
import b7.savsi.foundation.bank.savsi_bank.dao.AccountDao;
import b7.savsi.foundation.bank.savsi_bank.dao.CustomerDao;
import b7.savsi.foundation.bank.savsi_bank.entity.Account;
import b7.savsi.foundation.bank.savsi_bank.entity.Customer;

@Service
public class CustomerProfileService {

	@Autowired
	AccountDao accountDao;
	@Autowired
	CustomerDao customerDao;

	public CustomerProfile getAccountdetailsById(Integer accountId) {
		Account accountFound = accountDao.findByAccountID(accountId);
		CustomerProfile customerProfile = new CustomerProfile();
		if (!(accountFound == null)) {
			customerProfile.setAccountID(accountFound.getAccountID());
			customerProfile.setAccountType(accountFound.getAccountType());
			customerProfile.setAccountType(accountFound.getAccountType());
			customerProfile.setCustomerId(accountFound.getCustomer().getCustomerId());
			customerProfile.setCustomerName(accountFound.getCustomer().getName());
			customerProfile.setCustomerPhone(accountFound.getCustomer().getPhone());
			customerProfile.setAccountBalance(accountFound.getBalance());
			return customerProfile;
		}
		return null;
	}

	public Account createAccount(CustomerProfile customerProfile) {
		Account newAccount= new Account();
		Customer newCustomer= new Customer();
		newCustomer.setName(customerProfile.getCustomerName());
		newCustomer.setPhone(customerProfile.getCustomerPhone());
		newAccount.setAccountType(customerProfile.getAccountType());
		newAccount.setCustomer(newCustomer);
		Account savedAccount=accountDao.save(newAccount);
		return savedAccount;
		
	}

}
