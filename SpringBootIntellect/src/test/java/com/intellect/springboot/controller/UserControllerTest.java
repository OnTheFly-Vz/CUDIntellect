package com.intellect.springboot.controller;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.intellect.springboot.exception.ValidationException;
import com.intellect.springboot.jpa.UserApp;
import com.intellect.springboot.repository.UserRepository;

import org.junit.runners.MethodSorters;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private UserController controller;
	
	String jsonResponse = "{\"resMsg\":\"User created successfully\",\"userId\":1,\"valErrors\":null}";
	String json  = "{ \"id\" : \"123\",\"fName\" : \"John\",\"lName\" : \"Smith\",\"email\" : \"John.Smith@gmail.com\",\"pinCode\" : 123456,\"birthDate\" : \"02-MAR-2017\"}";
	
	String deletejsonInp  = "{ \"id\" : \"1\",\"fName\" : \"John\",\"lName\" : \"Smith\",\"email\" : \"John.Smith@gmail.com\",\"pinCode\" : 123456,\"birthDate\" : \"02-MAR-2017\"}";

	String deletejsonResponse = "{\"resMsg\":\"User is De-Activated\",\"userId\":null,\"valErrors\":null}";
	
	String updatejsonInp  = "{ \"id\" : \"1\",\"fName\" : \"John\",\"lName\" : \"Smith\",\"email\" : \"John.Smith@gmail.com\",\"pinCode\" : 123456,\"birthDate\" : \"02-MAR-2017\"}";
	
	String updatejsonResponse = "{\"resMsg\":\"User details updated\",\"userId\":1,\"valErrors\":null}";
	
	
	String deletejsonInpExcep  = "{ \"id\" : \"ethi\",\"fName\" : \"John\",\"lName\" : \"Smith\",\"email\" : \"John.Smith@gmail.com\",\"pinCode\" : 123456,\"birthDate\" : \"02-MAR-2017\"}";
	
	String updatejsonResponseExcep = "{\"resMsg\":\"Failure\",\"userId\":null,\"valErrors\":[{\"code\":\"100\",\"field\":\"UserId\",\"message\":\"UserId is invalid\"}]}";
	
	@Test
	public void acreateUserTest() throws Exception {

		UserApp mockUser = new UserApp();
		mockUser.setId(1L);
		mockUser.setActive(true);
		mockUser.setBirthDate("02-MAR-2017");
		mockUser.setfName("Fname");
		mockUser.setlName("LName");
		mockUser.setPinCode(0);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user/createUser/")
				.accept(MediaType.APPLICATION_JSON).content(json)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		assertEquals(jsonResponse,
				response.getContentAsString());
	}
	
	
	@Test
	public void bupdateUserTest() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/user/updateUser/")
				.accept(MediaType.APPLICATION_JSON).content(updatejsonInp)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		assertEquals(updatejsonResponse,
				response.getContentAsString());
		
	}
	
	@Test
	public void cdeleteUserTest() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/user/deleteUser/")
				.accept(MediaType.APPLICATION_JSON).content(deletejsonInp)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		assertEquals(deletejsonResponse,
				response.getContentAsString());
	}
	
	
	@Test
	public void TestException() throws Exception{
//		throw new ValidationException("", "", "");
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/user/deleteUser/")
				.accept(MediaType.APPLICATION_JSON).content(deletejsonInpExcep)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

//		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		assertEquals(updatejsonResponseExcep,
				response.getContentAsString());
	}
	
	
	
	
}
