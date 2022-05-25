package com.addressbook.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.addressbook.service.AddressBookService;

@RestController
@RequestMapping("/employee")
public class AddressBookController {
	@Autowired
	AddressBookService userService;

	@PostMapping("/addUser")
	public String addUser(@Valid @RequestBody AddUserDto userEntity) {
		String response = userService.addUser(userEntity);
		return response;
	}

	@GetMapping("/getUser/{id}")
	public String getUserById(@PathVariable Long id) {
		JSONObject response = new JSONObject();
		UserEntity userEntity = userService.getUserById(id);
		if(null != userEntity) {
			response.put("status", "sucess");
			response.put("message", "User Details");
			response.put("data", userEntity);
		}else {
			response.put("status", "failure");
			response.put("message", "No user found");
			response.put("data", userEntity);
		}
		return response.toString();
	}

	@PutMapping("/updateUser/{id}")
	public String updateUser(@PathVariable Long id, @RequestBody UpdateUserDto user) {
		String response = userService.updateUser(id, user);
		return response;

	}

	@GetMapping("/getAllUsers")
	public String getAllUsers() {
		JSONObject response = new JSONObject();
		List<UserEntity> userEntity = userService.getAllUser();
		if(userEntity.isEmpty()) {
			response.put("status", "failure");
			response.put("message", "No details found");
		}else {
			response.put("status", "sucess");
			response.put("message", "All User Details ");
			response.put("data", userEntity);
		}
		return response.toString();
	}

	@DeleteMapping("/deleteUserById")
	public String deleteUserById(@RequestParam("id") Long id) {
		String response = userService.deleteUser(id);
		return response;

	}
	
	@GetMapping("/getDetailsByLastName")
	public String getDetailsByLastName(@RequestParam ("lastName") String lastName) {
		JSONObject response = new JSONObject();
		List<UserEntity> userEntity = userService.getUserBylastName(lastName);
		if(userEntity.isEmpty()) {
			response.put("status", "failure");
			response.put("message", "No details found");
		}else {
			response.put("status", "sucess");
			response.put("message", "All User Details ");
			response.put("data", userEntity);
		}
		return response.toString();
	}
	
	@GetMapping("/getDetailsByZip")
	public String getDetailsByZip(@RequestParam ("zip") int zip) {
		JSONObject response = new JSONObject();
		List<UserEntity> userEntity = userService.getUserByZip(zip);
		if(userEntity.isEmpty()) {
			response.put("status", "failure");
			response.put("message", "No details found");
		}else {
			response.put("status", "sucess");
			response.put("message", "All User Details ");
			response.put("data", userEntity);
		}
		
		return response.toString();

	}

}
