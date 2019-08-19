package b7.savsi.foundation.bank.savsi_bank.bean;

public class TransactionRequest {

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
		// TODO Auto-generated constructor stub
	}
	public TransactionRequest(Integer withdrawalAccountId, Integer depositAccountId, Long transactionamount) {
		super();
		this.withdrawalAccountId = withdrawalAccountId;
		this.depositAccountId = depositAccountId;
		this.transactionamount = transactionamount;
	}
	
	

}
