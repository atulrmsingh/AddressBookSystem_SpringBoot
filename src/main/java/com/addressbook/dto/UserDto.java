package com.addressbook.dto;

import java.util.List;

import com.addressbook.entity.AddressEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private String name;
	private String email;
	private Long mobile;
	
	
}
