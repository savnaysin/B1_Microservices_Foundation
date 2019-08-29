package b7.savsi.foundation.bank.savsi_bank.bean;

import java.io.Serializable;

public class TransactionRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer withdrawalAccountId;
	private Integer depositAccountId;
	private Long transactionamount;

	public Integer getWithdrawalAccountId() {
		return withdrawalAccountId;
	}

	public void setWithdrawalAccountId(Integer withdrawalAccountId) {
		this.withdrawalAccountId = withdrawalAccountId;
	}

	public Integer getDepositAccountId() {
		return depositAccountId;
	}

	public void setDepositAccountId(Integer depositAccountId) {
		this.depositAccountId = depositAccountId;
	}

	public Long getTransactionamount() {
		return transactionamount;
	}

	public void setTransactionamount(Long transactionamount) {
		this.transactionamount = transactionamount;
	}

	public TransactionRequest() {
		super();
	}

	

}
