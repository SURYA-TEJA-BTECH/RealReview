package com.surya.user;

import org.springframework.stereotype.Service;

import com.surya.messages.MessageConstants;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IUserImpl implements IUser {

	private final UserReposistry userRepo;

	@Override
	public Integer registerId(CreateUserRequest req) {

		Integer count = userRepo.checkEmailExists(req.getEmail());

		if (count != 0) {
			throw new UniqueEmailException(MessageConstants.EMAIL_EXISTS);
		}

		UserEntity user = new UserEntity(req.getName(), req.getEmail());

		return userRepo.save(user).getId();
	}

	@Override
	public String updateUserEmail(UserUpdateRequest req) {

		Integer count = userRepo.checkEmailExists(req.getEmail(), req.getId());
		if (count != 0) {
			throw new UniqueEmailException(MessageConstants.EMAIL_EXISTS);
		}

		userRepo.updateEmail(req.getEmail(), req.getId());

		return MessageConstants.UPDATE_EMAIL_SUCCESS_MESS;
	}

	@Override
	public String disableUser(Integer userId) {

		Boolean checkStatus = userRepo.checkStatus(userId);

		if (checkStatus == null) {
			throw new UserNotFoundException(UserNotFoundException.getMessage(userId));
		}

		userRepo.updateStatus(userId);
		return MessageConstants.DISABLE_USER_SUCCESS_MESSAGE;

	}

	@Override
	public UserEntity getUserById(Integer userId) {

		return userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(UserNotFoundException.getMessage(userId)));
	}

}
