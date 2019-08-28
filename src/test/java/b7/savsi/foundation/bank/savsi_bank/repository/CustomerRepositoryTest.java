package b7.savsi.foundation.bank.savsi_bank.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import b7.savsi.foundation.bank.savsi_bank.entity.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void testFindById() {
		Customer customer = new Customer("john", "612555555");
		Integer savedCustomerId = testEntityManager.persist(customer).getCustomerId();
		testEntityManager.flush();
		assertEquals("testFindById:TC1", "john", customerRepository.findById(savedCustomerId).orElse(null).getName());
		assertEquals("testFindById:TC2", null, customerRepository.findById(123).orElse(null));
	}

	@Test
	public void testSave() {
		Customer customerFound = customerRepository.save(new Customer("peter", "612555556"));
		assertThat(customerFound).hasFieldOrPropertyWithValue("name", "peter");
		assertThat(customerFound).hasFieldOrPropertyWithValue("phone", "612555556");
	}

}
