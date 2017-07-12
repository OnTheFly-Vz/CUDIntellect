package com.intellect.springboot.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellect.springboot.exception.ValidationException;
import com.intellect.springboot.jpa.UserApp;
import com.intellect.springboot.model.AppResponse;
import com.intellect.springboot.model.AppResq;
import com.intellect.springboot.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Intellect User Service")
@RequestMapping(value="/user")
public class UserController {
	
	private static final  Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired(required = true)
	private UserRepository userRepository;
	
	@ApiOperation(value="Create new User")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "New User is created"),
	        @ApiResponse(code = 409, message = "User already exists with the same email id "),
	}
	)
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ResponseEntity<AppResponse> createUser(@RequestBody String appResq) throws ValidationException,IOException, ParseException{
		ObjectMapper mapper = new ObjectMapper();
		AppResq appreq = null;
		UserApp up = new UserApp();
		AppResponse appResp = new AppResponse();
		try {
			appreq = mapper.readValue(appResq, AppResq.class);
			inputValidation(appreq);
			if (!checkUserexists(appreq)) {
				up.seteMailid(appreq.getEmail());
				up.setActive(true);
				up.setfName(appreq.getfName());
				up.setPinCode(appreq.getPinCode());
				up.setlName(appreq.getlName());
				up.setBirthDate(appreq.getBirthDate());
				userRepository.save(up);
				logger.info("Generated id" + up.getId());
			}
		} finally{
			//Do nothing
		}

		if (up.getId() != null) {
			
			appResp.setUserId(up.getId());
			appResp.setResMsg("User created successfully");
			return new ResponseEntity<>(appResp, HttpStatus.CREATED);
		}

		appResp.setResMsg("User is already exists");
		return new ResponseEntity<>(appResp,HttpStatus.CONFLICT);

	}
	
	@ApiOperation(value="Delete User")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "User De-Activated"),
	        @ApiResponse(code = 409, message = "User not found "),
	}
	)
	@RequestMapping(value="/deleteUser", method= RequestMethod.DELETE)
	public ResponseEntity<AppResponse> deleteUser(@RequestBody String appResq) throws ValidationException, IOException, ParseException{
		ObjectMapper mapper = new ObjectMapper();
		AppResq appreq = null;
		AppResponse app = new AppResponse();
		UserApp userdb = new UserApp();
		try {
			appreq = mapper.readValue(appResq, AppResq.class);
			deleteInputValidation(appreq);
			userdb = userRepository.findByIdAndIsActive(Long.parseLong(appreq.getId()),true);
			if(userdb!=null){
				userdb.setActive(false);	
				userRepository.save(userdb);
				app.setResMsg("User is De-Activated");
				return new ResponseEntity<>(app,HttpStatus.OK);
			}else{
				logger.info("User is not active");
				throw new ValidationException("108","UserID","User Id "+appreq.getId()+" is NOT Active/Present");
			}
			}catch(Exception e){
				throw new ValidationException("100","UserId" ,"UserId is invalid");
				
			}
				finally{
			
				logger.info("Something");
			}
		
		
	}
	
	private void inputValidation(AppResq appreq) throws ValidationException, ParseException{
		
		if(appreq.getId()==null){
			throw new ValidationException("1000","ID","ID is Empty");
		}if(appreq.getEmail()==null){
			throw new ValidationException("1001","EMail","Email is Empty");
		}if(appreq.getPinCode()==0){
			throw new ValidationException("1002","PinCode","PinCode is InValid");
		}if(appreq.getBirthDate()==null || isInValidDate(appreq.getBirthDate())){
			throw new ValidationException("1003","BirthDay","Birthday is InValid");
		}if(appreq.getfName()==null){
			throw new ValidationException("1004","FirstName","FirstName is Empty");
		}if(appreq.getlName()==null){
			throw new ValidationException("1005","LastName","LastName is Empty");
		}
		
	}
	
private void updateInputValidation(AppResq appreq) throws ValidationException, ParseException{
		
		if(appreq.getId()==null){
			throw new ValidationException("1000","ID","ID is Empty");
		}if(appreq.getPinCode()==0){
			throw new ValidationException("1002","PinCode","PinCode is InValid");
		}if(appreq.getBirthDate()==null && isInValidDate(appreq.getBirthDate())){
			throw new ValidationException("1003","Birthday","Birthday is InValid");
		}
		
	}

private void deleteInputValidation(AppResq appreq) throws ValidationException, ParseException{
	
	if(appreq.getId()==null){
		throw new ValidationException("1000","ID","ID is Empty");
	}
	
}


	@ApiOperation(value="Update User details(PinCode/DateOfBirth")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "User Updated"),
	        @ApiResponse(code = 409, message = "User not found"),
	}
	)
	@RequestMapping(value="/updateUser", method= RequestMethod.PUT)
	public ResponseEntity<AppResponse> updateUser(@RequestBody String appResq) throws ValidationException,IOException,ParseException{
		ObjectMapper mapper = new ObjectMapper();
		AppResq appreq = null;
		AppResponse app = new AppResponse();
		UserApp userdb = new UserApp();
		try {
			appreq = mapper.readValue(appResq, AppResq.class);
			updateInputValidation(appreq);
			userdb = userRepository.findByIdAndIsActive(Long.parseLong(appreq.getId()),true);
			if(userdb!=null){
				userdb.setPinCode(appreq.getPinCode());
				userdb.setBirthDate(appreq.getBirthDate());
				userRepository.save(userdb);
				app.setResMsg("User details updated");
				app.setUserId(Long.parseLong(appreq.getId()));
				return new ResponseEntity<>(app,HttpStatus.OK);
			}else{
				logger.info("User is not active");
				app.setResMsg("No User Exists");
				app.setUserId(Long.parseLong(appreq.getId()));
				return new ResponseEntity<>(app,HttpStatus.BAD_REQUEST);
			}
			
		}finally{
			logger.info("Finally updateuser");
		}
	}

	private boolean checkUserexists(AppResq appreq) {
		UserApp dbUser = userRepository.findByeMailidAndIsActive(appreq.getEmail(),true);
		if (dbUser != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isInValidDate(String pDateString) throws ParseException, ValidationException {
		SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
		String d = pDateString;
        try {
        	f.parse(d);
            Date formatted = f.parse(d);
            Date sysDate = new Date();
            if(formatted.before(sysDate)){
                return false;
            }else{
            	return true;
            }
        }
        catch(ParseException e){
        	throw new ValidationException("1009","Birthday","Format is incorrect");
        }
}
}
