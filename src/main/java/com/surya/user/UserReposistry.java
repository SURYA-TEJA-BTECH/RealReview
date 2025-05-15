package com.surya.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface UserReposistry extends JpaRepository<UserEntity, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE UserEntity u SET u.isActive = false WHERE u.id = :userId")
	public Integer updateStatus(Integer userId);

	@Query("SELECT u.isActive from UserEntity u WHERE u.id = :userId")
	public Boolean checkStatus(Integer userId);

	@Query("SELECT count(u) FROM UserEntity u WHERE u.email = :userEmail AND u.id <> :userId")
	public Integer checkEmailExists(String userEmail, Integer userId);

	@Query("SELECT count(u) FROM UserEntity u WHERE u.email=:userEmail")
	public Integer checkEmailExists(String userEmail);

	@Modifying
	@Transactional
	@Query("UPDATE UserEntity u SET u.email=:email where u.id=id")
	public void updateEmail(String email, Integer id);

}
