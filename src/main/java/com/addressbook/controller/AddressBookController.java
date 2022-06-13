package com.addressbook.controller;

import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.addressbook.dto.AddUserDto;
import com.addressbook.dto.UpdateUserDto;
import com.addressbook.entity.UserEntity;
import com.addressbook.response.ResponseHandler;
import com.addressbook.service.AddressBookService;



@RestController
@RequestMapping("/users")
public class AddressBookController {
	
	Logger logger = LoggerFactory.getLogger(AddressBookController.class);
	
	@Value("${user_add}")
	private String userAdd;
	
	@Value("${email_already_exist}")
	private String emailExist;

	@Value("${user_update}")
	private String userUpdate;

	@Value("${user_delete}")
	private String userDelete;

	@Value("${user_get}")
	private String userget;

	@Value("${userNotFound}")
	private String userNotFound;
	@Autowired
	AddressBookService userService;

	@PostMapping("/register")
	public ResponseEntity<Object> addUser(@Valid @RequestBody AddUserDto userEntity) {
		try {
			logger.info("Start of addUser API >> Request >> "+userEntity);
			UserEntity user = userService.findByEmail(userEntity.getEmail());
			if(null != user) {
				logger.info("Start of addUser API >> Message >> "+emailExist);
				return ResponseHandler.generateResponse(emailExist, HttpStatus.OK);
			}else {
				userService.addUser(userEntity);
				logger.info("Start of addUser API >> Response Msg  >> "+userAdd);
				return ResponseHandler.generateResponse(userAdd, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in addUser API >> Error Message >> "+e.getMessage());
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS);
		}
	}
	

	@GetMapping("/find/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable Long id) {
		try {
			UserEntity userEntity = userService.getUserById(id);
			return ResponseHandler.generateObjectResponse(userget, HttpStatus.OK, userEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(userNotFound, HttpStatus.MULTI_STATUS);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto user) {
		try {

			userService.updateUser(id, user);
			return ResponseHandler.generateResponse(userUpdate, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(userNotFound, HttpStatus.MULTI_STATUS);
		}

	}

	@GetMapping("/allUsers")
	public ResponseEntity<Object> getAllUsers() {
		try {
			List<UserEntity> userList = userService.getAllUser();
			logger.info("Start of getAllUsers API >> Response : :"+userList );
			return ResponseHandler.generateObjectResponse(userget, HttpStatus.OK, userList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS);
		}
	}

	@DeleteMapping("/remove")
	public ResponseEntity<Object> deleteUserById(@RequestParam("id") Long id) {
		try {
			logger.info("Start of deleteUserById API >> Request : :"+id );
			userService.deleteUser(id);
			return ResponseHandler.generateResponse(userDelete, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(userNotFound, HttpStatus.MULTI_STATUS);
		}

	}
	
	@GetMapping("/getDetailsByLastName")
	public ResponseEntity<Object> getDetailsByLastName(@RequestParam ("lastName") String lastName) {
		try {
			List<UserEntity> userEntity = userService.getUserBylastName(lastName);
			return ResponseHandler.generateObjectResponse(userget, HttpStatus.OK, userEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(userNotFound, HttpStatus.MULTI_STATUS);
		}
	}
	
	@GetMapping("/getDetailsByZip")
	public ResponseEntity<Object> getDetailsByZip(@RequestParam ("zip") int zip) {
		try {
			List<UserEntity> userEntity = userService.getUserByZip(zip);
			return ResponseHandler.generateObjectResponse(userget, HttpStatus.OK, userEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(userNotFound, HttpStatus.MULTI_STATUS);
		}

	}

}
