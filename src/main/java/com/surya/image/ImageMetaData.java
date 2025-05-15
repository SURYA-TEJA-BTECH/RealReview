package com.surya.image;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.surya.rating.Rating;

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
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = { "ratings", "image" })
@Setter
@Getter
public class ImageMetaData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String fileName;
	

	@Column(nullable = false)
	private String filePath;

	@ManyToOne
	@JoinColumn(name = "image_id")
	@JsonBackReference
	private Image image;

	@Column(nullable = false)
	private LocalDateTime dateTime;

	@Column(nullable = false)
	private Boolean isAuthorized = Boolean.FALSE;

	@OneToMany(mappedBy = "imageMetaData")
	@JsonManagedReference(value = "metaData-rating")
	private List<Rating> ratings;

}
