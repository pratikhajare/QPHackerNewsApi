package com.hackernews.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Data
@Table(name = "story")
@Entity
@SuperBuilder
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

	@CreationTimestamp
	@Column(name = "submission_time")
	private LocalDateTime submissionTime;

	@Column(name = "url")
	private String url;
	
	@Column(name = "viewed")
	private String isViewed;
}
