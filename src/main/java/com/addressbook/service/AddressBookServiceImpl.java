package com.addressbook.service;

import java.util.List;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addressbook.dao.AddressBookRepo;
import com.addressbook.dto.AddUserDto;
import com.addressbook.dto.UpdateUserDto;
import com.addressbook.entity.UserEntity;

@Service

public class AddressBookServiceImpl implements AddressBookService {
	@Autowired
	AddressBookRepo userRepo;
	ModelMapper map = new ModelMapper();

	@Override
	public String addUser(AddUserDto user) {
		JSONObject response = new JSONObject();
		UserEntity userEntity = userRepo.findByEmail(user.getEmail());
		if(null != userEntity) {
			response.put("status", "failure");
			response.put("message", "email id already exist");
			
		}else {
			UserEntity user1 = map.map(user, UserEntity.class);
			userRepo.save(user1);
			response.put("status", "success");
			response.put("message", "user added");
			response.put("data", user1);
		}
		
		return response.toString();
	}

	@Override
	public String updateUser(Long userId, UpdateUserDto user) {
		JSONObject response = new JSONObject();
		UserEntity user1 = userRepo.findById(userId).get();
		if(null != user1) {
			user1 = map.map(user, UserEntity.class);
			userRepo.save(user1);
			response.put("status", "success");
			response.put("message", "user details updated");
			response.put("data", user1);
		}else {
			response.put("status", "failure");
			response.put("message", "No user found");
		}
		return response.toString();
	}

	@Override
	public UserEntity getUserById(Long userId) {
		return userRepo.findById(userId).get();
	}

	@Override
	public String deleteUser(Long UserId) {
		JSONObject response = new JSONObject();
		UserEntity user1 = userRepo.findById(UserId).get();
		if(null != user1) {
			userRepo.deleteById(UserId);
			response.put("status", "success");
			response.put("message", "user deleted successfully");
			response.put("data", user1);
		}else {
			response.put("status", "failure");
			response.put("message", "No user found");
		}
		return response.toString();
	}

	@Override
	public List<UserEntity> getAllUser() {
		return userRepo.findAll();
	}

	@Override
	public List<UserEntity> getUserByZip(int zip) {
		List<UserEntity> userList = (List<UserEntity>) userRepo.findByZip(zip); 
		return userList;
	}

	@Override
	public List<UserEntity> getUserBylastName(String lastName) {
		List<UserEntity> userList = (List<UserEntity>) userRepo.findByLastName(lastName); 
		return userList;
	}

}
