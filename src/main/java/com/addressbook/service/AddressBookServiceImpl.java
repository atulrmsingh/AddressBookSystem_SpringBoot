package com.addressbook.service;

import java.util.List;
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
	public UserEntity addUser(AddUserDto user) {
		UserEntity user1 = map.map(user, UserEntity.class);
		System.out.println(user1.toString());
		user1 = userRepo.save(user1);
		return user1;
	}

	@Override
	public UserEntity updateUser(Long userId, UpdateUserDto user) {
		UserEntity user1 = userRepo.findById(userId).get();
		user1 = map.map(user, UserEntity.class);
		user1.setUserId(userId);
		user1 = userRepo.save(user1);
		return user1;
	}

	@Override
	public UserEntity getUserById(Long userId) {
		return userRepo.findById(userId).get();
	}

	@Override
	public void deleteUser(Long UserId) {
		userRepo.deleteById(UserId);
		System.out.println("User deleted");
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

	@Override
	public UserEntity findByEmail(String email) {
		UserEntity user = userRepo.findByEmail(email);
		return user;
	}

}
