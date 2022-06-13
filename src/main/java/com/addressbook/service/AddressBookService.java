package com.addressbook.service;

import java.util.List;

import com.addressbook.dto.AddUserDto;
import com.addressbook.dto.UpdateUserDto;
import com.addressbook.entity.UserEntity;

public interface AddressBookService {

	public UserEntity addUser(AddUserDto user);

	public UserEntity updateUser(Long userId, UpdateUserDto user);

	public UserEntity getUserById(Long userId);

	public void deleteUser(Long UserId);

	public List<UserEntity> getAllUser();
	
	public List<UserEntity> getUserByZip(int zip);
	
	public List<UserEntity> getUserBylastName(String lastName);
	
	public UserEntity findByEmail(String email);
	

}
