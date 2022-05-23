package com.addressbook.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.addressbook.dto.UserDto;
import com.addressbook.entity.AddressEntity;
import com.addressbook.entity.UserEntity;
import com.addressbook.service.AddressBookService;

@RestController
@RequestMapping("/employee")
public class AddressBookController {
	@Autowired
	AddressBookService userService;

	@PostMapping("/addUser")
	public String addUser(@Valid @RequestBody UserEntity userEntity) {
		JSONObject response = new JSONObject();
		try {
			UserEntity userEntity1 = userService.addUser(userEntity);
			response.put("status", "sucess");
			response.put("message", "user added");
			response.put("data", userEntity1);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "failure");
			response.put("message", "bad request");

		}
		return response.toString();
	}

	@GetMapping("/getUser/{id}")
	public String getUserById(@PathVariable Long id) {
		JSONObject response = new JSONObject();
		try {

			UserEntity userEntity = userService.getUserById(id);
			response.put("status", "sucess");
			response.put("message", "User Details");
			response.put("data", userEntity);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "failure");
			response.put("message", "no user found");
		}
		return response.toString();

	}

	@PutMapping("/updateUser/{id}")
	public String updateUser(@PathVariable Long id, @RequestBody UserDto user) {
		JSONObject response = new JSONObject();
		UserEntity userEntity = userService.updateUser(id, user);
		response.put("status", "sucess");
		response.put("message", "User Details Updated");
		response.put("data", userEntity);
		return response.toString();

	}

	@GetMapping("/getAllUsers")
	public String getAllUsers() {
		JSONObject response = new JSONObject();
		List<UserEntity> userEntity = userService.getAllUser();
		response.put("status", "sucess");
		response.put("message", "All User Details ");
		response.put("data", userEntity);
		return response.toString();

	}

	@DeleteMapping("/deleteUserById")
	public String deleteUserById(@RequestParam("id") Long id) {
		JSONObject response = new JSONObject();
		userService.deleteUser(id);
		response.put("status", "sucess");
		response.put("message", "User Deleted ");
		return response.toString();

	}

}
