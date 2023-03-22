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
@Table(name = "comments")
@Entity
@NoArgsConstructor
public class Comment {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "comment_id",columnDefinition = "CHAR(32)")
	private String comment_id;

	@NonNull
	@Size(max = 254)
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "parent_comment_id")
	private String parentComment;
	
	@Column(name = "story_id")
	private String story_id;

	@Column(name = "comment")
	private String comment;

	@NonNull
	@Column(name = "submission_time")
	private LocalTime submissionTime;

}
