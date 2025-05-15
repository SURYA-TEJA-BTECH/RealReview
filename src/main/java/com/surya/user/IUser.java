package com.surya.user;

public interface IUser {

	public Integer registerId(CreateUserRequest req);

	public String updateUserEmail(UserUpdateRequest req);

	public String disableUser(Integer userId);

	public UserEntity getUserById(Integer userId);

}
