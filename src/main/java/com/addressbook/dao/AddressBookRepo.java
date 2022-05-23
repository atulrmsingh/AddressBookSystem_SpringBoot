package com.addressbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.addressbook.entity.AddressEntity;
import com.addressbook.entity.UserEntity;

@Repository
public interface AddressBookRepo extends JpaRepository<UserEntity, Long> {

}
