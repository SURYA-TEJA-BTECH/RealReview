package com.surya.user;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserOperationsController {

	private final IUser userService;

	@PostMapping("/register")
	public ResponseEntity<Integer> registerUser(@RequestBody @Valid CreateUserRequest request) {

		Integer userId = userService.registerId(request);

		return new ResponseEntity<>(userId, HttpStatus.CREATED);
	}

	@PatchMapping("/update/email")
	public ResponseEntity<String> updateEmail(@RequestBody @Valid UserUpdateRequest request) {

		String message = userService.updateUserEmail(request);

		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PatchMapping("/disable/{id}")
	public ResponseEntity<String> disableUser(@PathVariable Integer id) {

		String message = userService.disableUser(id);

		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
