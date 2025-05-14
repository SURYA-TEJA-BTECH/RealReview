package com.surya.rating;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.surya.image.ImageMetaData;
import com.surya.user.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "imageMetaData")

@Table(name = "rating", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "image_id" }))

public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String comment;

	private Integer score;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "imageMetaData")
	@JsonBackReference(value = "metaData-rating")
	private ImageMetaData imageMetaData;
}
