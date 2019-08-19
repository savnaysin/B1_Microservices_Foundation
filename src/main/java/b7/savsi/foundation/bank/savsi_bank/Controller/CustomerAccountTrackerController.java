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

	// CreateNewAccount POST
	@PostMapping(path = "/createNewAccount", consumes = "application/json", produces = "application/json")
	public Account createNewAccount(@RequestBody CustomerProfile customerProfile) {
		return customerProfileService.createAccount(customerProfile);
	}

	// getAccountBalance GET

	// DisplayAccountsORCustomers GET

	@GetMapping(path = "/accountInfo/{id}")
	public CustomerProfile getCustomerAccountProfile(@PathVariable("id") Integer accountId) {
		return customerProfileService.getAccountdetailsById(accountId);
	}

	// TransferFunds POST
	@PutMapping(path = "/transfer_funds", consumes = "application/json", produces = "application/json")
	public TransactionResponse transferFunds(@RequestBody TransactionRequest transactionRequest) {
		return TransactionService.transferFunds(transactionRequest);
	}

}
