package b7.savsi.foundation.bank.savsi_bank.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer customerId;
	private String name;
	private String phone;

	public Customer(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}

	public Customer() {
		super();
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", phone=" + phone + "]";
	}

	public Customer(Integer customerId, String name, String phone) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.phone = phone;
	}

}
