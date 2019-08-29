package b7.savsi.foundation.bank.savsi_bank.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import b7.savsi.foundation.bank.savsi_bank.entity.Account;
import b7.savsi.foundation.bank.savsi_bank.entity.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void testFindById() {
		Account account = new Account("saving", new Customer("john", "612555555"), (long) 1000);
		Integer savedAccountId = testEntityManager.persist(account).getAccountID();
		testEntityManager.flush();
		assertEquals("testFindById:TC1", "saving",
				accountRepository.findById(savedAccountId).orElse(null).getAccountType());
		assertEquals("testFindById:TC2", null, accountRepository.findById(123).orElse(null));
	}

	@Test
	public void testSave() {
		Account accountFound = accountRepository
				.save(new Account("checking", new Customer("peter", "612555556"), (long) 3000));
		assertThat(accountFound).hasFieldOrPropertyWithValue("accountType", "checking");
		assertThat(accountFound.getCustomer()).hasFieldOrPropertyWithValue("name", "peter");

	}

}
