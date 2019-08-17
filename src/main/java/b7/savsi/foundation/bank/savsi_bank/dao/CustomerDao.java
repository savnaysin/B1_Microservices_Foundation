package b7.savsi.foundation.bank.savsi_bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import b7.savsi.foundation.bank.savsi_bank.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

	String saveCustomer(Customer customer);

	List<Customer> findAll();

}
