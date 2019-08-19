package b7.savsi.foundation.bank.savsi_bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import b7.savsi.foundation.bank.savsi_bank.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

}
