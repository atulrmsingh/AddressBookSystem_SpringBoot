package com.addressbook.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.addressbook.entity.AddressEntity;
import com.addressbook.entity.UserEntity;

@Repository
public interface AddressBookRepo extends JpaRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	List<UserEntity> findByLastName(String lastName);
	List<UserEntity> findByZip(int zip);
}
