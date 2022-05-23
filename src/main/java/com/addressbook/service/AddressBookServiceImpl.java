package com.addressbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addressbook.dao.AddressBookRepo;
import com.addressbook.dto.UserDto;
import com.addressbook.entity.AddressEntity;
import com.addressbook.entity.UserEntity;

@Service

public class AddressBookServiceImpl implements AddressBookService {
	@Autowired
	AddressBookRepo userRepo;

	@Override
	public UserEntity addUser(UserEntity user) {
		return userRepo.save(user);
	}

	@Override
	public UserEntity updateUser(Long userId, UserDto user) {
		UserEntity user1 = userRepo.findById(userId).get();
		user1.setEmail(user.getEmail() == null ? user1.getEmail() : user.getEmail());
		user1.setName(user.getName() == null ? user1.getName() : user.getName());
		user1.setMobile(user.getMobile() == 0 ? user1.getMobile() : user.getMobile());
		return userRepo.save(user1);
	}

	@Override
	public UserEntity getUserById(Long userId) {
		return userRepo.findById(userId).get();
	}

	@Override
	public void deleteUser(Long UserId) {
		userRepo.deleteById(UserId);

	}

	@Override
	public List<UserEntity> getAllUser() {
		return userRepo.findAll();
	}

}
