package com.surya.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUpdateRequest {

	@NotBlank(message = "email is required")
	@Email(message = "enter a valid email address")
	private String email;

	@NotNull(message = "id is required")
	private Integer id;

}
