package b7.savsi.foundation.bank.savsi_bank.Controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import b7.savsi.foundation.bank.savsi_bank.bean.CustomerProfile;
import b7.savsi.foundation.bank.savsi_bank.bean.TransactionRequest;
import b7.savsi.foundation.bank.savsi_bank.bean.TransactionResponse;
import b7.savsi.foundation.bank.savsi_bank.entity.Account;
import b7.savsi.foundation.bank.savsi_bank.entity.Customer;
import b7.savsi.foundation.bank.savsi_bank.exception.NotFoundException;
import b7.savsi.foundation.bank.savsi_bank.repository.AccountRepository;
import b7.savsi.foundation.bank.savsi_bank.repository.CustomerRepository;

@RestController
public class CustomerAccountTrackerController {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CustomerRepository customerReposistory;

	@GetMapping(path = "/accountInfo/{id}")
	public ResponseEntity<Account> getAccountProfile(@PathVariable("id") Integer accountId) throws NotFoundException {
		Account account = accountRepository.findById(accountId).orElse(null);
		if (account == null)
			throw new NotFoundException("Account not found for id :: " + accountId);
		return ResponseEntity.ok().body(account);
	}

	@GetMapping(path = "/customerInfo/{id}")
	public ResponseEntity<Customer> getCustomerProfile(@PathVariable("id") Integer customerId)
			throws NotFoundException {
		Customer customer = customerReposistory.findById(customerId).orElse(null);
		if (customer == null)
			throw new NotFoundException("Customer not found for id :: " + customerId);
		return ResponseEntity.ok().body(customer);
	}

	@PostMapping(path = "/createNewCustomer", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Void> createNewCustomer(@RequestBody CustomerProfile customerProfile) {
		Customer newCustomerSaved = customerReposistory
				.save(new Customer(customerProfile.getCustomerName(), customerProfile.getCustomerPhone()));

		if (newCustomerSaved == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCustomerSaved.getCustomerId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PostMapping(path = "/createNewAccount", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Void> createNewAccount(@RequestBody CustomerProfile customerProfile) {
		Customer newCustomer = new Customer(customerProfile.getCustomerName(), customerProfile.getCustomerPhone());
		Account newAccountSaved = accountRepository
				.save(new Account(customerProfile.getAccountType(), newCustomer, customerProfile.getAccountBalance()));
		if (newAccountSaved == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAccountSaved.getAccountID())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path = "/transferFunds", consumes = "application/json", produces = "application/json")
	public TransactionResponse transferFunds(@RequestBody TransactionRequest transactionRequest) {
		Account sourceAccount = accountRepository.findById(transactionRequest.getWithdrawalAccountId()).orElse(null);
		Account destinationAccount = accountRepository.findById(transactionRequest.getDepositAccountId()).orElse(null);
		Long amount = transactionRequest.getTransactionamount();
		Integer sourceActId = transactionRequest.getWithdrawalAccountId();
		Integer destActId = transactionRequest.getDepositAccountId();
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setSourceAccountId(sourceActId);
		transactionResponse.setDestinationAccountId(destActId);
		if (sourceAccount == null) {
			transactionResponse.setTransactionStatus("FAILED");
			transactionResponse.setMessage("SOURCE ACCOUNT NOT FOUND");
			return transactionResponse;
		}
		if (destinationAccount == null) {
			transactionResponse.setTransactionStatus("FAILED");
			transactionResponse.setMessage("DESTINATION ACCOUNT NOT FOUND");
			return transactionResponse;
		}
		if (sourceAccount != null && !((sourceAccount.getBalance() - amount) >= 0)) {
			transactionResponse.setTransactionStatus("FAILED");
			transactionResponse.setMessage("INSUFFICIENT FUNDS IN SOURCE ACCOUNT");
			return transactionResponse;
		}
		if (sourceAccount != null && destinationAccount != null) {
			destinationAccount.setBalance(destinationAccount.getBalance() + amount);
			sourceAccount.setBalance(sourceAccount.getBalance() - amount);
			//System.out.println(accountRepository.save(sourceAccount).getBalance());
			//transactionResponse.setSourceBalance(accountRepository.save(sourceAccount).getBalance());
			//transactionResponse.setDestinationBalance(accountRepository.save(destinationAccount).getBalance());
			transactionResponse.setTransactionStatus("SUCCESS");
			transactionResponse.setMessage("FUNDS TRANSFERRED");
		}
		return transactionResponse;
	}

}
