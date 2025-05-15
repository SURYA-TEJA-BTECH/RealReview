package com.surya.image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surya.user.UserEntity;

public interface ImageRepo extends JpaRepository<Image, Integer> {

	public List<Image> findByUser(UserEntity user);

}
