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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

/*
 *  @author Pratik Hajare
 *  
 * */

@Data
@Entity
@Table(name = "comments")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "comment_id", columnDefinition = "CHAR(32)")
	private String commentId;

	@NonNull
	@Size(max = 254)
	@Column(name = "user_id")
	private String userId;

	@Column(name = "parent_comment_id")
	private String parentComment;

	@Column(name = "story_id")
	private String storyId;

	@Column(name = "comment")
	private String comment;

	@CreationTimestamp
	@Column(name = "submission_time")
	private LocalDateTime submissionTime;

}
