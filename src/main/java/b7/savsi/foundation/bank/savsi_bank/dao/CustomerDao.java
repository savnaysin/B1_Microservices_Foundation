package b7.savsi.foundation.bank.savsi_bank.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import b7.savsi.foundation.bank.savsi_bank.entity.Customer;

@Transactional
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

	Customer findByCustomerId(Integer customerId);

}
