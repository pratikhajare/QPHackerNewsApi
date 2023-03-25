package com.hackernews.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import lombok.AllArgsConstructor;
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

	private String id;

	private String by;

	private Integer descendants;

	private String title;

	private Integer score;

	private BigInteger time;

	private String url;

	private String type;

	private List<String> kids;
}
