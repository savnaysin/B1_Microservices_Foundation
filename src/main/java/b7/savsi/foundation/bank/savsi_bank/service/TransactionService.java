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
		Account sourceAccount = accountDao.findByAccountID(transactionRequest.getDepositAccountId());
		Account destinationAccount = accountDao.findByAccountID(transactionRequest.getWithdrawalAccountId());
		Long amount = transactionRequest.getTransactionamount();
		Integer sourceActId = transactionRequest.getWithdrawalAccountId();
		Integer destActId = transactionRequest.getDepositAccountId();
		TransactionResponse transactionResponse = new TransactionResponse(sourceActId, destActId, null, null, "", "");

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
		destinationAccount.setBalance(destinationAccount.getBalance() + amount);
		sourceAccount.setBalance(sourceAccount.getBalance() - amount);
		accountDao.save(sourceAccount);
		accountDao.save(destinationAccount);

		transactionResponse.setSourceBalance(sourceAccount.getBalance());
		transactionResponse.setDestinationBalance(destinationAccount.getBalance());
		transactionResponse.setTransactionStatus("SUCCESS");
		transactionResponse.setMessage("FUNDS TRANSFERRED");
		return transactionResponse;

	}

}
