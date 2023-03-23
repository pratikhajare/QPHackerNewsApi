package com.hackernews.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String commentId;

	@NotNull
	private String userId;

	private String parentComment;

	@NotNull
	private String storyId;

	private String comment;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime submissionTime;

	private Integer totalChildComments;

	public CommentDto(Object[] objects) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		
		this.commentId = (String) objects[0];
		this.userId = (String) objects[1];
		this.parentComment = (String) objects[2];
		this.storyId = (String) objects[3];
		this.comment = (String) objects[4];
		this.submissionTime = objects[5] != null ? LocalDateTime.parse(objects[5].toString(), formatter) : null;
		this.totalChildComments = (objects[6] != null && !objects[6].toString().isEmpty())
				? Integer.valueOf(objects[6].toString())
				: 0;

	}

}
