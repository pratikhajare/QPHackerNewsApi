package com.hackernews.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/*
 *  @author Pratik Hajare
 *  
 * */
@Data
@AllArgsConstructor
@SuperBuilder
public class StoryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String storyId;

	@NotNull
	private String userId;

	private Integer descendants;

	private String title;

	private Integer score;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime submissionTime;

	private String url;

	@Builder.Default
	private String isViewed = "N";
}
