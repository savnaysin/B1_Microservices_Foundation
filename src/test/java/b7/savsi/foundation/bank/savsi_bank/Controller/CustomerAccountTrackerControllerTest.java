package b7.savsi.foundation.bank.savsi_bank.Controller;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Ignore;
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
	CustomerProfile customerProfile;
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
		String expected = "{accountID: 1001,accountType: \"current\",balance: 1000}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetCustomerProfile() throws Exception {
		mockCustomer = new Customer(1003, "savinay", "123");

		Mockito.when(mockCustomerReposistory.findById(Mockito.anyInt())).thenReturn(Optional.of(mockCustomer));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customerInfo/1003")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{customerId: 1003,name:\"savinay\",phone: \"123\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testCreateNewAccount() throws Exception {
		mockCustomer = new Customer(1004, "John", "6124236667");
		mockAccount = new Account(1005, "saving", mockCustomer, (long) 5000);
		String customerProfileJson = "{1004,\"saving\",5000,1005,\"john\",\"6124236667\"}";
		Mockito.when(mockAccountRepository.save(Mockito.any(Account.class))).thenReturn(mockAccount);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createNewCustomer")
				.accept(MediaType.APPLICATION_JSON).content(customerProfileJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		Assert.assertEquals("http://localhost/createNewAccount/1", response.getHeader(HttpHeaders.LOCATION));

	}

	@Ignore
	@Test
	public void testTransferFunds() {

	}

}
