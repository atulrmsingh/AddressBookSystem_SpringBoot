package com.addressbook.service;

import java.util.List;

import com.addressbook.dto.UserDto;
import com.addressbook.entity.UserEntity;

public interface AddressBookService {

	public UserEntity addUser(UserEntity user);

	public UserEntity updateUser(Long userId, UserDto user);

	public UserEntity getUserById(Long userId);

	public void deleteUser(Long UserId);

	public List<UserEntity> getAllUser();

}
