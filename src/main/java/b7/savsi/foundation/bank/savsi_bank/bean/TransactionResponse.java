package b7.savsi.foundation.bank.savsi_bank.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TransactionResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer sourceAccountId;
	private Integer destinationAccountId;

	@JsonIgnore
	private Long sourceBalance;
	@JsonIgnore
	private Long destinationBalance;
	private String transactionStatus;
	private String message;


	public TransactionResponse() {
		super();
	}

	public Integer getSourceAccountId() {
		return sourceAccountId;
	}

	public void setSourceAccountId(Integer sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

	public Integer getDestinationAccountId() {
		return destinationAccountId;
	}

	public void setDestinationAccountId(Integer destinationAccountId) {
		this.destinationAccountId = destinationAccountId;
	}

	public double getSourceBalance() {
		return sourceBalance;
	}

	public void setSourceBalance(Long sourceBalance) {
		this.sourceBalance = sourceBalance;
	}

	public Long getDestinationBalance() {
		return destinationBalance;
	}

	public void setDestinationBalance(Long destinationBalance) {
		this.destinationBalance = destinationBalance;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
