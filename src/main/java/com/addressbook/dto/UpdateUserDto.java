package com.addressbook.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
	@Email(message = "enter a valid email")
	private String email;
	@NotEmpty(message = "gender is mandatory")
	private String gender;
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String mobile;
	@NotEmpty(message = "city is mandatory")
	private String city;
	@NotEmpty(message = "state is mandatory")
	private String state;

}
