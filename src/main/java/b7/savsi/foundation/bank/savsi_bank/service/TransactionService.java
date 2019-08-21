package b7.savsi.foundation.bank.savsi_bank.service;

import javax.transaction.Transactional;

import org.hibernate.query.criteria.internal.predicate.ExistsPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import b7.savsi.foundation.bank.savsi_bank.bean.TransactionRequest;
import b7.savsi.foundation.bank.savsi_bank.bean.TransactionResponse;
import b7.savsi.foundation.bank.savsi_bank.dao.AccountDao;
import b7.savsi.foundation.bank.savsi_bank.dao.CustomerDao;
import b7.savsi.foundation.bank.savsi_bank.entity.Account;

@Service
public class TransactionService {

	@Autowired
	AccountDao accountDao;
	@Autowired
	CustomerDao customerDao;

	@Transactional
	public TransactionResponse transferFunds(TransactionRequest transactionRequest) {
		Account sourceAccount = accountDao.findByAccountID(transactionRequest.getWithdrawalAccountId());
		Account destinationAccount = accountDao.findByAccountID(transactionRequest.getDepositAccountId());
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
		if (sourceAccount != null && destinationAccount != null)
		{
		destinationAccount.setBalance(destinationAccount.getBalance() + amount);
		sourceAccount.setBalance(sourceAccount.getBalance() - amount);
		System.out.println(accountDao.save(sourceAccount).getBalance());
		transactionResponse.setSourceBalance(accountDao.save(sourceAccount).getBalance());
		transactionResponse.setDestinationBalance(accountDao.save(destinationAccount).getBalance());
		transactionResponse.setTransactionStatus("SUCCESS");
		transactionResponse.setMessage("FUNDS TRANSFERRED");
		}		
		
		System.out.println("$$$$$$$$$$$$$$$$$$");
		return transactionResponse;

	}

}
