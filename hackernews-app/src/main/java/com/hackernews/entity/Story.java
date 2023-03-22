package com.hackernews.entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Table(name = "story")
@Entity
@NoArgsConstructor
public class Story {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "story_id",columnDefinition = "CHAR(32)")
	private String storyId;

	@NonNull
	@Size(max = 254)
	@Column(name = "user_id")
	private String userId;

	@Column(name = "descendants")
	private Integer descendants;

	@Column(name = "title")
	private String title;

	@Column(name = "score")
	private Integer score;

	@NonNull
	@Column(name = "submission_time")
	private LocalTime submissionTime;

	@Column(name = "url")
	private String url;
}
