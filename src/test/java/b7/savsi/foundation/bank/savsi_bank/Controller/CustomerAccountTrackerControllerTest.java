package b7.savsi.foundation.bank.savsi_bank.Controller;

import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import b7.savsi.foundation.bank.savsi_bank.entity.Account;
import b7.savsi.foundation.bank.savsi_bank.entity.Customer;
import b7.savsi.foundation.bank.savsi_bank.repository.AccountRepository;
import b7.savsi.foundation.bank.savsi_bank.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerAccountTrackerController.class, secure = false)
public class CustomerAccountTrackerControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	Account mockAccount;
	@MockBean
	Customer mockCustomer;
	@MockBean
	AccountRepository mockAccountRepository;
	@MockBean
	CustomerRepository mockCustomerReposistory;

	@Test
	public void testGetAccountProfile() throws Exception {

		mockCustomer = new Customer(1002, "savinay", "6124236666");
		mockAccount = new Account(1001, "current", mockCustomer, (long) 1000);
		Mockito.when(mockAccountRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(mockAccount));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accountInfo/1001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{accountID: 1001,accountType: current,balance: 1000}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetCustomerProfile() throws Exception {
		mockCustomer = new Customer(1003, "savinay","123456");
		Mockito.when(mockCustomerReposistory.findById(Mockito.anyInt())).thenReturn(Optional.of(mockCustomer));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customerInfo/1003")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{customerId:1003,name:savinay,phone: 123456}";	

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testCreateNewAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testTransferFunds() {
		fail("Not yet implemented");
	}

}
