package b7.savsi.foundation.bank.savsi_bank.bean;

public class CustomerProfile {

	private Integer accountID;
	private String accountType;
	private Long accountBalance;
	private Integer customerId;
	private String customerName;
	private String customerPhone;

	public CustomerProfile(Integer accountID, String accountType, Long accountBalance, Integer customerId,
			String customerName, String customerPhone) {
		super();
		this.accountID = accountID;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
	}

	public CustomerProfile() {
		super();
	}

	@Override
	public String toString() {
		return "CustomerProfile [accountID=" + accountID + ", accountType=" + accountType + ", accountBalance="
				+ accountBalance + ", customerId=" + customerId + ", customerName=" + customerName + ", customerPhone="
				+ customerPhone + "]";
	}

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Long getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Long accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

}
