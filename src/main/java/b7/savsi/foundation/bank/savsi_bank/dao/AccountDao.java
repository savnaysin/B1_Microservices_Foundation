package b7.savsi.foundation.bank.savsi_bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import b7.savsi.foundation.bank.savsi_bank.entity.Account;

public interface AccountDao extends JpaRepository<Account, Integer> {

	String saveAccount(Account Account);

	List<Account> findAll();

}
