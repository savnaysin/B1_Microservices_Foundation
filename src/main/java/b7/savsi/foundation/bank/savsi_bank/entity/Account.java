package b7.savsi.foundation.bank.savsi_bank.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer accountID;
	private String accountType;

	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId", referencedColumnName = "customerId")
	@JsonIgnore
	private Customer customer;

	private Long balance;

	public Account() {
		super();

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

	public Account(Integer accountID, String accountType, Customer customer, Long balance) {
		super();
		this.accountID = accountID;
		this.accountType = accountType;
		this.customer = customer;
		this.balance = balance;
	}

}
