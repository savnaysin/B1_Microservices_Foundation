package b7.savsi.foundation.bank.savsi_bank.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import b7.savsi.foundation.bank.savsi_bank.bean.CustomerProfile;
import b7.savsi.foundation.bank.savsi_bank.service.CustomerProfileService;

@RestController
public class CustomerAccountTrackerController {
	@Autowired
	CustomerProfileService customerProfileService;
	// CreateNewAccount POST

	// getAccountBalance GET

	// DisplayAccountsORCustomers GET

	@GetMapping("/accountInfo/{id}")
	public CustomerProfile getCustomerAccountProfile(@PathVariable("id") Integer accountId) {
		return customerProfileService.getAccountdetailsById(accountId);
	}
		
	// TransferFunds POST
}
