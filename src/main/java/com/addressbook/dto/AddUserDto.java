package com.addressbook.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDto {

	@NotEmpty(message = "enter a valid name")
	private String firstName;
	@NotEmpty(message = "enter a valid name")
	private String lastName;
	@Min(value = 15)
	private int age;
	@Email(message = "enter a valid email")
	private String email;
	private String gender;
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String mobile;
	private int zip;
	private String city;
	private String state;
	private String country;
	private List<AddressDto> address;

}
