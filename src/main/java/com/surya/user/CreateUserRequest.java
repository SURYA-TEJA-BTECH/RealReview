package com.surya.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserRequest {

	@NotBlank(message = "user name is required")
	private String name;

	@NotBlank(message = "email is required")
	@Email(message = "enter a valid email address")
	private String email;

}
