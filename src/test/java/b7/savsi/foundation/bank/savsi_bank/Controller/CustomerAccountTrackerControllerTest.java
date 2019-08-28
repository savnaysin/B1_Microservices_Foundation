package b7.savsi.foundation.bank.savsi_bank.Controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import b7.savsi.foundation.bank.savsi_bank.bean.CustomerProfile;
import b7.savsi.foundation.bank.savsi_bank.bean.TransactionResponse;
import b7.savsi.foundation.bank.savsi_bank.entity.Account;
import b7.savsi.foundation.bank.savsi_bank.entity.Customer;
import b7.savsi.foundation.bank.savsi_bank.exception.NotFoundException;
import b7.savsi.foundation.bank.savsi_bank.repository.AccountRepository;
import b7.savsi.foundation.bank.savsi_bank.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerAccountTrackerController.class, secure = false)
public class CustomerAccountTrackerControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CustomerProfile customerProfile;
	@MockBean
	AccountRepository mockAccountRepository;
	@MockBean
	CustomerRepository mockCustomerReposistory;
	@MockBean(name = "customer1")
	Customer mockCustomer1;
	@MockBean(name = "account1")
	Account mockAccount1;
	@MockBean(name = "customer2")
	Customer mockCustomer2;
	@MockBean(name = "account2")
	Account mockAccount2;
	@MockBean
	TransactionResponse mockTransactionResponse;

	@Before
	public void setup() {
		mockCustomer1 = new Customer(101, "John", "6124236666");
		mockAccount1 = new Account(1001, "current", mockCustomer1, (long) 2000);
		mockCustomer2 = new Customer(102, "Peter", "6124236667");
		mockAccount2 = new Account(1002, "current", mockCustomer2, (long) 1000);
	}

	@Test
	public void testGetAccountProfileSuccess() throws Exception {
		Mockito.when(mockAccountRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(mockAccount1));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accountInfo/1001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{accountID: 1001,accountType: \"current\",balance: 2000}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test(expected = NotFoundException.class)
	public void testGetAccountProfileFailure() throws Exception {
		// Mockito.when(mockAccountRepository.findById(Mockito.anyInt())).thenThrow(new
		// NotFoundException("Account not found for id :: " + 1001));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accountInfo/1001")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
	}

	@Test
	public void testGetCustomerProfile() throws Exception {
		Mockito.when(mockCustomerReposistory.findById(Mockito.anyInt())).thenReturn(Optional.of(mockCustomer1));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customerInfo/101")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{customerId: 101,name:\"John\",phone: \"6124236666\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testCreateNewAccount() throws Exception {
		String customerProfileJson = "{\"accountType\": \"current\",\"accountBalance\": 2000,\"customerName\":\"John\",\"customerPhone\": \"6124236666\"}";
		Mockito.when(mockAccountRepository.save(Mockito.any(Account.class))).thenReturn(mockAccount1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createNewAccount")
				.accept(MediaType.APPLICATION_JSON).content(customerProfileJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		Assert.assertEquals("testCreateNewAccount:TC1", HttpStatus.CREATED.value(), response.getStatus());
		Assert.assertEquals("testCreateNewAccount:TC2", "http://localhost/createNewAccount/1001",
				response.getHeader(HttpHeaders.LOCATION));

	}

	@Test
	public void testCreateNewCustomer() throws Exception {
		String customerProfileJson = "{\"customerName\":\"John\",\"customerPhone\": \"6124236666\"}";
		Mockito.when(mockCustomerReposistory.save(Mockito.any(Customer.class))).thenReturn(mockCustomer1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createNewCustomer")
				.accept(MediaType.APPLICATION_JSON).content(customerProfileJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		Assert.assertEquals("testCreateNewCustomer:TC1", HttpStatus.CREATED.value(), response.getStatus());
		Assert.assertEquals("testCreateNewCustomer:TC2", "http://localhost/createNewCustomer/101",
				response.getHeader(HttpHeaders.LOCATION));

	}

	@Test
	public void testTransferFunds() throws Exception {
		String transactionRequestJson = "{\"withdrawalAccountId\": 1001,\"depositAccountId\": 1002,\"transactionamount\":200}";
		String transactionResponseJson = "{\"sourceAccountId\": 1001,\"destinationAccountId\": 1002,\"transactionStatus\":\"SUCCESS\", \"message\":\"FUNDS TRANSFERRED\"}";
		Mockito.when(mockAccountRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(mockAccount1));
		Mockito.when(mockAccountRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(mockAccount2));

		Mockito.doNothing().when(mockTransactionResponse).setSourceAccountId(mockAccount1.getAccountID());
		Mockito.doNothing().when(mockTransactionResponse).setDestinationAccountId(mockAccount1.getAccountID());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/transferFunds").accept(MediaType.APPLICATION_JSON)
				.content(transactionRequestJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		JSONAssert.assertEquals(transactionResponseJson, result.getResponse().getContentAsString(), false);

	}

}
