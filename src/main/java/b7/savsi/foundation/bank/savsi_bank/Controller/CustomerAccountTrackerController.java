package b7.savsi.foundation.bank.savsi_bank.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import b7.savsi.foundation.bank.savsi_bank.bean.CustomerProfile;
import b7.savsi.foundation.bank.savsi_bank.bean.TransactionRequest;
import b7.savsi.foundation.bank.savsi_bank.bean.TransactionResponse;
import b7.savsi.foundation.bank.savsi_bank.entity.Account;
import b7.savsi.foundation.bank.savsi_bank.service.CustomerProfileService;
import b7.savsi.foundation.bank.savsi_bank.service.TransactionService;

@RestController
public class CustomerAccountTrackerController {
	@Autowired
	CustomerProfileService customerProfileService;
	@Autowired
	TransactionService TransactionService;

	@PostMapping(path = "/createNewAccount", consumes = "application/json", produces = "application/json")
	public void createNewAccount(@RequestBody CustomerProfile customerProfile) {
		customerProfileService.createAccount(customerProfile);
	}

	@GetMapping(path = "/accountInfo/{id}")
	public CustomerProfile getCustomerAccountProfile(@PathVariable("id") Integer accountId) {
		return customerProfileService.getAccountdetailsById(accountId);
	}
	
	@GetMapping(path = "/customerInfo/{id}")
	public CustomerProfile getCustomerProfile(@PathVariable("id") Integer customerId) {
		return customerProfileService.getCustomerdetailsById(customerId);
	}

	@PutMapping(path = "/transfer_funds", consumes = "application/json", produces = "application/json")
	public TransactionResponse transferFunds(@RequestBody TransactionRequest transactionRequest) {
		return TransactionService.transferFunds(transactionRequest);
	}

}
