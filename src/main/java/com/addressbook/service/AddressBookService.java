package com.addressbook.service;

import java.util.List;

import com.addressbook.dto.AddUserDto;
import com.addressbook.dto.UpdateUserDto;
import com.addressbook.entity.UserEntity;

public interface AddressBookService {

	public String addUser(AddUserDto user);

	public String updateUser(Long userId, UpdateUserDto user);

	public UserEntity getUserById(Long userId);

	public String deleteUser(Long UserId);

	public List<UserEntity> getAllUser();
	
	public List<UserEntity> getUserByZip(int zip);
	
	public List<UserEntity> getUserBylastName(String lastName);
	

}
