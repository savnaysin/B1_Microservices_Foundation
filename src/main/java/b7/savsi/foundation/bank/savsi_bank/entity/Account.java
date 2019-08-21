package b7.savsi.foundation.bank.savsi_bank.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer accountID;
	private String accountType;

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "customerId", referencedColumnName = "customerId")
	private Customer customer;
	private Long balance;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
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

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Account(String accountType, Customer customer, Long balance) {
		super();
		this.accountType = accountType;
		this.customer = customer;
		this.balance = balance;
	}

}
