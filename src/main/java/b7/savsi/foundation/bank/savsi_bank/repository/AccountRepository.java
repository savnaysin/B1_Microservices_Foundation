package b7.savsi.foundation.bank.savsi_bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import b7.savsi.foundation.bank.savsi_bank.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
