package b7.savsi.foundation.bank.savsi_bank.bean;

public class CustomerProfile {

	private Integer accountID;
	private String accountType;
	private double accountBalance;
	private Integer customerId;
	private String customerName;
	private String customerPhone;
	private double depositAmount;
	private double withdrawalAmount;

	public CustomerProfile(Integer accountID, String accountType, double accountBalance, Integer customerId,
			String customerName, String customerPhone, double depositAmount, double withdrawalAmount) {
		super();
		this.accountID = accountID;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.depositAmount = depositAmount;
		this.withdrawalAmount = withdrawalAmount;
	}

	public CustomerProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CustomerProfile [accountID=" + accountID + ", accountType=" + accountType + ", accountBalance="
				+ accountBalance + ", customerId=" + customerId + ", customerName=" + customerName + ", customerPhone="
				+ customerPhone + ", depositAmount=" + depositAmount + ", withdrawalAmount=" + withdrawalAmount + "]";
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

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
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

	public double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public double getWithdrawalAmount() {
		return withdrawalAmount;
	}

	public void setWithdrawalAmount(double withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}

}
