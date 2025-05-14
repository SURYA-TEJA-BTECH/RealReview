package com.surya.image;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.surya.user.UserEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "metaData")
@Setter
@Getter
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer imageId;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@NonNull
	private UserEntity user;

	@Column(nullable = false)
	@NonNull
	private String location;

	@OneToMany(mappedBy = "image", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<ImageMetaData> metaData;

}
